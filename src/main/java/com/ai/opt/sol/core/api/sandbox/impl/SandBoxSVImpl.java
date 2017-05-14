package com.ai.opt.sol.core.api.sandbox.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sol.api.sandbox.ISandBoxSV;
import com.ai.opt.sol.api.sandbox.param.*;
import com.ai.opt.sol.core.mapper.bo.ApiCallSetting;
import com.ai.opt.sol.core.mapper.bo.ApiCallSettingReq;
import com.ai.opt.sol.core.sandbox.mock.DubboMockUtil;
import com.ai.opt.sol.core.sandbox.mock.HttpClientUtil;
import com.ai.opt.sol.core.sandbox.util.OspSandboxUtil;
import com.ai.opt.sol.core.sandbox.util.ReflectUtil;
import com.ai.opt.sol.core.service.atom.interfaces.IApiCallSettingAtomSV;
import com.ai.opt.sol.core.service.atom.interfaces.IApiCallSettingReqAtomSV;
import com.ai.opt.sol.core.service.business.interfaces.IApiSandBoxSettingSV;
import com.ai.opt.sol.core.util.APISearchUtil;
import com.ai.paas.ipaas.dbs.util.CollectionUtil;
import com.ai.runner.apicollector.vo.APIDoc;
import com.ai.runner.apicollector.vo.APIParamDoc;
import com.ai.runner.base.exception.CallerException;
import com.ai.runner.base.exception.SystemException;
import com.ai.runner.base.vo.PageInfo;
import com.ai.runner.utils.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

//@Service
@Component
public class SandBoxSVImpl implements ISandBoxSV {

    @Autowired
    IApiCallSettingAtomSV iApiCallSettingAtomSV;

    @Autowired
    IApiSandBoxSettingSV iApiSandBoxSettingSV;

    @Autowired
    IApiCallSettingReqAtomSV iApiCallSettingReqAtomSV;

    @Override
    public APICallSetting getAPICallSettingFromES(String indexId) throws CallerException {
        if (StringUtil.isBlank(indexId)) {
            throw new SystemException("获取接口配置出错：API的索引ID为空");
        }
        /* 1.首先从搜索引擎获取相关信息 */
        String data = APISearchUtil.getAPIVersionNew(indexId);
        if (StringUtil.isBlank(data)) {
            throw new SystemException("获取接口配置出错：此API索引信息不存在");
        }
        APIDoc apiDoc = JSONObject.parseObject(data, APIDoc.class);
        APICallSetting record = new APICallSetting();
        /* 2.封装接口主题信息 */
        ApiCallSetting vo = iApiCallSettingAtomSV.getApiCallSetting(apiDoc.getOwner(),
                apiDoc.getInterfaceName(), apiDoc.getMethodName());
        if (vo != null) {
            BeanUtils.copyProperties(vo, record);
            record.setFirstSetting(false);
        } else {
            record.setInterfaceName(apiDoc.getInterfaceName());
            record.setMethod(apiDoc.getMethodName());
            record.setFirstSetting(true);
        }
        record.setIndexId(apiDoc.getId());
        record.setOwner(apiDoc.getOwner());
        record.setOwnerType(apiDoc.getOwnerType());
        record.setBriefComment(apiDoc.getBriefComment());
        record.setAuthor(apiDoc.getAuthor());
        record.setDetailComment(apiDoc.getDetailComment());
        record.setPublishDate(apiDoc.getPublishDate());
        // 2.1 从搜索引擎获取最新的接口返回值信息
        if (apiDoc.getReturnAPIParamDoc() != null) {
            record.setReturnComment(apiDoc.getReturnAPIParamDoc().getCommentText());
            record.setReturnJavaType(apiDoc.getReturnAPIParamDoc().getClassName());
            String editorType = OspSandboxUtil.getEditorType(apiDoc.getReturnAPIParamDoc()
                    .getClassName());
            record.setEditorType(editorType);
        }
        /* 2.2 封装接口参数信息，从搜索引擎获取最新的参数列表 */
        List<APICallSettingReq> reqSettings = new ArrayList<APICallSettingReq>();
        if (!CollectionUtil.isEmpty(apiDoc.getInAPIParamDocs())) {
            int i = 0;
            for (APIParamDoc paramDoc : apiDoc.getInAPIParamDocs()) {
                APICallSettingReq req = new APICallSettingReq();
                ApiCallSettingReq settingReq = iApiCallSettingReqAtomSV.getApiCallSettingReq(
                        apiDoc.getInterfaceName(), apiDoc.getMethodName(), paramDoc.getName());
                if (settingReq != null) {
                	System.out.println("进来");
                    req.setSettingId(settingReq.getSettingId());
                    req.setSettingReqId(settingReq.getSettingReqId());
                    req.setJson(settingReq.getJson());
                }else{
                	
                    for(APIParamDoc apiParamDoc:apiDoc.getInAPIParamDocs()){
                    	//需要考虑为基本数据类型的情况
                    	if(com.ai.opt.sol.core.sandbox.util.ReflectUtil.checkReflectMap(apiParamDoc.getClassName())){
                    		String inParamIndex=String.valueOf(apiParamDoc.getId());
                        	Map<Object,Object> map=ReflectUtil.reflectClass(inParamIndex);
                        	req.setJson(JSON.toJSONString(map));
                    	}else{
                    		req.setJson("");
                    	}
                    	
                }
                }
                String editorType = OspSandboxUtil.getEditorType(paramDoc.getClassName());
                req.setEditorType(editorType);
                req.setInterfaceName(apiDoc.getInterfaceName());
                req.setJavaType(paramDoc.getClassName());
                req.setMethod(apiDoc.getMethodName());
                req.setParamComment(paramDoc.getCommentText());
                req.setParamName(paramDoc.getName());
                req.setSort(i);
                i++;
                reqSettings.add(req);
            }
        }
        record.setReqSettings(reqSettings);
        System.out.println(JSON.toJSONString(record));
        return record;
    }

    @Override
    public void saveAPICallSetting(APICallSetting req) throws CallerException {
        if (req == null) {
            throw new SystemException("保存服务出入参数模板出错：参数为空");
        }
        if (StringUtil.isBlank(req.getOwner())) {
            throw new SystemException("保存服务出入参数模板出错：服务提供者为空");
        }
        if (StringUtil.isBlank(req.getInterfaceName())) {
            throw new SystemException("保存服务出入参数模板出错：服务接口为空");
        }
        if (StringUtil.isBlank(req.getMethod())) {
            throw new SystemException("保存服务出入参数模板出错：服务方法为空");
        }
        APIParamDoc[] inParamDocs = APISearchUtil.getAPIInAPIParamDocs(req.getOwnerType(),
                req.getOwner(), req.getInterfaceName(), req.getMethod());
        List<APICallSettingReq> reqSettings = req.getReqSettings();
        int size1 = CollectionUtil.isEmpty(inParamDocs) ? 0 : inParamDocs.length;
        int size2 = CollectionUtil.isEmpty(reqSettings) ? 0 : reqSettings.size();
        if (size1 != size2) {
            throw new SystemException("保存服务出入参数模板出错：模板设定的入参个数与实际不相符");
        }
        APIParamDoc returnAPIParamDoc = APISearchUtil.getAPIReturnAPIParamDocs(
                req.getOwnerType(), req.getOwner(), req.getInterfaceName(), req.getMethod());
        if (returnAPIParamDoc != null) {
            req.setReturnJavaType(returnAPIParamDoc.getClassName());
            req.setReturnComment(returnAPIParamDoc.getCommentText());
        }
        iApiSandBoxSettingSV.saveAPICallSetting(req);
    }

    @Override
    public List<APICallCase> getAPICallCases(String interfaceName, String method)
            throws CallerException {
        if (StringUtil.isBlank(interfaceName)) {
            throw new SystemException("服务接口为空");
        }
        if (StringUtil.isBlank(method)) {
            throw new SystemException("服务方法为空");
        }
        return iApiSandBoxSettingSV.getAPICallCases(interfaceName, method);
    }

    @Override
    public void saveAPICallCase(APICallCase callCase) throws CallerException {
        if (callCase == null) {
            throw new SystemException("保存API测试用例失败：没有数据");
        }
        if (StringUtil.isBlank(callCase.getInterfaceName())) {
            throw new SystemException("保存API测试用例失败：服务接口为空");
        }
        if (StringUtil.isBlank(callCase.getMethod())) {
            throw new SystemException("保存API测试用例失败：服务方法为空");
        }
        if (StringUtil.isBlank(callCase.getTester())) {
            throw new SystemException("保存API测试用例失败：测试人员为空");
        }

        List<APICallCaseReqParam> reqParams = callCase.getReqParams();
        if (!CollectionUtil.isEmpty(reqParams)) {
            for (APICallCaseReqParam p : reqParams) {
                if (StringUtil.isBlank(p.getParamName())) {
                    throw new SystemException("保存API测试用例失败：入参名称不能为空");
                }
            }
        }
        iApiSandBoxSettingSV.saveAPICallCase(callCase);
    }

    @Override
    public String excuteTest(APICallCase callCase, String registryURL) throws CallerException {
        if (callCase == null) {
            throw new SystemException("API服务测试失败：没有数据");
        }
        if (StringUtil.isBlank(callCase.getInterfaceName())) {
            throw new SystemException("API服务测试失败：服务接口为空");
        }
        if (StringUtil.isBlank(callCase.getMethod())) {
            throw new SystemException("API服务测试失败：服务方法为空");
        }
        if (StringUtil.isBlank(registryURL)) {
            throw new SystemException("API服务测试失败：没有选择注册中心");
        }

        List<APICallCaseReqParam> reqParams = callCase.getReqParams();
        if (!CollectionUtil.isEmpty(reqParams)) {
            for (APICallCaseReqParam p : reqParams) {
                if (StringUtil.isBlank(p.getParamName())) {
                    throw new SystemException("API服务测试失败：参数名称不能为空");
                }
            }
        }
        String result = iApiSandBoxSettingSV.mockTest(callCase, registryURL);
        return result;
    }

    @Override
    public void setAPISandboxSetting(APICallSetting req) throws CallerException {
        if (req == null) {
            throw new SystemException("保存沙箱设置出错：参数为空");
        }

        if (StringUtil.isBlank(req.getCallType())) {
            throw new SystemException("保存沙箱设置出错：调用方式为空");
        }
        if (StringUtil.isBlank(req.getSettingId())) {
            throw new SystemException("保存沙箱设置出错：服务设置ID为空");
        }
        if (StringUtil.isBlank(req.getInterfaceName())) {
            throw new SystemException("保存沙箱设置出错：服务接口为空");
        }
        if (StringUtil.isBlank(req.getMethod())) {
            throw new SystemException("保存沙箱设置出错：服务方法为空");
        }
        iApiSandBoxSettingSV.setAPISandboxSetting(req);
    }

    @Override
    public PageInfo<APICallCase> queryAPICallCases(APICallCaseQuery query) throws CallerException {
        if (query == null) {
            throw new SystemException("查询用例出错：参数为空");
        }
        return iApiSandBoxSettingSV.queryAPICallCases(query);
    }

    @Override
    public List<APICallCaseReqParam> queryAPICallCaseReqParams(String caseId)
            throws CallerException {
        return iApiSandBoxSettingSV.queryAPICallCaseReqParams(caseId);
    }

    @Override
    public boolean checkRegistryAvailable(String registryURL) throws CallerException {
        boolean check = DubboMockUtil.checkRegistryAvailable(registryURL);
        return check;
    }

    @Override
    public APIRest getAPIRest(String indexId) throws CallerException {
        if (StringUtil.isBlank(indexId)) {
            throw new SystemException("从索引获取API信息失败，缺少索引信息");
        }
        String data = APISearchUtil.getAPIVersionNew(indexId);
        if (StringUtil.isBlank(data)) {
            throw new SystemException("从索引获取API信息失败：此API索引信息不存在");
        }
        APIDoc apiDoc = JSONObject.parseObject(data, APIDoc.class);
        APIRest apiRest = new APIRest();
        BeanUtils.copyProperties(apiDoc, apiRest);
        ApiCallSetting vo = iApiCallSettingAtomSV.getApiCallSetting(apiDoc.getOwner(),
                apiDoc.getInterfaceName(), apiDoc.getMethodName());
        if (vo != null) {
            apiRest.setReturnJson(vo.getReturnJson());
        }
        if (apiDoc.getReturnAPIParamDoc() != null) {
            apiRest.setReturnComment(apiDoc.getReturnAPIParamDoc().getCommentText());
            apiRest.setReturnJavaType(apiDoc.getReturnAPIParamDoc().getClassName());
            String editorType = OspSandboxUtil.getEditorType(apiDoc.getReturnAPIParamDoc()
                    .getClassName());
            apiRest.setEditorType(editorType);
        }
        List<APIRestParam> restParams = new ArrayList<APIRestParam>();
        if (!CollectionUtil.isEmpty(apiDoc.getInAPIParamDocs())) {
            for (APIParamDoc paramDoc : apiDoc.getInAPIParamDocs()) {
                APIRestParam req = new APIRestParam();
                ApiCallSettingReq settingReq = iApiCallSettingReqAtomSV.getApiCallSettingReq(
                        apiDoc.getInterfaceName(), apiDoc.getMethodName(), paramDoc.getName());
                if (settingReq != null) {
                    req.setParamValue(settingReq.getJson());
                }
                String editorType = OspSandboxUtil.getEditorType(paramDoc.getClassName());
                req.setEditorType(editorType);
                req.setJavaType(paramDoc.getClassName());
                req.setParamComment(paramDoc.getCommentText());
                req.setParamName(paramDoc.getName());
                restParams.add(req);
            }
        }
        apiRest.setRestParams(restParams);
        return apiRest;
    }

    @Override
    public String testRest(APIRestTestReq restTestReq) throws CallerException {
        if (restTestReq == null) {
            throw new SystemException("测试REST服务失败，参数为空");
        }
        if (StringUtil.isBlank(restTestReq.getRestMethod())) {
            throw new SystemException("测试REST服务失败，REST请求方式为空");
        }
        if (StringUtil.isBlank(restTestReq.getRestURL())) {
            throw new SystemException("测试REST服务失败，REST请求地址为空");
        }
        String data = "";
        if (!CollectionUtil.isEmpty(restTestReq.getRestParams())) {
            APIRestParam p = restTestReq.getRestParams().get(0);
            data = p.getParamValue();
        }
        JSONObject obj = HttpClientUtil.sendPostRequest(restTestReq.getRestURL(), data);
        return obj.toJSONString();
    }

}
