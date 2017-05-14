package com.ai.opt.sol.core.mapper.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.opt.sol.core.mapper.bo.ApiCallCase;
import com.ai.opt.sol.core.mapper.bo.ApiCallCaseCriteria;

public interface ApiCallCaseMapper {
    int countByExample(ApiCallCaseCriteria example);

    int deleteByExample(ApiCallCaseCriteria example);

    int deleteByPrimaryKey(String caseId);

    int insert(ApiCallCase record);

    int insertSelective(ApiCallCase record);

    List<ApiCallCase> selectByExample(ApiCallCaseCriteria example);

    ApiCallCase selectByPrimaryKey(String caseId);

    int updateByExampleSelective(@Param("record") ApiCallCase record, @Param("example") ApiCallCaseCriteria example);

    int updateByExample(@Param("record") ApiCallCase record, @Param("example") ApiCallCaseCriteria example);

    int updateByPrimaryKeySelective(ApiCallCase record);

    int updateByPrimaryKey(ApiCallCase record);
}