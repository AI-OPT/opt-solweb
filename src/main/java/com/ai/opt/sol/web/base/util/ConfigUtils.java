package com.ai.opt.sol.web.base.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 配置工具类，获取配置文件中的属性 * 
 * @author gucl
 * 
 */
public final class ConfigUtils {

	private static Properties prop = new Properties();;
	private static Logger LOG = LogManager.getLogger(ConfigUtils.class);
	private ConfigUtils() {
	}

	static {
		try {
			LOG.debug("开始加载配置文件");
			InputStream inStream = ConfigUtils.class.getClassLoader()
					.getResourceAsStream("context/config.properties");
			prop.load(inStream);
			
			LOG.debug("完成加载配置文件");
		} catch (Exception e) {
			LOG.debug("加载配置文件失败"+e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Properties getProp() {
		return prop;
	}

	public static void setProp(Properties prop) {
		ConfigUtils.prop = prop;
	}

	public static String getProperty(String key) {
		return prop.getProperty(key, "");
	}
	public static void main(String[] args) {
		System.out.println(ConfigUtils.getProperty("esconfig"));
	}
}
