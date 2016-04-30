package com.ai.opt.sol.web.base.util;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.OfficeManager;

import com.ai.runner.base.exception.BusinessException;
  
//转换文档为pdf  
public final class OpenOfficeConvertPdf {  
	private static final Logger LOG = LogManager.getLogger(OpenOfficeConvertPdf.class);
    private static OfficeManager officeManager;//需要导入一个  
    
    public static void init(OfficeManager om){
    	officeManager=om;
    }
    public static void convert2PDF(String inputFile, String outputFile) throws FileNotFoundException {  
    	LOG.error("开始文档转换:" + inputFile + " --> " + outputFile);  
    	if(officeManager==null){
    		LOG.error("officeManager未实例化！");
    		throw new BusinessException("", "officeManager未实例化！");
    	}
        /* 1  进行转换 操作  
         *  deal  tranlate  
         */  
        String fileName1 = inputFile.substring(0, inputFile.lastIndexOf("."));  
        if(inputFile.endsWith("txt")){  
            String odtFile = fileName1+".odt";  
            if(new File(odtFile).exists()){  
            	LOG.error("odt文件已存在！");  
                inputFile = odtFile;  
            }else{  
                FileUtil.copyFile(inputFile, odtFile);  
                inputFile = odtFile;  
            }  
        }  
        //进行转换  
        LOG.error("进行文档转换:" + inputFile + " --> " + outputFile);  
        OfficeDocumentConverter converter = new OfficeDocumentConverter(  
                officeManager);  
        converter.convert(new File(inputFile), new File(outputFile));  
        LOG.error("完成文档转换:" + inputFile + " --> " + outputFile);  
  
    } 
     
}  