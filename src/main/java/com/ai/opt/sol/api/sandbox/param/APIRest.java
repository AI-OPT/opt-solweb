package com.ai.opt.sol.api.sandbox.param;

import java.io.Serializable;
import java.util.List;

public class APIRest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String owner;

    private String ownerType;

    private String interfaceName;

    private String methodName;

    private String returnJavaType;

    private String returnComment;

    private String returnJson;

    private String editorType;

    private String restRelativeURL;

    private String restMethod;

    private List<APIRestParam> restParams;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
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

    public String getEditorType() {
        return editorType;
    }

    public void setEditorType(String editorType) {
        this.editorType = editorType;
    }

    public String getRestRelativeURL() {
        return restRelativeURL;
    }

    public void setRestRelativeURL(String restRelativeURL) {
        this.restRelativeURL = restRelativeURL;
    }

    public String getRestMethod() {
        return restMethod;
    }

    public void setRestMethod(String restMethod) {
        this.restMethod = restMethod;
    }

    public List<APIRestParam> getRestParams() {
        return restParams;
    }

    public void setRestParams(List<APIRestParam> restParams) {
        this.restParams = restParams;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    
    
}
