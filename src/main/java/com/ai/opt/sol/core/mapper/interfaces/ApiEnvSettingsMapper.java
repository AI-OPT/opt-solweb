package com.ai.opt.sol.core.mapper.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.opt.sol.core.mapper.bo.ApiEnvSettings;
import com.ai.opt.sol.core.mapper.bo.ApiEnvSettingsCriteria;

public interface ApiEnvSettingsMapper {
    int countByExample(ApiEnvSettingsCriteria example);

    int deleteByExample(ApiEnvSettingsCriteria example);

    int deleteByPrimaryKey(String settingsId);

    int insert(ApiEnvSettings record);

    int insertSelective(ApiEnvSettings record);

    List<ApiEnvSettings> selectByExample(ApiEnvSettingsCriteria example);

    ApiEnvSettings selectByPrimaryKey(String settingsId);

    int updateByExampleSelective(@Param("record") ApiEnvSettings record, @Param("example") ApiEnvSettingsCriteria example);

    int updateByExample(@Param("record") ApiEnvSettings record, @Param("example") ApiEnvSettingsCriteria example);

    int updateByPrimaryKeySelective(ApiEnvSettings record);

    int updateByPrimaryKey(ApiEnvSettings record);
}