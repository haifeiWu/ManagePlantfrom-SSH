package com.haifeiWu.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.haifeiWu.interceptor.HtmlToPdfInterceptor;

public class HtmlToPdf {
	// private static final String toPdfTool =
	// "F:\\QMDownload\\wkhtmltox\\wkhtmltopdf\\bin\\wkhtmltopdf.exe";

	/**
	 * html转pdf
	 * 
	 * @param srcPath
	 *            html路径，可以是硬盘上的路径，也可以是网络路径
	 * @param destPath
	 *            pdf保存路径
	 * @return 转换成功返回true
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static boolean convert(String srcPath, String destPath)
			throws IOException {
		Properties pro;
		pro = new Properties();
		InputStream in = HtmlToPdf.class.getResourceAsStream("/pdf.properties");
		pro.load(in);
		in.close();
		// if(in==null)
		// {
		// System.out.println("没有获取到配置文件");
		// }
		System.out.println("获取到配置文件" + in);

		String toPdfTool = pro.getProperty("path");
		System.out.println(toPdfTool);

		File file = new File(destPath);
		File parent = file.getParentFile();
		// 如果pdf保存路径不存在，则创建路径
		if (!parent.exists()) {
			parent.mkdirs();
		}

		StringBuilder cmd = new StringBuilder();
		cmd.append(toPdfTool);
		cmd.append(" ");
		cmd.append(srcPath);
		cmd.append(" ");
		cmd.append(destPath);

		System.out.println("cmd");

		boolean result = true;
		try {
			Process proc = Runtime.getRuntime().exec(cmd.toString());
			HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(
					proc.getErrorStream());
			HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(
					proc.getInputStream());
			System.out.println("output");
			error.start();
			System.out.println("error");
			output.start();
			System.out.println("output");
			proc.waitFor();
			// proc.destroy();
			System.out.println("proc");
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		System.out.println("转换完成");
		return result;
	}
}
