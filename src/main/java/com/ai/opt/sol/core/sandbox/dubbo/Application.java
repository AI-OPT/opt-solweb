package com.ai.opt.sol.core.sandbox.dubbo;

import java.util.List;

import com.ai.runner.utils.util.CollectionUtil;
import com.ai.runner.utils.util.StringUtil;

public class Application {

    // 所有在线的服务提供者
    private String appName;

    // 所有在线的服务列表
    private List<Service> services;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Service getService(String serviceName) {
        if (CollectionUtil.isEmpty(services))
            return null;
        if (StringUtil.isBlank(serviceName))
            return null;
        Service service = null;
        for (Service s : services) {
            if (s.getServiceName().equals(serviceName)) {
                service = s;
                break;
            }
        }
        return service;
    }

    public int getServicesCount() {
        return CollectionUtil.isEmpty(services) ? 0 : services.size();
    }

}
