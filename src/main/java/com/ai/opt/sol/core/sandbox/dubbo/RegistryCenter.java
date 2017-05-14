package com.ai.opt.sol.core.sandbox.dubbo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.utils.util.CollectionUtil;
import com.ai.runner.utils.util.StringUtil;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.registry.NotifyListener;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.alibaba.dubbo.registry.RegistryService;

/**
 * Title: MVNO-CRM <br>
 * Description: 注册中心封装<br>
 * Date: 2014年7月29日 <br>
 * Copyright (c) 2014 AILK <br>
 * 
 * @author zhangchao
 */
public class RegistryCenter {

	private Logger log = LoggerFactory.getLogger(RegistryCenter.class);

	private static Map<String, RegistryCenter> registryCenterMap = new HashMap<String, RegistryCenter>();

	// 存储所有应用名称
	private final Set<String> applications = new ConcurrentHashSet<String>();

	// 存储所有接口的类名
	private final Set<String> services = new ConcurrentHashSet<String>();

	// 存储提供此类的服务的应用集合
	// eg:com.ailk.mvno.crm.api.orders.orderpay.interfaces.IOrderPayDubboSV=[MVNO-CRM-BACK-FACTORY]
	private final Map<String, Set<String>> providerServiceApplications = new ConcurrentHashMap<String, Set<String>>();

	// 存储每个应用提供的服务集合 eg:mvno-uip-tele=[com.ailk.tele.mvno.service.uam.IUamSign,
	// com.ailk.tele.mvno.service.recharge.IRecharge]
	private final Map<String, Set<String>> providerApplicationServices = new ConcurrentHashMap<String, Set<String>>();

	// 存储每个服务的提供者集合eg:com.ailk.mvno.crm.api.orders.orderpay.interfaces.IOrderPayDubboSV=[dubbo://10.1.249.32:20882/com.ailk.mvno.crm.api.orders.orderpay.interfaces.IOrderPayDubboSV?anyhost=true&application=MVNO-CRM-BACK-FACTORY&default.retries=0&default.timeout=30000&dubbo=2.5.3&interface=com.ailk.mvno.crm.api.orders.orderpay.interfaces.IOrderPayDubboSV&methods=pay&pid=16311&revision=1.5-SNAPSHOT&side=provider&threads=300&timestamp=1406794426823]
	private final Map<String, List<URL>> serviceProviders = new ConcurrentHashMap<String, List<URL>>();

	// 存储每个注册中心最后包装好的应用信息
	private final Map<String, List<Application>> registryApplications = new ConcurrentHashMap<String, List<Application>>();

	// 注册中心URL
	private String registryURL;

	// 注册中心服务实例
	private RegistryService registry;

	// 注册中心是否连接成功
	private boolean connected;

	public static RegistryCenter getInstance(String registryURL) {
		RegistryCenter instance = null;
		if (!registryCenterMap.containsKey(registryURL)) {
			instance = new RegistryCenter(registryURL);
			instance.registryURL = registryURL;
			registryCenterMap.put(registryURL, instance);
		}
		instance = registryCenterMap.get(registryURL);
		return instance;
	}
	
	public static void clear(String registryURL){
	    if (!registryCenterMap.containsKey(registryURL)) {
	        registryCenterMap.remove(registryURL);
	    }
	}

	/**
	 * 根据注册中心地址构造注册中心实例
	 * 
	 * @param registryURL
	 */
	private RegistryCenter(String registryURL) {
		RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(
				RegistryFactory.class).getAdaptiveExtension();
		Registry registry = registryFactory.getRegistry(URL
				.valueOf(registryURL));
		this.registry = registry;
	}

	/**
	 * 获取注册中心所有存在服务提供的应用集合
	 * 
	 * @return
	 * @author zhangchao
	 */
	public Set<String> getApplications() {
		return Collections.unmodifiableSet(this.applications);
	}

	/**
	 * 获取注册中心上所有的接口服务类
	 * 
	 * @return
	 * @author zhangchao
	 */
	public Set<String> getServices() {
		return Collections.unmodifiableSet(this.services);
	}

	/**
	 * 获取接口服务类与服务提供者URL的关系。比如一个服务部署在2台实例
	 * 
	 * @return
	 * @author zhangchao
	 */
	public Map<String, List<URL>> getServiceProviders() {
		return Collections.unmodifiableMap(this.serviceProviders);
	}

	/**
	 * 获取注册中心上某应用下的所有接口服务类
	 * 
	 * @param application
	 * @return
	 * @author zhangchao
	 */
	public Set<String> getServices(String application) {
		return this.providerApplicationServices.get(application);
	}

	/**
	 * 获取接口服务类的方法
	 * 
	 * @param service
	 * @return
	 * @author zhangchao
	 */
	public List<String> getMethodsByService(String service) {
		List<String> ms = new ArrayList<String>();
		List<URL> urls = this.getProvidersByService(service);
		if (CollectionUtil.isEmpty(urls)) {
			return ms;
		}
		URL url = urls.get(0);
		String methods = url.getParameter("methods");
		if (StringUtil.isBlank(methods)) {
			return ms;
		}

		String[] methodarray = methods.split(",");
		for (int i = 0; i < methodarray.length; i++) {
			String m = methodarray[i];
			ms.add(m);
		}
		return ms;
	}

	/**
	 * 获取一个接口服务的所有提供者，多个实例部署则有多条记录
	 * 
	 * @param service
	 * @return
	 * @author zhangchao
	 */
	public List<URL> getProvidersByService(String service) {
		List<URL> urls = (List<URL>) this.serviceProviders.get(service);
		return urls == null ? null : Collections.unmodifiableList(urls);
	}

	/**
	 * 获取某一台主机如10.1.249.32上的所有服务URL
	 * 
	 * @param host
	 * @return
	 * @author zhangchao
	 */
	public List<URL> getProvidersByHost(String host) {
		List<URL> urls = new ArrayList<URL>();
		if ((host != null) && (host.length() > 0)) {
			for (List<URL> providers : this.serviceProviders.values()) {
				for (URL url : providers) {
					if (host.equals(url.getHost())) {
						urls.add(url);
					}
				}
			}
		}
		return urls;
	}

	/**
	 * 获取一个应用下的所有服务提供者。如果是多个实例，则一个接口会有两条记录
	 * 
	 * @param application
	 * @return
	 * @author zhangchao
	 */
	public List<URL> getProvidersByApplication(String application) {
		List<URL> urls = new ArrayList<URL>();
		if ((application != null) && (application.length() > 0)) {
			for (List<URL> providers : this.serviceProviders.values()) {
				for (URL url : providers) {
					if (application.equals(url.getParameter("application"))) {
						urls.add(url);
					}
				}
			}
		}
		return urls;
	}

	/**
	 * 获取所有部署主机列表。如10.1.249.32:20888; 10.1.249.33:20888
	 * 
	 * @return
	 * @author zhangchao
	 */
	public Set<String> getProvidersHosts() {
		Set<String> addresses = new HashSet<String>();
		for (List<URL> providers : this.serviceProviders.values()) {
			for (URL url : providers) {
				addresses.add(url.getHost() + ":" + url.getPort());
			}
		}
		return addresses;
	}

	/**
	 * 获取一个具体服务部署的主机列表.如10.1.249.32:20888; 10.1.249.33:20888
	 * 
	 * @param interfaceName
	 * @return
	 * @author zhangchao
	 */
	public Set<String> getProvidersHosts(String interfaceName) {
		Set<String> addresses = new HashSet<String>();
		List<URL> providers = this.serviceProviders.get(interfaceName);
		for (URL url : providers) {
			addresses.add(url.getHost() + ":" + url.getPort());
		}
		return addresses;
	}

	/**
	 * 获取服务URL
	 * 
	 * @param interfaceName
	 * @return
	 * @author zhangchao
	 */
	public String getProviderURL(String interfaceName) {
		List<URL> providers = this.serviceProviders.get(interfaceName);
		if (!CollectionUtil.isEmpty(providers)) {
			return providers.get(0).toIdentityString();
		}
		return null;
	}

	public void start() {
		log.debug("开始联系注册中心[" + registryURL + "]进行服务分析处理");
		System.out.println("开始联系注册中心[" + registryURL + "]进行服务分析处理");
		URL subscribeUrl = new URL("admin", NetUtils.getLocalHost(), 0, "",
				new String[] { "interface", "*", "group", "*", "version", "*",
						"classifier", "*", "category", "providers", "check",
						String.valueOf(false) });
		this.registry.subscribe(subscribeUrl, new NotifyListener() {
			public void notify(List<URL> urls) {
				if ((urls == null) || (urls.size() == 0)) {
					return;
				}
				// 存放单个接口的多个服务提供URL
				Map<String, List<URL>> proivderMap = new HashMap<String, List<URL>>();
				for (URL url : urls) {
					String application = url.getParameter("application");
					String category = url.getParameter("category", "providers");
					// 本功能只处理服务提供者
					if (!"providers".equals(category)) {
						continue;
					}
					// 如果提供者没有设置应用名称，则不处理
					if (StringUtil.isBlank(application) || "dubbo-monitor".equals(application)) {
						continue;
					}
					System.out.println("正在分析application=" + application
							+ "对应的URL：" + url.toFullString());
					log.debug("正在分析application=" + application + "对应的URL："
							+ url.toFullString());
					// 当前URL包含的服务接口
					String service = url.getServiceInterface();
					// 将服务接口类名存入
					RegistryCenter.this.services.add(service);
					// 存储当前服务的所有服务提供URL
					List<URL> list = (List<URL>) proivderMap.get(service);
					if (list == null) {
						list = new ArrayList<URL>();
						proivderMap.put(service, list);
					}
					list.add(url);

					// 存储应用名称
					RegistryCenter.this.applications.add(application);
					Set<String> serviceApplications = (Set<String>) RegistryCenter.this.providerServiceApplications
							.get(service);
					if (serviceApplications == null) {
						RegistryCenter.this.providerServiceApplications.put(
								service, new ConcurrentHashSet<String>());
						serviceApplications = (Set<String>) RegistryCenter.this.providerServiceApplications
								.get(service);
					}
					serviceApplications.add(application);

					Set<String> applicationServices = (Set<String>) RegistryCenter.this.providerApplicationServices
							.get(application);
					if (applicationServices == null) {
						RegistryCenter.this.providerApplicationServices.put(
								application, new ConcurrentHashSet<String>());
						applicationServices = (Set<String>) RegistryCenter.this.providerApplicationServices
								.get(application);
					}
					applicationServices.add(service);

				}

				if (!proivderMap.isEmpty()) {
					RegistryCenter.this.serviceProviders.putAll(proivderMap);
				}
			}
		});

		connected = true;
		log.debug("完成注册中心[" + registryURL + "]服务分析处理");
	}

	/**
	 * 构造注册中心与应用服务信息
	 * 
	 * @param applications
	 * @author zhangchao
	 */
	public void registryApps(List<Application> applications) {
		RegistryCenter.this.registryApplications.put(registryURL, applications);
	}

	/**
	 * 获取应用信息
	 * 
	 * @return
	 * @author zhangchao
	 */
	public List<Application> getApps() {
		return RegistryCenter.this.registryApplications.get(registryURL);
	}

	public boolean isConnected() {
		return connected;
	}

}
