package com.ai.opt.sol.api.sandbox.param;

import java.io.Serializable;
import java.util.List;

public class APICallSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    private int indexId;

    private String settingId;

    private String ownerType;

    private String owner;

    private String apiCode;

    private String interfaceName;

    private String method;
    
    // 简要说明
    private String briefComment;

    // 详细说明
    private String detailComment;

    // 自动发布时间
    private String publishDate;

    // 服务作者
    private String author;

    private String returnJavaType;

    private String returnComment;

    private String returnJson;

    private String callType;

    private String sandboxResp;

    private List<com.ai.opt.sol.api.sandbox.param.APICallSettingReq> reqSettings;

    /**
     * 返回参数编辑器类型
     */
    private String editorType;

    /**
     * 是否首次设置模板
     */
    private boolean isFirstSetting;

    public String getSettingId() {
        return settingId;
    }

    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
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

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getSandboxResp() {
        return sandboxResp;
    }

    public void setSandboxResp(String sandboxResp) {
        this.sandboxResp = sandboxResp;
    }

    public String getReturnJavaType() {
        return returnJavaType;
    }

    public void setReturnJavaType(String returnJavaType) {
        this.returnJavaType = returnJavaType;
    }

    public String getReturnComment() {
        return returnComment;
    }

    public void setReturnComment(String returnComment) {
        this.returnComment = returnComment;
    }

    public String getReturnJson() {
        return returnJson;
    }

    public void setReturnJson(String returnJson) {
        this.returnJson = returnJson;
    }

    public List<com.ai.opt.sol.api.sandbox.param.APICallSettingReq> getReqSettings() {
        return reqSettings;
    }

    public void setReqSettings(List<APICallSettingReq> reqSettings) {
        this.reqSettings = reqSettings;
    }

    public String getEditorType() {
        return editorType;
    }

    public void setEditorType(String editorType) {
        this.editorType = editorType;
    }

    public boolean isFirstSetting() {
        return isFirstSetting;
    }

    public void setFirstSetting(boolean isFirstSetting) {
        this.isFirstSetting = isFirstSetting;
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

    public int getIndexId() {
        return indexId;
    }

    public void setIndexId(int indexId) {
        this.indexId = indexId;
    }

    public String getBriefComment() {
        return briefComment;
    }

    public void setBriefComment(String briefComment) {
        this.briefComment = briefComment;
    }

    public String getDetailComment() {
        return detailComment;
    }

    public void setDetailComment(String detailComment) {
        this.detailComment = detailComment;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    

}
