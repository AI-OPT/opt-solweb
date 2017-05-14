package com.ai.opt.sol.api.apisearch.param;

import java.io.Serializable;

public class APIVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 版本对应的API明细的索引ID
     */
    private String apiDetailIndexId;

    /**
     * 版本号
     */
    private String version;

    public String getApiDetailIndexId() {
        return apiDetailIndexId;
    }

    public void setApiDetailIndexId(String apiDetailIndexId) {
        this.apiDetailIndexId = apiDetailIndexId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
