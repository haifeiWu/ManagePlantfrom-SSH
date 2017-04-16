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
import com.haifeiWu.utils.Video;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 录波器上传结束接口
 * 
 */

@Controller
@Scope("prototype")
public class fileStatusAction extends ActionSupport
		implements ServletRequestAware, ServletResponseAware, ServletContextAware {

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

	public String fileStatus() throws IOException {
		JSONObject jsonRequest = JSONObject.parseObject(this.getJsonData());
		// 获取json中参数
		int uploadType = Integer.parseInt(jsonRequest.getString("uploadType"));
		int policeId = Integer.parseInt(jsonRequest.getString("policeId"));
		String identificationCard = jsonRequest.getString("identificationCard");
		System.out.println("fileStatus收到的数据 -----------           " + uploadType + "     " + policeId + "     "
				+ identificationCard);
		if (uploadType == 0) {// 注意对下载失败的处理
			// 查询文件上传状态
			String filename = Video.queryDownloadFileStatu(policeId, identificationCard);
			if (!((filename == null) || filename.equals(""))) {// 成功
				activityRecordService.updatevedio_Number(filename, policeId, identificationCard);
				suspectService.updateIs_RecordVideo_DownLoad(1, policeId, identificationCard);
			}
		}
		return "success";
	}

	/**
	 * 解析收到的JSON数据
	 * 
	 * @return
	 */
	private String getJsonData() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		InputStream inputStream;
		String strResponse = "";
		try {
			inputStream = request.getInputStream();
			String strMessage = "";
			BufferedReader reader;
			reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
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
