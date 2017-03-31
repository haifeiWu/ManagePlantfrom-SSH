package com.haifeiWu.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.BandService;
import com.haifeiWu.service.SuspectService;
import com.opensymphony.xwork2.ActionContext;
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
	@Autowired
	private BandService bandService;
	// 活动记录实例类
	@Autowired
	private ActivityRecordService activityRecordService;

	// String uploadType;
	// String policeId;
	// String identificationCard;

	public String fileStatus() {
		JSONObject jsonRequest = JSONObject.parseObject(this.getStrResponse());
		// 获取json中参数
		String uploadType = jsonRequest.getString("uploadType");
		String policeId = jsonRequest.getString("policeId");
		String identificationCard = jsonRequest.getString("identificationCard");
		System.out.println("getParameter收到的数据 -----------           "
				+ uploadType + "     " + policeId + "     "
				+ identificationCard);
		return "success";
	}

	public String getStrResponse() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		InputStream inputStream;
		String strResponse = "";
		try {
			inputStream = request.getInputStream();
			String strMessage = "";
			BufferedReader reader;
			reader = new BufferedReader(new InputStreamReader(inputStream,
					"utf-8"));
			while ((strMessage = reader.readLine()) != null) {
				strResponse += strMessage;
			}
			reader.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strResponse;
	}

	// System.out.println("fileStatus收到的数据 -----------           "
	// + uploadType + "     " + policeId + "     "
	// + identificationCard);
	// uploadType = (String) request.getParameter("uploadType");
	// policeId = (String) request.getParameter("policeId");
	// identificationCard = (String) request
	// .getParameter("identificationCard");
	// System.out.println("getParameter收到的数据 -----------           "
	// + uploadType + "     " + policeId + "     "
	// + identificationCard);
	// uploadType = (String) request.getAttribute("uploadType");
	// policeId = (String) request.getAttribute("policeId");
	// identificationCard = (String) request
	// .getAttribute("identificationCard");
	//
	// System.out.println("getAttribute收到的数据              ------" +
	// uploadType
	// + "     " + policeId + "     " + identificationCard);

	// 查询下载文件的状态
	// String videonumber = Video.queryDownloadFileStatu(bandService
	// .findByRemark(policeId).getBand_ID(), identificationCard);
	//

	//
	// suspectService.updateIs_RecordVideo_DownLoad(1, identificationCard);
	// System.out.println("修改嫌疑人下载位");
	// activityRecordService.updatevedio_Number(videonumber,
	// identificationCard);
	// System.out.println("修改嫌疑人询问音频录像位");

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

	// public String getUploadType() {
	// return uploadType;
	// }
	//
	// public void setUploadType(String uploadType) {
	// this.uploadType = uploadType;
	// }
	//
	// public String getPoliceId() {
	// return policeId;
	// }
	//
	// public void setPoliceId(String policeId) {
	// this.policeId = policeId;
	// }
	//
	// public String getIdentificationCard() {
	// return identificationCard;
	// }
	//
	// public void setIdentificationCard(String identificationCard) {
	// this.identificationCard = identificationCard;
	// }
}
