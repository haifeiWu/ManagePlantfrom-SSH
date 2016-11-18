package com.whfstudio.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

//测试读取properties文件
public class testProperties {
	@Test
	public void readProperties(){
		Properties prop =  new  Properties();    
        InputStream in = this.getClass().getResourceAsStream("/recordConf.properties");    
         try  {    
            prop.load(in);    
            String param1 = prop.getProperty("SetSplitType");    
            String param2 = prop.getProperty("StartRecording");  
            System.out.println("參數1："+param1);
            System.out.println("參數2："+param2);
        }  catch  (IOException e) {    
            e.printStackTrace();    
        }    
	}
}
