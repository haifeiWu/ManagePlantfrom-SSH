package com.haifeiWu.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//
//import com.haifeiWu.utils.Video;
//import com.opensymphony.xwork2.ActionSupport;
//
///**
// * 活动记录
// * 
// * @author wuhaifei
// * @d2016年9月28日
// */
import org.springframework.stereotype.Controller;
//
//import java.io.IOException;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.struts2.interceptor.ServletRequestAware;
//import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haifeiWu.utils.Video;

@Controller
@Scope("prototype")
public class FTPAction {
	// /**
	// *
	// */
	private static final long serialVersionUID = 1201107017949225716L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;

	/**
	 * 配置FTP信息，，并发送上传文件的指令，，
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "ftp")
	public String configFTP() throws Exception {
		Video.setRBServerCfg();
		Video.setFtpServerCfg(1, "55");
		Video.uploadRecFile(1, "55");
		return "success";
	}
	//
	// /**
	// * @throws IOException
	// *
	// */
	// public String selectDownLoadStatu() throws IOException {
	// Video.queryDownloadFileStatu(1, "12436565768");
	// return "success";
	// }
	//
	// @Override
	// public void setServletContext(ServletContext application) {
	// this.application = application;
	// }
	//
	// @Override
	// public void setServletResponse(HttpServletResponse response) {
	// this.response = response;
	// }
	//
	// @Override
	// public void setServletRequest(HttpServletRequest request) {
	// this.request = request;
	// }

	// public String getSuspect_ID() {
	// return suspect_ID;
	// }
	//
	// public void setSuspect_ID(String suspect_ID) {
	// this.suspect_ID = suspect_ID;
	// }

}
