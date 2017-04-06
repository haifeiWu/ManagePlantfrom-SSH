package com.haifeiWu.utils;

import java.io.File;



import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import sun.tools.tree.ThisExpression;

import com.haifeiWu.interceptor.HtmlToPdfInterceptor;

public class HtmlToPdf {
	//private static final String toPdfTool = "F:\\QMDownload\\wkhtmltox\\wkhtmltopdf\\bin\\wkhtmltopdf.exe";  
    
	
    /** 
     * html转pdf 
     * @param srcPath html路径，可以是硬盘上的路径，也可以是网络路径 
     * @param destPath pdf保存路径 
     * @return 转换成功返回true 
     * @throws IOException 
     */  
    public static boolean convert(String srcPath, String destPath) throws IOException{  
   	     Properties pro;
   	     pro = new Properties();  
        InputStream in =HtmlToPdf.class.getResourceAsStream("/wkhtmltox.properties");  
        pro.load(in);  
        in.close(); 
        if(in==null)
        {
        	System.out.println("没有获取到配置文件");
        }
           System.out.println("获取到配置文件"+in);
       
         String toPdfTool=pro.getProperty("path");
         System.out.println(toPdfTool);
         
           
        File file = new File(destPath);  
        File parent = file.getParentFile();  
       //如果pdf保存路径不存在，则创建路径  
       if(!parent.exists()){  
            parent.mkdirs();  
        }  
          
        StringBuilder cmd = new StringBuilder();  
        cmd.append(toPdfTool);  
        cmd.append(" ");  
        cmd.append(srcPath);  
        cmd.append(" ");  
        cmd.append(destPath);  
          
        boolean result = true;  
        try{  
            Process proc = Runtime.getRuntime().exec(cmd.toString());  
            HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());  
            HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());  
            error.start();  
            output.start();  
            proc.waitFor();  
        }catch(Exception e){  
            result = false;  
            e.printStackTrace();  
        }  
          
        return result;  
    }  
}

