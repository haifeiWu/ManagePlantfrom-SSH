package com.haifeiWu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件的工具类
 * 
 * @author wuhaifei
 * @d2016年11月18日
 */
public class PropertiesReadUtils {
	/*
	 * 私有化构造方法
	 */
	private PropertiesReadUtils() {

	}

	// 私有化的内部类
	private static class SingletonInstance {
		static PropertiesReadUtils instance = new PropertiesReadUtils();
	}

	// 获取PropertiesReadUtils的单例对象
	public static PropertiesReadUtils getInstance() {
		return SingletonInstance.instance;
	}

	/**
	 * 获取参数
	 * 
	 * @param param
	 *            参数的key
	 * @return 参数的value
	 */
	public static String getRecordConfString(String param) {
		// recordConf.properties是录播设备的服务器
		return getInstance().getPropertiesString("/recordConf.properties",
				param);
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public static String getPDFString(String param) {
		// recordConf.properties是录播设备的服务器
		return getInstance().getPropertiesString("/pdf.properties", param);
	}
	
	/**
	 * 获取公安局名称
	 * 
	 * @param param
	 * @return
	 */
	public static String getTitleString(String param) {
		// recordConf.properties是录播设备的服务器
		return getInstance().getPropertiesString("/title.properties", param);
	}


	/**
	 * 获取Peoperties文件的参数的值
	 * 
	 * @param path
	 *            路径
	 * @param param
	 *            参数的key
	 * @return 参数的value
	 */
	public String getPropertiesString(String path, String param) {// 应为private
		Properties prop = new Properties();
		InputStream in = this.getClass().getResourceAsStream(path);// path是录播设备的配置文件
		try {
			prop.load(in);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		return prop.getProperty(param);
	}
}
