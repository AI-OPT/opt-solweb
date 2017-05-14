package com.ai.opt.sol.core.mapper.bo;

public class ApiCallSetting {
    private String settingId;

    private String apiCode;

    private String appName;

    private String interfaceName;

    private String method;

    private String returnJavaType;

    private String returnComment;

    private String returnJson;

    private String callType;

    private String sandboxResp;

    public String getSettingId() {
        return settingId;
    }

    public void setSettingId(String settingId) {
        this.settingId = settingId == null ? null : settingId.trim();
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode == null ? null : apiCode.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName == null ? null : interfaceName.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getReturnJavaType() {
        return returnJavaType;
    }

    public void setReturnJavaType(String returnJavaType) {
        this.returnJavaType = returnJavaType == null ? null : returnJavaType.trim();
    }

    public String getReturnComment() {
        return returnComment;
    }

    public void setReturnComment(String returnComment) {
        this.returnComment = returnComment == null ? null : returnComment.trim();
    }

    public String getReturnJson() {
        return returnJson;
    }

    public void setReturnJson(String returnJson) {
        this.returnJson = returnJson == null ? null : returnJson.trim();
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType == null ? null : callType.trim();
    }

    public String getSandboxResp() {
        return sandboxResp;
    }

    public void setSandboxResp(String sandboxResp) {
        this.sandboxResp = sandboxResp == null ? null : sandboxResp.trim();
    }
}