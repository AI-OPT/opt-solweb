package com.ai.opt.sol.web.base.util;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.stereotype.Component;

@Component
public class OpenOfficeManagerService {
	private static final Logger LOG = LogManager.getLogger(OpenOfficeManagerService.class);
	private static OfficeManager officeManager;
    private static String OFFICE_HOME = "";//安装OPenOffice 的路径  
    private static int port[] = new int[1];  
    static{
    	OFFICE_HOME = ConfigUtils.getProperty("openoffice_home");
    	port[0]=Integer.parseInt(ConfigUtils.getProperty("openoffice_port"), 10);
    }
    @PostConstruct
	void init(){
		startService();
		OpenOfficeConvertPdf.init(officeManager);
	}
	// 打开服务器  
    public static void startService() {  
        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();  
        try {  
        	LOG.debug("准备启动服务....");  
            configuration.setOfficeHome(OFFICE_HOME);// 设置OpenOffice.org安装目录  
            configuration.setPortNumbers(port); // 设置转换端口，默认为8100  
            configuration.setTaskExecutionTimeout(1000 * 60 * 5L);// 设置任务执行超时为5分钟  
            configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);// 设置任务队列超时为24小时  
  
            officeManager = configuration.buildOfficeManager();  
            officeManager.start(); // 启动服务  
            LOG.debug("office转换服务启动成功!");  
        } catch (Exception ce) {  
            LOG.error("office转换服务启动失败!详细信息:" + ce);  
        }  
    }  
}
