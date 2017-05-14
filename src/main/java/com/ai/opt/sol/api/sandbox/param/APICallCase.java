package com.ai.opt.sol.api.sandbox.param;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class APICallCase implements Serializable {

    private static final long serialVersionUID = 1L;

    private String caseId;

    private String ownerType;

    private String owner;

    private String interfaceName;

    private String method;

    private String actualResult;

    private String tester;

    private Timestamp testTime;

    private String caseTag;

    private List<com.ai.opt.sol.api.sandbox.param.APICallCaseReqParam> reqParams;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

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

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public Timestamp getTestTime() {
        return testTime;
    }

    public void setTestTime(Timestamp testTime) {
        this.testTime = testTime;
    }

    public List<com.ai.opt.sol.api.sandbox.param.APICallCaseReqParam> getReqParams() {
        return reqParams;
    }

    public void setReqParams(List<APICallCaseReqParam> reqParams) {
        this.reqParams = reqParams;
    }

    public String getCaseTag() {
        return caseTag;
    }

    public void setCaseTag(String caseTag) {
        this.caseTag = caseTag;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    

}
