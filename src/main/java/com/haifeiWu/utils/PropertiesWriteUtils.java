package com.haifeiWu.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesWriteUtils {
	/**
	 * 传递键值对的Map，更新properties文件
	 * 
	 * @param fileName
	 *            文件名(放在resource源包目录下)，需要后缀
	 * @param keyValueMap
	 *            键值对Map
	 */
	public static void updateProperties(String fileName,
			Map<String, String> keyValueMap) {
		// InputStream
		// inputStream=PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);//输入流
		String filePath = PropertiesWriteUtils.class.getClassLoader()
				.getResource(fileName).getFile();// 文件的路径
		System.out.println("propertiesPath:" + filePath);
		Properties props = new Properties();
		BufferedReader br = null;
		BufferedWriter bw = null;

		try {
			// 从输入流中读取属性列表（键和元素对）
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					filePath), "UTF-8"));
			props.load(br);
			br.close();

			// 写入属性文件
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath), "UTF-8"));
			// props.clear();// 清空旧的文件
			for (String key : keyValueMap.keySet())
				props.setProperty(key, keyValueMap.get(key));
			props.store(bw, "");
			bw.close();
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating " + ""
					+ " value error");
		} finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", "尖草坪");
		map.put("name", "太原工业学院");

		updateProperties("title.properties", map);
		// 要进行汉字的转码,否则会出现编码格式的失败
		String title = new String(PropertiesReadUtils.getTitleString("title")
				.getBytes("ISO-8859-1"), "utf-8");
		System.out.println(title);
		System.out.println(PropertiesReadUtils.getTitleString("name"));
		map.put("remoteServerIP", "192.168.1.220");
		updateProperties("recordConf.properties", map);
		System.out.println(PropertiesReadUtils
				.getRecordConfString("remoteServerIP"));

		map.put("remoteServerIP", "198.0.0.1");
		map.put("remoteServerPort", "120");
		String str = "http://"
				+ map.put(
						"StartRecording",
						"http://"
								+ PropertiesReadUtils
										.getRecordConfString("remoteServerIP")
								+ ":"
								+ PropertiesReadUtils
										.getRecordConfString("remoteServerPort")
								+ "/SxStartRecording.psp");
		updateProperties("recordConf.properties", map);
		System.out.println(PropertiesReadUtils
				.getRecordConfString("StartRecording"));

	}
}
