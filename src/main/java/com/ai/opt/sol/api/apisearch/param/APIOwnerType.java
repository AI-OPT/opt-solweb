package com.ai.opt.sol.api.apisearch.param;

import java.io.Serializable;
import java.util.List;

public class APIOwnerType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务归属类型
     */
    private String ownerType;

    /**
     * 服务归属类型中文名称
     */
    private String ownerTypeName;

    /**
     * 包含产品个数
     */
    private int ownerCount;

    /**
     * 包含服务个数
     */
    private int serviceCount;

    /**
     * 该类型下的所有提供者的服务统计信息
     */
    private List<com.ai.opt.sol.api.apisearch.param.APIOwnerStatistics> ownerStatistics;

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerTypeName() {
        return ownerTypeName;
    }

    public void setOwnerTypeName(String ownerTypeName) {
        this.ownerTypeName = ownerTypeName;
    }

    public List<com.ai.opt.sol.api.apisearch.param.APIOwnerStatistics> getOwnerStatistics() {
        return ownerStatistics;
    }

    public void setOwnerStatistics(List<APIOwnerStatistics> ownerStatistics) {
        this.ownerStatistics = ownerStatistics;
    }

    public int getOwnerCount() {
        return ownerCount;
    }

    public void setOwnerCount(int ownerCount) {
        this.ownerCount = ownerCount;
    }

    public int getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(int serviceCount) {
        this.serviceCount = serviceCount;
    }

}
