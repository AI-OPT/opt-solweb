package com.ai.opt.sol.core.service.business.interfaces;

import java.util.List;

import com.ai.opt.sol.api.sandbox.param.APICallCase;
import com.ai.opt.sol.api.sandbox.param.APICallCaseQuery;
import com.ai.opt.sol.api.sandbox.param.APICallCaseReqParam;
import com.ai.opt.sol.api.sandbox.param.APICallSetting;
import com.ai.runner.base.vo.PageInfo;

public interface IApiSandBoxSettingSV {

    void saveAPICallSetting(APICallSetting req);

    List<APICallCase> getAPICallCases(String interfaceName, String method);

    void saveAPICallCase(APICallCase callCase);

    String mockTest(APICallCase callCase, String registryURL);

    void setAPISandboxSetting(APICallSetting req);
    
    PageInfo<APICallCase> queryAPICallCases(APICallCaseQuery query);
    
    List<APICallCaseReqParam> queryAPICallCaseReqParams(String caseId) ;

}
