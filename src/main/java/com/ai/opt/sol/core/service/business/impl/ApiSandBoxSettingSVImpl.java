package com.ai.opt.sol.core.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.sol.api.sandbox.param.*;
import com.ai.opt.sol.core.constants.OspConstants;
import com.ai.opt.sol.core.mapper.bo.ApiCallCase;
import com.ai.opt.sol.core.mapper.bo.ApiCallCaseReqParam;
import com.ai.opt.sol.core.mapper.bo.ApiCallSetting;
import com.ai.opt.sol.core.mapper.bo.ApiCallSettingReq;
import com.ai.opt.sol.core.sandbox.mock.DubboMockUtil;
import com.ai.opt.sol.core.sandbox.util.OspSandboxUtil;
import com.ai.opt.sol.core.service.atom.interfaces.*;
import com.ai.opt.sol.core.service.business.interfaces.IApiSandBoxSettingSV;
import com.ai.opt.sol.core.util.APISearchUtil;
import com.ai.paas.ipaas.dbs.util.CollectionUtil;
import com.ai.runner.apicollector.vo.APIParamDoc;
import com.ai.runner.base.exception.SystemException;
import com.ai.runner.base.vo.PageInfo;
import com.ai.runner.utils.util.DateUtil;
import com.ai.runner.utils.util.StringUtil;
import com.ai.runner.utils.util.UUIDUtil;
import com.alibaba.fastjson.JSON;

@Service
@Transactional
public class ApiSandBoxSettingSVImpl implements IApiSandBoxSettingSV {

    @Autowired
    private IApiCallSettingReqAtomSV iApiCallSettingReqAtomSV;

    @Autowired
    private IApiCallSettingAtomSV iApiCallSettingAtomSV;

    @Autowired
    private IApiCallCaseReqParamAtomSV iApiCallCaseReqParamAtomSV;

    @Autowired
    private IApiCallCaseAtomSV iApiCallCaseAtomSV;

    @Autowired
    private IApiEnvSettingsAtomSV iApiEnvSettingsAtomSV;

    @Override
    public List<APICallCase> getAPICallCases(String interfaceName, String method) {
        List<ApiCallCase> cases = iApiCallCaseAtomSV.getApiCallCases(interfaceName, method);
        if (CollectionUtil.isEmpty(cases)) {
            return null;
        }
        List<APICallCase> lst = new ArrayList<APICallCase>();
        for (ApiCallCase bo : cases) {
            APICallCase vo = new APICallCase();
            BeanUtils.copyProperties(bo, vo);
            List<APICallCaseReqParam> reqParams = this.getAPICallCaseReqParams(bo.getCaseId());
            vo.setReqParams(reqParams);
            lst.add(vo);
        }
        return lst;
    }

    private List<APICallCaseReqParam> getAPICallCaseReqParams(String caseId) {
        List<ApiCallCaseReqParam> reqParams = iApiCallCaseReqParamAtomSV
                .getApiCallCaseReqParams(caseId);
        List<APICallCaseReqParam> lst = new ArrayList<APICallCaseReqParam>();
        if (!CollectionUtil.isEmpty(reqParams)) {
            for (ApiCallCaseReqParam bo : reqParams) {
                APICallCaseReqParam vo = new APICallCaseReqParam();
                BeanUtils.copyProperties(bo, vo);
                lst.add(vo);
            }
        }
        return lst;
    }

    @Override
    public void saveAPICallCase(APICallCase callCase) {
        callCase.setCaseId(UUIDUtil.genId32());
        ApiCallCase record = new ApiCallCase();
        BeanUtils.copyProperties(callCase, record);
        record.setTestTime(DateUtil.getSysDate());
        iApiCallCaseAtomSV.add(record);

        List<APICallCaseReqParam> reqParams = callCase.getReqParams();
        if (!CollectionUtil.isEmpty(reqParams)) {
            for (APICallCaseReqParam bo : reqParams) {
                APIParamDoc paramDoc = APISearchUtil.getAPIInAPIParamDoc(callCase.getOwnerType(),
                        callCase.getOwner(), callCase.getInterfaceName(), callCase.getMethod(),
                        bo.getParamName());
                ApiCallCaseReqParam caseParam = new ApiCallCaseReqParam();
                caseParam.setReqId(UUIDUtil.genId32());
                caseParam.setCaseId(callCase.getCaseId());
                caseParam.setInterfaceName(callCase.getInterfaceName());
                caseParam.setJavaType(paramDoc.getClassName());
                caseParam.setMethod(callCase.getMethod());
                caseParam.setParamName(paramDoc.getName());
                caseParam.setSort(bo.getSort());
                caseParam.setJson(bo.getJson());
                iApiCallCaseReqParamAtomSV.add(caseParam);
            }
        }
    }

    @Override
    public String mockTest(APICallCase callCase, String registryURL) {
        String[] reqParamJavaTypes = null;
        String[] qs = null;
        List<APICallCaseReqParam> reqParams = callCase.getReqParams();
        if (!CollectionUtil.isEmpty(reqParams)) {
            reqParamJavaTypes = new String[reqParams.size()];
            qs = new String[reqParams.size()];
            for (APICallCaseReqParam bo : reqParams) {
                ApiCallSettingReq p = iApiCallSettingReqAtomSV.getApiCallSettingReq(
                        callCase.getInterfaceName(), callCase.getMethod(), bo.getParamName());
                int index = reqParams.indexOf(bo);
                reqParamJavaTypes[index] = p.getJavaType();
                qs[index] = bo.getJson();
            }
        }
        Object result = DubboMockUtil.mockCall(registryURL, callCase.getOwner(),
                callCase.getInterfaceName(), callCase.getMethod(), reqParamJavaTypes, qs);
        return JSON.toJSONString(result);
    }

    @Override
    public void saveAPICallSetting(APICallSetting req) {
        String settingId = null;
        /* 1.判断API设置主表是新增记录还是修改记录 */
        if (StringUtil.isBlank(req.getSettingId())) {
            settingId = UUIDUtil.genId32();
            ApiCallSetting setting = new ApiCallSetting();
            BeanUtils.copyProperties(req, setting);
            setting.setAppName(req.getOwner());
            setting.setApiCode(req.getApiCode());
            setting.setSettingId(settingId);
            setting.setSandboxResp(req.getReturnJson());
            setting.setCallType(OspConstants.CallType.CALL_TYPE_DIRECT);
            iApiCallSettingAtomSV.add(setting);
        } else {
            ApiCallSetting setting = new ApiCallSetting();
            settingId = req.getSettingId();
            setting.setAppName(req.getOwner());
            setting.setApiCode(req.getApiCode());
            setting.setSettingId(settingId);
            setting.setReturnComment(req.getReturnComment());
            setting.setReturnJavaType(req.getReturnJavaType());
            setting.setReturnJson(req.getReturnJson());
            iApiCallSettingAtomSV.update(setting);
        }
        /* 2.防止参数有增加或减少，先删除所有参数 */
        iApiCallSettingReqAtomSV.delete(settingId);
        /* 3.新增参数 */
        List<APICallSettingReq> reqSettings = req.getReqSettings();
        this.saveNewAPICallSettingReqs(settingId, req.getOwnerType(), req.getOwner(),
                req.getInterfaceName(), req.getMethod(), reqSettings);
    }

    /**
     * 保存新增的入参模板信息
     * 
     * @param settingId
     * @param interfaceName
     * @param method
     * @param reqParams
     * @author zhangchao
     */
    private void saveNewAPICallSettingReqs(String settingId, String ownerType, String owner,
            String interfaceName, String methodName, List<APICallSettingReq> reqParams) {
        if (CollectionUtil.isEmpty(reqParams))
            return;
        for (APICallSettingReq reqParam : reqParams) {
            if (StringUtil.isBlank(reqParam.getParamName())) {
                throw new SystemException("保存API入参模板出错：缺少参数名称");
            }
            APIParamDoc p = APISearchUtil.getAPIInAPIParamDoc(ownerType, owner, interfaceName,
                    methodName, reqParam.getParamName());
            if (p == null) {
                throw new SystemException("保存API入参模板出错：入参名称[" + reqParam.getParamName() + "]实际不存在");
            }
            ApiCallSettingReq record = new ApiCallSettingReq();
            record.setSettingReqId(UUIDUtil.genId32());
            record.setSettingId(settingId);
            record.setParamName(p.getName());
            record.setParamComment(p.getCommentText());
            record.setSort(reqParam.getSort());
            record.setJavaType(p.getClassName());
            record.setInterfaceName(interfaceName);
            record.setMethod(methodName);
            record.setJson(reqParam.getJson());
            iApiCallSettingReqAtomSV.add(record);
        }
    }

    @Override
    public void setAPISandboxSetting(APICallSetting req) {
        ApiCallSetting record = new ApiCallSetting();
        record.setSettingId(req.getSettingId());
        record.setCallType(req.getCallType());
        record.setSandboxResp(req.getSandboxResp());
        iApiCallSettingAtomSV.update(record);
    }

    @Override
    public PageInfo<APICallCase> queryAPICallCases(APICallCaseQuery query) {
        List<APICallCase> lst = new ArrayList<APICallCase>();
        List<ApiCallCase> list = iApiCallCaseAtomSV.getApiCallCases(query);
        if (!CollectionUtil.isEmpty(list)) {
            for (ApiCallCase src : list) {
                APICallCase bo = new APICallCase();
                BeanUtils.copyProperties(src, bo);
                lst.add(bo);
            }
        }
        int count = iApiCallCaseAtomSV.getCount(query);
        PageInfo<APICallCase> pageInfo = new PageInfo<APICallCase>();
        pageInfo.setCount(count);
        pageInfo.setPageNo(query.getPageInfo().getPageNo());
        pageInfo.setPageSize(query.getPageInfo().getPageSize());
        pageInfo.setPageCount(pageInfo.getPageCount());
        pageInfo.setResult(lst);
        return pageInfo;
    }

    @Override
    public List<APICallCaseReqParam> queryAPICallCaseReqParams(String caseId) {
        List<APICallCaseReqParam> lst = new ArrayList<APICallCaseReqParam>();
        List<ApiCallCaseReqParam> list = iApiCallCaseReqParamAtomSV.getApiCallCaseReqParams(caseId);
        if (!CollectionUtil.isEmpty(list)) {
            for (ApiCallCaseReqParam src : list) {
                APICallCaseReqParam bo = new APICallCaseReqParam();
                BeanUtils.copyProperties(src, bo);
                bo.setEditorType(OspSandboxUtil.getEditorType(src.getJavaType()));
                lst.add(bo);
            }
        }
        return lst;
    }
}
