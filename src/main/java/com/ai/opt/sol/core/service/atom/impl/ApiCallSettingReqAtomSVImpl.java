package com.ai.opt.sol.core.service.atom.impl;

import java.util.List;

import com.ai.opt.sol.core.mapper.interfaces.ApiCallSettingReqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sol.core.mapper.bo.ApiCallSettingReq;
import com.ai.opt.sol.core.mapper.bo.ApiCallSettingReqCriteria;
import com.ai.opt.sol.core.mapper.factory.MapperFactory;
import com.ai.opt.sol.core.service.atom.interfaces.IApiCallSettingReqAtomSV;
import com.ai.paas.ipaas.dbs.util.CollectionUtil;

@Component
public class ApiCallSettingReqAtomSVImpl implements IApiCallSettingReqAtomSV {
    @Autowired
    ApiCallSettingReqMapper apiCallSettingReqMapper;
    @Override
    public void add(ApiCallSettingReq record) {
        apiCallSettingReqMapper.insert(record);
    }

    @Override
    public void update(ApiCallSettingReq record) {
        apiCallSettingReqMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ApiCallSettingReq getApiCallSettingReq(String paramId) {
        return apiCallSettingReqMapper.selectByPrimaryKey(paramId);
    }

    @Override
    public List<ApiCallSettingReq> getApiCallSettingReqs(String interfaceName, String method) {
        ApiCallSettingReqCriteria sql = new ApiCallSettingReqCriteria();
        sql.or().andInterfaceNameEqualTo(interfaceName).andMethodEqualTo(method);
        sql.setOrderByClause(" sort asc");
        return apiCallSettingReqMapper.selectByExample(sql);
    }

    @Override
    public ApiCallSettingReq getApiCallSettingReq(String interfaceName, String method,
            String paramName) {
        ApiCallSettingReqCriteria sql = new ApiCallSettingReqCriteria();
        sql.or().andInterfaceNameEqualTo(interfaceName).andMethodEqualTo(method)
                .andParamNameEqualTo(paramName);
        List<ApiCallSettingReq> ls = apiCallSettingReqMapper
                .selectByExample(sql);
        return CollectionUtil.isEmpty(ls) ? null : ls.get(0);
    }

    @Override
    public void delete(String settingId) {
        ApiCallSettingReqCriteria sql = new ApiCallSettingReqCriteria();
        sql.or().andSettingIdEqualTo(settingId);
        apiCallSettingReqMapper.deleteByExample(sql);
    }

}
