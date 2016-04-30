package com.ai.opt.sol.web.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
  
//转换成swf格式的  
public final class PdfConvertSwf {  
	private static final Logger LOG = LogManager.getLogger(PdfConvertSwf.class);
    private PdfConvertSwf(){}
    //private static String PDF2SWF_PATH = "C:\\Program Files (x86)\\SWFTools\\pdf2swf.exe";//swftools工具的安装路径  
    private static String PDF2SWF_PATH = ConfigUtils.getProperty("pdf2swf_path");
    public static void pdf2swf(String inputFile, String outputFile) {  
           File pdfFile = new File(inputFile);  
           File swfFile = new File(outputFile);  
           if(!inputFile.endsWith(".pdf")){  
        	   LOG.error("文件格式不是pdf");  
               return ;   
           }  
           if(!pdfFile.exists()){  
        	   LOG.error("pdf文件不存在！");  
               return ;  
           }  
           if(swfFile.exists()){  
        	   LOG.error("swf文件已存在！");  
               return ;  
           }  
           //进行转换  
           try{  
               /*  
                * languagedir=D:\\xpdf-chinese-simplified  用于处理pdf转换时出现的乱码  
                * 处理pdf转换的乱码需要进行三步   
                * 1 下载XPDF：ftp://ftp.foolabs.com/pub/xpdf/xpdf-chinese-simplified.tar.gz 解压到 D:\xpdf-chinese-simplified  
                  2下载字体:http://blog.pjoke.com/wp-content/uploads/2009/02/font.zip 解压到D:\xpdf-chinese-simplified\CMap\  
                  3 配置打开并修改xpdf-chinese-simplified目录下的add-to-xpdfrc文件。将里面的路径设为自己的路径：  
                */  
        	   // String command = PDF2SWF_PATH +" \""+inputFile+"\" -o "+swfFile+" -T 9 -s languagedir=D:\\xpdf-chinese-simplified";//-s languagedir=D:\\xpdf-chinese-simplified是用来处理转换时出现的中文乱码  
        	   LOG.error("开始转换文档: "+inputFile);  
        	   Process pro = null;  
               if (isWindowsSystem()) {  
                   //如果是windows系统  
                     //命令行命令  
                     String cmd = PDF2SWF_PATH + " \"" + inputFile + "\" -o \"" + outputFile + "\" -T 9 -f";  
                   //Runtime执行后返回创建的进程对象  
                     pro = Runtime.getRuntime().exec(cmd);  
               } else {  
                   //如果是linux系统,路径不能有空格，而且一定不能用双引号，否则无法创建进程  
            	   String cmd = PDF2SWF_PATH + " " + inputFile + "  -o  " + outputFile + "  -T 9 -f"; 
                   /*String[] cmd = new String[3];  
                   cmd[0] = PDF2SWF_PATH;  
                   cmd[1] = inputFile;  
                   cmd[2] = outputFile;*/  
                   //Runtime执行后返回创建的进程对象  
                     pro = Runtime.getRuntime().exec(cmd);  
               }  
               //非要读取一遍cmd的输出，要不不会flush生成文件（多线程）  
               new DoOutput(pro.getInputStream()).start();  
               new DoOutput(pro.getErrorStream()).start();  
               try {  
                   //调用waitFor方法，是为了阻塞当前进程，直到cmd执行完  
                    pro.waitFor();  
               } catch (InterruptedException e) {  
                  e.printStackTrace();  
                  LOG.error("转换文档为swf文件发生InterruptedException错误！具体原因为："+e.getMessage());  
               }  
                LOG.error("成功转换为SWF文件！");  
            } catch (IOException e) {  
                //e.printStackTrace();  
                LOG.error("转换文档为swf文件失败！具体原因为："+e.getMessage());  
            }  
    }  
    
    /** 
     * 判断是否是windows操作系统 
      * @author iori 
     * @return 
     */  
   private static boolean isWindowsSystem() {  
       String p = System.getProperty("os.name");  
       return p.toLowerCase().indexOf("windows") >= 0 ? true : false;  
   }  
      
   /** 
     * 多线程内部类 
      * 读取转换时cmd进程的标准输出流和错误输出流，这样做是因为如果不读取流，进程将死锁 
      * @author iori 
     */  
   private static class DoOutput extends Thread {  
       public InputStream is;  
       
       //构造方法  
        public DoOutput(InputStream is) {  
           this.is = is;  
       }  
       
       public void run() {  
           BufferedReader br = new BufferedReader(new InputStreamReader(this.is));  
           String str = null;  
           try {  
               //这里并没有对流的内容进行处理，只是读了一遍  
                 while ((str = br.readLine()) != null);  
           } catch (IOException e) {  
               e.printStackTrace();  
           } finally {  
               if (br != null) {  
                   try {  
                       br.close();  
                   } catch (IOException e) {  
                       e.printStackTrace();  
                   }  
               }  
           }  
       }  
   }  
  
  public static void main(String[] args) {  
           String inputFile ="g:/客户注册接口设计_横向.pdf";  
           String outputFile = "g:/客户注册接口设计_横向.swf";  
           PdfConvertSwf.pdf2swf(inputFile,outputFile);  
  }  
  
}  
