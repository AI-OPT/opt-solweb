package com.ai.opt.sol.core.service.atom.impl;

import java.util.List;

import com.ai.opt.sol.core.mapper.interfaces.ApiEnvSettingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sol.core.mapper.bo.ApiEnvSettings;
import com.ai.opt.sol.core.mapper.bo.ApiEnvSettingsCriteria;
import com.ai.opt.sol.core.mapper.factory.MapperFactory;
import com.ai.opt.sol.core.service.atom.interfaces.IApiEnvSettingsAtomSV;

@Component
public class ApiEnvSettingsAtomSVImpl implements IApiEnvSettingsAtomSV {

    @Autowired
    ApiEnvSettingsMapper apiEnvSettingsMapper;
    @Override
    public int insert(ApiEnvSettings record) {
        return apiEnvSettingsMapper.insert(record);
    }

    @Override
    public List<ApiEnvSettings> selectByExample(ApiEnvSettingsCriteria example) {
        return apiEnvSettingsMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKeySelective(ApiEnvSettings record) {
        return apiEnvSettingsMapper.updateByPrimaryKeySelective(record);
    }

}
