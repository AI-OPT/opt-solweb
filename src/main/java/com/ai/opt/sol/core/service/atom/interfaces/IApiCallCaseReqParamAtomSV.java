package com.ai.opt.sol.core.service.atom.interfaces;

import java.util.List;

import com.ai.opt.sol.core.mapper.bo.ApiCallCaseReqParam;

public interface IApiCallCaseReqParamAtomSV {

    void add(ApiCallCaseReqParam record);
    
    List<ApiCallCaseReqParam> getApiCallCaseReqParams(String caseId);
    
    

}
