package com.ai.opt.sol.web.controller.category;

import java.sql.Timestamp;

public class SolCategory {
    private String categoryId;

    private String categoryCode;

    private String categoryName;

    private String parentCategoryId;

    private String parentCategoryIds;

    private Long categorySort;

    private String catetoryRemark;

    private Timestamp createTime;

    private Timestamp updateTime;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId == null ? null : parentCategoryId.trim();
    }

    public String getParentCategoryIds() {
        return parentCategoryIds;
    }

    public void setParentCategoryIds(String parentCategoryIds) {
        this.parentCategoryIds = parentCategoryIds == null ? null : parentCategoryIds.trim();
    }

    public Long getCategorySort() {
        return categorySort;
    }

    public void setCategorySort(Long categorySort) {
        this.categorySort = categorySort;
    }

    public String getCatetoryRemark() {
        return catetoryRemark;
    }

    public void setCatetoryRemark(String catetoryRemark) {
        this.catetoryRemark = catetoryRemark == null ? null : catetoryRemark.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}