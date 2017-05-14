package com.ai.opt.sol.core.service.atom.impl;

import java.util.List;

import com.ai.opt.sol.core.mapper.interfaces.ApiCallSettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sol.core.mapper.bo.ApiCallSetting;
import com.ai.opt.sol.core.mapper.bo.ApiCallSettingCriteria;
import com.ai.opt.sol.core.mapper.factory.MapperFactory;
import com.ai.opt.sol.core.service.atom.interfaces.IApiCallSettingAtomSV;
import com.ai.paas.ipaas.dbs.util.CollectionUtil;

@Component
public class ApiCallSettingAtomSVImpl implements IApiCallSettingAtomSV {

    @Autowired
    ApiCallSettingMapper apiCallSettingMapper;
    @Override
    public void add(ApiCallSetting record) {
        apiCallSettingMapper.insert(record);
    }

    @Override
    public void update(ApiCallSetting record) {
        apiCallSettingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ApiCallSetting getApiCallSetting(String settingId) {
        return apiCallSettingMapper.selectByPrimaryKey(settingId);
    }

    @Override
    public ApiCallSetting getApiCallSetting(String appName, String interfaceName, String method) {
        ApiCallSettingCriteria sql = new ApiCallSettingCriteria();
        sql.or().andAppNameEqualTo(appName).andInterfaceNameEqualTo(interfaceName)
                .andMethodEqualTo(method);
        List<ApiCallSetting> list = apiCallSettingMapper.selectByExample(sql);
        return CollectionUtil.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public int getApiCallSettingCount(String appName) {
        ApiCallSettingCriteria sql = new ApiCallSettingCriteria();
        sql.or().andAppNameEqualTo(appName);
        List<ApiCallSetting> list = apiCallSettingMapper.selectByExample(sql);
        return CollectionUtil.isEmpty(list) ? 0 : list.size();
    }

    @Override
    public List<ApiCallSetting> getApiCallSettings(String appName) {
        ApiCallSettingCriteria sql = new ApiCallSettingCriteria();
        sql.or().andAppNameEqualTo(appName);
        List<ApiCallSetting> list = apiCallSettingMapper.selectByExample(sql);
        return list;
    }

}
