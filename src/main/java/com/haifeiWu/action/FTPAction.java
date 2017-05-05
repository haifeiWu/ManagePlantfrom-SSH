package com.haifeiWu.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haifeiWu.utils.HtmlToPdf;
import com.haifeiWu.utils.Video;

@Controller
@Scope("prototype")
public class FTPAction {

	/**
	 * 只是用来测试，模拟离区时请求上传录像文件的指令，直接将数据写入即可
	 */
	@RequestMapping(value = "/ftp")
	public String configFTP() throws Exception {
		Video.setRBServerCfg();
		Video.setFtpServerCfg(1, "1243657687980");
		Video.uploadRecFile(1, "1243657687980");
		return "success";
	}

	@RequestMapping(value = "/pdf")
	public String testpdf() throws Exception {

		HtmlToPdf.createPdf("LB-HB-20170505001");
		return "success";
	}

}
