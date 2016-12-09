package com.haifeiWu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件复制的工具类
 * 
 * @author wuhaifei
 * @d2016年12月9日
 */
public class CopyFile {

	/*
	 * 私有化构造方法
	 */
	public CopyFile() {

	}

	// 私有化的内部类
	private static class SingletonInstance {
		static CopyFile instance = new CopyFile();
	}

	// 获取PropertiesReadUtils的单例对象
	public static CopyFile getInstance() {
		return SingletonInstance.instance;
	}

	/**
	 * 文件复制
	 * 
	 * @param srcFileName
	 * @param destFileName
	 */
	public static void copyFile(String srcFileName, String destFileName) {
		int byteread = 0; // 读取的字节数
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(new File(srcFileName));
			out = new FileOutputStream(new File(destFileName));
			byte[] buffer = new byte[1024];

			while ((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException();
		} catch (IOException e) {
			throw new RuntimeException();
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
