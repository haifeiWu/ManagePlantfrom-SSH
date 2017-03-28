package com.haifeiWu.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.Video;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 录波器上传结束接口
 * 
 * */

@Controller
@Scope("prototype")
public class fileStatusAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6226713528433803678L;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;
	// 嫌疑人信息
	@Autowired
	private SuspectService suspectService;
	// 活动记录实例类
	@Autowired
	private ActivityRecordService activityRecordService;

	public String fileStatus() throws Exception {
		String uploadType = request.getParameter("UploadType");
		System.out.println(uploadType);
		int cardReader_ID = Integer.parseInt(request
				.getParameter("cardReader_ID"));
		String identificationCard = request.getParameter("identificationCard");

		String videonumber = Video.queryDownloadFileStatu(cardReader_ID,
				identificationCard);
		System.out.println("上传接口查询文件状态");

		//
		// suspectService.updateIs_RecordVideo_DownLoad(1, identificationCard);
		// System.out.println("修改嫌疑人下载位");
		// activityRecordService.updatevedio_Number(videonumber,
		// identificationCard);
		// System.out.println("修改嫌疑人询问音频录像位");
		return "success";
	}

	@Override
	public void setServletContext(ServletContext application) {
		this.application = application;

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}
}
