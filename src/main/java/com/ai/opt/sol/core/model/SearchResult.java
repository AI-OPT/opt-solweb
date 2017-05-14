package com.ai.opt.sol.core.model;

/**
 * Created by astraea on 2015/7/7.
 */
public class SearchResult {
    private long totalCount;
    private String result;

    public SearchResult(long totalCount, String result) {
        this.totalCount = totalCount;
        this.result = result;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "{\"totalCount\":" + totalCount + ",\"result\":" + result + "}";
    }
}
