package com.ai.opt.sol.api.sandbox.param;

import java.io.Serializable;
import java.util.List;

public class APIRestTestReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String restURL;

    private String restMethod;

    private List<com.ai.opt.sol.api.sandbox.param.APIRestParam> restParams;

    public String getRestURL() {
        return restURL;
    }

    public void setRestURL(String restURL) {
        this.restURL = restURL;
    }

    public String getRestMethod() {
        return restMethod;
    }

    public void setRestMethod(String restMethod) {
        this.restMethod = restMethod;
    }

    public List<com.ai.opt.sol.api.sandbox.param.APIRestParam> getRestParams() {
        return restParams;
    }

    public void setRestParams(List<APIRestParam> restParams) {
        this.restParams = restParams;
    }

}
