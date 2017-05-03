package com.haifeiWu.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;

import com.haifeiWu.interceptor.HtmlToPdfInterceptor;

public class HtmlToPdf {

	/**
	 * html转pdf
	 * 
	 * @param srcPath
	 *            html路径，可以是硬盘上的路径，也可以是网络路径
	 * @param destPath
	 *            pdf保存路径
	 * @return 转换成功返回true
	 * @throws UnsupportedEncodingException 
	 */
	private static boolean convert(String srcPath, String destPath) throws UnsupportedEncodingException {
		// wkhtmltopdf在系统中的路径
		String toPdfTool =new String(PropertiesReadUtils.getPDFString("toolPath").getBytes("ISO-8859-1"),"utf-8");
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

		boolean result = true;
		try {
			Process proc = Runtime.getRuntime().exec(cmd.toString());
			HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(
					proc.getErrorStream());
			HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(
					proc.getInputStream());
			error.start();
			output.start();
			proc.waitFor();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * // 生成PDF
	 * 
	 * @param suspectId
	 * @throws UnsupportedEncodingException 
	 */
	public static void createPdf(String suspectId) throws UnsupportedEncodingException {

		// 获取pdf的临时保存路径,也就是服务器的路径
		String pdfPath = new String(PropertiesReadUtils.getPDFString("serverPath").getBytes("ISO-8859-1"),"utf-8")
				+ suspectId + ".pdf";
		String path = new String(PropertiesReadUtils.getPDFString("sourcePath").getBytes("ISO-8859-1"),"utf-8")
				+ suspectId;
		convert(path, pdfPath);
	}
}
