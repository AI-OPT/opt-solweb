package com.ai.opt.sol.core.mapper.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.opt.sol.core.mapper.bo.ApiCallCaseReqParam;
import com.ai.opt.sol.core.mapper.bo.ApiCallCaseReqParamCriteria;

public interface ApiCallCaseReqParamMapper {
    int countByExample(ApiCallCaseReqParamCriteria example);

    int deleteByExample(ApiCallCaseReqParamCriteria example);

    int deleteByPrimaryKey(String reqId);

    int insert(ApiCallCaseReqParam record);

    int insertSelective(ApiCallCaseReqParam record);

    List<ApiCallCaseReqParam> selectByExample(ApiCallCaseReqParamCriteria example);

    ApiCallCaseReqParam selectByPrimaryKey(String reqId);

    int updateByExampleSelective(@Param("record") ApiCallCaseReqParam record, @Param("example") ApiCallCaseReqParamCriteria example);

    int updateByExample(@Param("record") ApiCallCaseReqParam record, @Param("example") ApiCallCaseReqParamCriteria example);

    int updateByPrimaryKeySelective(ApiCallCaseReqParam record);

    int updateByPrimaryKey(ApiCallCaseReqParam record);
}