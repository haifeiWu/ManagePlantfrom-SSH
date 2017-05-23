package com.haifeiWu.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.haifeiWu.utils.Video;

@Controller
@Scope("prototype")
public class FTPAction {

	/**
	 * 只是用来测试，模拟离区时请求上传录像文件的指令，直接将数据写入即可
	 */
	@RequestMapping(value = "/ftp")
	public String configFTP(
			@RequestParam("identificationCard") String identificationCard)
			throws Exception {
		Video.setRBServerCfg();
		Video.setFtpServerCfg(1, identificationCard);
		Video.uploadRecFile(1, identificationCard);
		return "success";
	}

	// @RequestMapping(value = "/searchFTP")
	// public String searchFTP() throws Exception {
	// Video.queryDownloadFileStatu(1, "2423134242432423332");
	// return "success";
	// }

	// @RequestMapping(value = "/pdf")
	// public String testpdf() throws Exception {
	// HtmlToPdf.createPdf("LB-HB-20170505001");
	// return "success";
	// }

	@RequestMapping(value = "/a")
	public String a(
			@RequestParam("identificationCard") String identificationCard)
			throws Exception {

		Video.startRecording(1, 2, identificationCard);
		return "success";
	}

	@RequestMapping(value = "/b")
	public String b(
			@RequestParam("identificationCard") String identificationCard)
			throws Exception {

		Video.pauseRecording(1, 2, identificationCard);
		return "success";
	}

	@RequestMapping(value = "/c")
	public String c(
			@RequestParam("identificationCard") String identificationCard)
			throws Exception {

		Video.restartRecording(1, 2, identificationCard);
		return "success";
	}

	@RequestMapping(value = "/d")
	public String d(
			@RequestParam("identificationCard") String identificationCard)
			throws Exception {

		Video.stopRecording(1, 2, identificationCard);
		return "success";
	}

}
