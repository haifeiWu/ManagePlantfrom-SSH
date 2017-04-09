package com.haifeiWu.utils;

import java.io.File;

import com.haifeiWu.interceptor.HtmlToPdfInterceptor;

public class HtmlToPdf {
	// wkhtmltopdf在系统中的路径
	private static final String toPdfTool = PropertiesReadUtils
			.getPDFString("toolPath");

	/**
	 * html转pdf
	 * 
	 * @param srcPath
	 *            html路径，可以是硬盘上的路径，也可以是网络路径
	 * @param destPath
	 *            pdf保存路径
	 * @return 转换成功返回true
	 */
	private static boolean convert(String srcPath, String destPath) {
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
	 */
	public static void createPdf(String suspectId) {

		// 获取pdf的临时保存路径,也就是服务器的路径
		String pdfPath = PropertiesReadUtils.getPDFString("serverPath")
				+ suspectId + ".pdf";
		String path = PropertiesReadUtils.getPDFString("sourcePath")
				+ "LB-HB-20170317005";
		convert(path, pdfPath);

	}
}
