package com.ai.opt.sol.api.sandbox.param;

import java.io.Serializable;

import com.ai.runner.base.vo.PageInfo;

public class APICallCaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String interfaceName;

    private String method;

    private String tester;
    
    private String caseTag;

    private PageInfo<com.ai.opt.sol.api.sandbox.param.APICallCase> pageInfo;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public PageInfo<com.ai.opt.sol.api.sandbox.param.APICallCase> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<APICallCase> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getCaseTag() {
        return caseTag;
    }

    public void setCaseTag(String caseTag) {
        this.caseTag = caseTag;
    }
    
    

}
