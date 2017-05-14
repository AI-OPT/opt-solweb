package com.ai.opt.sol.api.apisearch.param;

import java.io.Serializable;

/**
 * 按照服务提供者角度统计服务数量 Date: 2015年10月14日 <br>
 * Copyright (c) 2015 asiainfo.com <br>
 * 
 * @author zhangchao
 */
public class APIOwnerStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务归属类型
     */
    private String ownerType;

    /**
     * 服务提供者中文名称
     */
    private String ownerName;

    /**
     * 服务提供者代码
     */
    private String owner;

    /**
     * 提供的服务总数
     */
    private int apiCount;

    /**
     * 颜色样式
     */
    private String color;

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getApiCount() {
        return apiCount;
    }

    public void setApiCount(int apiCount) {
        this.apiCount = apiCount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    

}
