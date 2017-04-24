package com.haifeiWu.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Encoder;

/**
 * 这个工具类，用到了吗？？？
 * 
 * @author wuhaifei
 * @2017年4月15日
 */
public class Base64 {
	@SuppressWarnings("restriction")
	public static String file2base64(InputStream inputStream) {
		byte[] buffer = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = inputStream.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			inputStream.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(buffer);// 返回Base64编码过的字节数组字符串
	}

	// @SuppressWarnings("restriction")
	// public static String file2base64(File file) {
	// byte[] buffer = null;
	// try {
	// FileInputStream fis = new FileInputStream(file);
	// ByteArrayOutputStream bos = new ByteArrayOutputStream();
	// byte[] b = new byte[1024];
	// int n;
	// while ((n = fis.read(b)) != -1) {
	// bos.write(b, 0, n);
	// }
	// fis.close();
	// bos.close();
	// buffer = bos.toByteArray();
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// BASE64Encoder encoder = new BASE64Encoder();
	// return encoder.encode(buffer);// 返回Base64编码过的字节数组字符串
	// }

}
