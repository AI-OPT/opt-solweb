package com.ai.opt.sol.api.apisearch.param;

import java.io.Serializable;

import com.ai.runner.base.vo.PageInfo;

public class APISearchKey implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务提供者
     */
    private String owner;

    /**
     * 服务提供者类型
     */
    private String ownerType;

    /**
     * 搜索关键字
     */
    private String keywords;

    /**
     * 限定搜索范围为没有处理异常的类 NULL=不限制 1=限定搜索没有声明CallerException的服务 0=限定搜索声明了CallerException的服务
     */
    private String limitException;

    private PageInfo<com.ai.opt.sol.api.apisearch.param.APISearchResult> pageInfo;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public PageInfo<com.ai.opt.sol.api.apisearch.param.APISearchResult> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<APISearchResult> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getLimitException() {
        return limitException;
    }

    public void setLimitException(String limitException) {
        this.limitException = limitException;
    }

}
