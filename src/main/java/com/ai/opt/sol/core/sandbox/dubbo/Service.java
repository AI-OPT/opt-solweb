package com.ai.opt.sol.core.sandbox.dubbo;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.common.URL;

public class Service {

    // 服务名
    private String serviceName;

    // 所有服务提供URLs
    private List<URL> urls;

    // 所有方法名称
    private List<String> methods = new ArrayList<String>();

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<URL> getUrls() {
        return urls;
    }

    public void setUrls(List<URL> urls) {
        this.urls = urls;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

}
