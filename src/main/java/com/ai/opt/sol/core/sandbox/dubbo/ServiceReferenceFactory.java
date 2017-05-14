package com.ai.opt.sol.core.sandbox.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;

public class ServiceReferenceFactory {

    public static ReferenceConfig<GenericService> getReferenceConfig(String registryURL) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        ApplicationConfig application = new ApplicationConfig();
        application.setName("osp-sandbox");
        // 连接注册中心配置 
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(registryURL);
        registry.setTimeout(100000);
        // 引用远程服务
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setGeneric(true);
        return reference;
    }

}
