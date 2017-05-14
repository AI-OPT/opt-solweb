package com.ai.opt.sol.core.sandbox.mock;

import java.util.HashMap;
import java.util.Map;

import com.ai.opt.sol.core.sandbox.dubbo.ServiceReferenceFactory;
import com.ai.opt.sol.core.sandbox.util.ReflectUtil;
import com.ai.runner.base.exception.SystemException;
import com.ai.runner.utils.util.StringUtil;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;

public class DubboMockUtil {

    /**
     * 获取dubbo业务接口的伪装服务
     * 
     * @param registryURL
     * @param interfaceName
     * @return
     * @author zhangchao
     */
    private static GenericService getGenericService(String registryURL, String interfaceName) {
        return getGenericService(registryURL, interfaceName, null);
    }

    private static GenericService getGenericService(String registryURL, String interfaceName,
            String version) {
        ReferenceConfig<GenericService> reference = ServiceReferenceFactory
                .getReferenceConfig(registryURL);
        reference.setInterface(interfaceName);
        if (!StringUtil.isBlank(version)) {
            reference.setVersion(version);
        }
        // ReferenceConfigCache cache=ReferenceConfigCache.getCache();
        // return cache.get(reference);
        return reference.get();
    }

    public static Object mockCall(String registryURL, String owner, String interfaceName,
            String method, String[] reqParamJavaTypes, String[] reqParams) {
        if (StringUtil.isBlank(registryURL)) {
            throw new SystemException("调用失败:dubbo注册中心地址不能为空");
        }
        if (StringUtil.isBlank(interfaceName)) {
            throw new SystemException("调用失败:dubbo接口类名不能为空");
        }
        if (StringUtil.isBlank(method)) {
            throw new SystemException("调用失败:dubbo接口方法名不能为空");
        }
        Object[] q = ReflectUtil.reflects(reqParamJavaTypes, reqParams);
        GenericService genericService = getGenericService(registryURL, interfaceName);
        Object result = null;
        String testResult = null;
        String remark = null;
        try {
            result = genericService.$invoke(method, reqParamJavaTypes, q);
            testResult = "success";
            remark = "测试成功";
        } catch (GenericException ex) {
            testResult = "failure";
            remark = ex.getExceptionMessage();
            throw new SystemException("调用异常:" + remark);
        } finally {
        }
        return result;
    }

    /**
     * 校验注册中心是否可以用
     * 
     * @param registryURL
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static boolean checkRegistryAvailable(String registryURL) {
        if (StringUtil.isBlank(registryURL)) {
            throw new SystemException("dubbo注册中心地址不能为空");
        }
        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class)
                .getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(URL.valueOf(registryURL));
        return registry.isAvailable();
    }

    public static void main(String[] agrs) {
        String owner = "runner-customer-center";
        String registryURL = "zookeeper://10.1.82.103:29181";
        String interfaceName = "com.ai.runner.center.customer.api.custquery.interfaces.ICustQuerySV";
        String method = "queryCustBaseInfoByCustId";
        String[] reqParamJavaTypes = new String[] { "com.ai.runner.center.customer.api.custquery.param.CustIdAndTenantIdQuery" };
        Map<String, Object> q = new HashMap<String, Object>();
        q.put("custId", "11");
        q.put("tenantId", "");
        String[] reqParams = new String[] { JSON.toJSONString(q) };
        Object object = mockCall(registryURL, owner, interfaceName, method, reqParamJavaTypes,
                reqParams);

    }

}
