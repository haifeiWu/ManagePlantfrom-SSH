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
	public String fileStatus(HttpServletRequest request) throws IOException,
			InterruptedException {
		long getRequest = System.currentTimeMillis();
		long sendResponse;
		this.request = request;
		JSONObject jsonRequest = JSONObject.parseObject(this.getJsonData());
		// 获取json中参数
		int uploadType = Integer.parseInt(jsonRequest.getString("uploadType"));
		int policeId = Integer.parseInt(jsonRequest.getString("policeId"));
		String identificationCard = jsonRequest.getString("identificationCard");
		request.setAttribute("uploadType", uploadType);
		request.setAttribute("policeId", policeId);
		request.setAttribute("identificationCard", identificationCard);
		System.out
				.println("fileStatus收到的数据 -------------------------------------------------------------------- "
						+ uploadType
						+ "     "
						+ policeId
						+ "     "
						+ identificationCard);
		// 查询
				if (uploadType == 0) {
					String result = Video.queryDownloadFileStatu(policeId,
							identificationCard);
					String filename = Video.getSuccessFile(result);
					// 将fileName存到数据库
					
					suspectService.updateIs_filename_DownLoad(1, policeId,
							identificationCard);
				}

		return "success";
	}

	private void search(int uploadType, int policeId, String identificationCard)
			throws IOException, InterruptedException {
		SearchVedioUploadStatus t = new SearchVedioUploadStatus(uploadType,
				policeId, identificationCard);
		Thread thread = new Thread(t);
		thread.sleep(400);
		thread.start();
	}

	/**
	 * 内部类，新开启一个线程，执行查询
	 * 
	 * @author WXY
	 * 
	 */
	class SearchVedioUploadStatus implements Runnable {
		private int uploadType;
		private int policeId;
		private String identificationCard;

		public SearchVedioUploadStatus(int uploadType, int policeId,
				String identificationCard) {
			super();
			this.uploadType = uploadType;
			this.policeId = policeId;
			this.identificationCard = identificationCard;
		}

		@Override
		public void run() {
			long beginQuery, endQuery, beginParse, endParse;
			if (uploadType == 0) {// 注意对下载失败的处理
				// 查询文件上传状态
				beginQuery = System.currentTimeMillis();
				String result = null;
				try {
					result = Video.queryDownloadFileStatu(policeId,
							identificationCard);
				} catch (IOException e) {
					System.out.println("------------------" + "异常");
					e.printStackTrace();
				}
				endQuery = System.currentTimeMillis();
				System.out.println("开始查询----查询结束          耗时"
						+ (endQuery - beginQuery));
				/*
				 * 注解 如果上传成功的话，filename返回String类型的视频名字
				 * 如果上传失败，filename返回String类型的0
				 */
				beginParse = System.currentTimeMillis();
				String filename = Video.getSuccessFile(result);
				endParse = System.currentTimeMillis();
				System.out.println("开始解析----解析结束          耗时"
						+ (endParse - beginParse));
				if (!(filename == null || filename.equals(""))) {// 成功,注意这里有问题，查询Autowired是否注入成功
					System.out.println("-----" + suspectService);
					String suspectId = suspectService
							.findByidentifyCard_Number(identificationCard)
							.getSuspect_ID();
					suspectService.updatevedio_Number(filename, suspectId);
					suspectService.updateIs_RecordVideo_DownLoad(1, policeId,
							identificationCard);
				}
			}
		}
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
