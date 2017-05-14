package com.ai.opt.sol.core.model;

import java.util.List;

public class APITemplate {
    
    private String id;
    
    private String owner;

    private String interfaceName;

    private String method;

    private String returnJavaType;

    private String returnComment;

    private String returnJson;
    
    private List<APIReqParamTemplate> reqParamTemplates;

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public List<APIReqParamTemplate> getReqParamTemplates() {
        return reqParamTemplates;
    }

    public void setReqParamTemplates(List<APIReqParamTemplate> reqParamTemplates) {
        this.reqParamTemplates = reqParamTemplates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
    
}
