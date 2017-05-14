package com.ai.opt.sol.api.sandbox.param;

import java.io.Serializable;

public class APICallSettingReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String settingReqId;

    private String settingId;

    private String paramName;

    private String paramComment;

    private Integer sort;

    private String javaType;

    private String interfaceName;

    private String method;

    private String json;

    /**
     * 输入参数编辑器类型
     */
    private String editorType;

    public String getSettingReqId() {
        return settingReqId;
    }

    public void setSettingReqId(String settingReqId) {
        this.settingReqId = settingReqId;
    }

    public String getSettingId() {
        return settingId;
    }

    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamComment() {
        return paramComment;
    }

    public void setParamComment(String paramComment) {
        this.paramComment = paramComment;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
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

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getEditorType() {
        return editorType;
    }

    public void setEditorType(String editorType) {
        this.editorType = editorType;
    }

}
