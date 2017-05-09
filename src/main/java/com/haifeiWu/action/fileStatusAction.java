package com.haifeiWu.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.BandService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.Video;

/**
 * 录波器上传结束接口
 * 
 */

@Controller
@Scope("prototype")
public class fileStatusAction {

	private HttpServletRequest request;
	// 嫌疑人信息
	@Autowired
	private SuspectService suspectService;
	@Autowired
	private BandService bandService;
	// 活动记录实例类
	@Autowired
	private ActivityRecordService activityRecordService;

	@RequestMapping(value = "/fileStatus")
	public String fileStatus(HttpServletRequest request) throws IOException {
		this.request = request;
		String filename = null;
		JSONObject jsonRequest = JSONObject.parseObject(this.getJsonData());
		// 获取json中参数
		int uploadType = Integer.parseInt(jsonRequest.getString("uploadType"));
		int policeId = Integer.parseInt(jsonRequest.getString("policeId"));
		String identificationCard = jsonRequest.getString("identificationCard");
		System.out.println("fileStatus收到的数据 ----------- " + uploadType
				+ "     " + policeId + "     " + identificationCard);

		if (uploadType == 0) {// 注意对下载失败的处理
			// 查询文件上传状态
			filename = Video.queryDownloadFileStatu(policeId,
					identificationCard);
			System.out.println("--------------->" + filename);
			/*
			 * 注解 如果上传成功的话，filename返回String类型的视频名字 如果上传失败，filename返回String类型的0
			 */
			if (!((filename == null) || filename.equals(""))) {// 成功
				String suspectId = suspectService.findByidentifyCard_Number(
						identificationCard).getSuspect_ID();
				suspectService.updatevedio_Number(filename, suspectId);
				suspectService.updateIs_RecordVideo_DownLoad(1, policeId,
						identificationCard);
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
}
