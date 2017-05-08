package com.haifeiWu.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class Video {
	/**
	 * 配置ftp服务器的参数
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String setFtpServerCfg(int Band_ID, String IdentifyCard_Number)
			throws Exception {
		// 配置FTP服务器的参数
		String configJson = packjson();
		String result = HttpRequest.sendOkMCVPost(
				PropertiesReadUtils.getRecordConfString("SxSetFtpServerCfg"),
				configJson);
		System.out.println("配置FTP服务器的----结果----------------" + result);
		return result;
	}

	/**
	 * 请求ftp服务器，上传指定id和身份信息的人员的录制文件
	 * 
	 * @param band_ID
	 * @param identificationCard
	 * @throws Exception
	 */
	public static String uploadRecFile(int band_ID, String identificationCard)
			throws Exception {
		String json = packjson(band_ID, identificationCard);
		String result = HttpRequest.sendOkMCVPost(
				PropertiesReadUtils.getRecordConfString("SxUploadRecFile"),
				json);
		System.out.println("请求ftp服务器 开始上传----结果----------------" + result);
		return result;
	}

	/**
	 * 配置远程服务器
	 * 
	 */
	public static void setRBServerCfg() throws Exception {
		String configResult = HttpRequest.sendOkMCVPost(
				PropertiesReadUtils.getRecordConfString("SxSetWebServerCfg"),
				rbsPackjson());
		System.out.println("配置远程服务器----结果----------------" + configResult);

	}

	/**
	 * 查询文件上传状态，上传成功，上传失败
	 * 
	 * @param band_ID
	 * @param identificationCard
	 * @return
	 * @throws IOException
	 */
	public static String queryDownloadFileStatu(int band_ID,
			String identificationCard) throws IOException {
		String json = packjson(band_ID, identificationCard);
		// String result = "";
		// Object code = "";
		System.out.println("-------dsdd-----"
				+ PropertiesReadUtils
						.getRecordConfString("SxQueryUploadFileStatus"));
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String result = HttpRequest.sendOkMCVPost(PropertiesReadUtils
				.getRecordConfString("SxQueryUploadFileStatus"), json);
		System.out.println(result);
		return getSuccessFile(result);
	}

	@SuppressWarnings("unchecked")
	private static String getSuccessFile(String result) {
		// List resultList = new ArrayList();
		String videonumber = null;
		try {
			// 此处一系列代码皆为脱壳，为的是获取录像编号
			// 获取result的map
			/*
			 * key:code value:200 key:data value:{"failFileList":[{"fileName":
			 * "1_140411199408210451_20170317090935_0100.MP4"
			 * },{"fileName":"1_140411199408210451_20170317082317_0100.MP4" }],
			 * "sucFileList":[]} key:errMsg value:Operate success.
			 */
			Map<String, Object> str = (Map<String, Object>) JSON.parse(result);
			Iterator<?> it1 = str.entrySet().iterator();
			/* 遍历 */
			while (it1.hasNext()) {
				/* 从迭代器中获取一个entry对象 */
				Entry<?, ?> entry = (Entry<?, ?>) it1.next();
				/* 通过entry.getKey()的方法获取key值 */
				System.out.println("key:" + entry.getKey());
				/* 通过entry.getValue()的方法获取value值 */
				System.out.println("value:" + entry.getValue());
			}
			/*
			 * 次处为data的map key:failFileList
			 * value:[{"fileName":"1_140411199408210451_20170317090935_0100.MP4"
			 * }, {"fileName":"1_140411199408210451_20170317082317_0100.MP4"}]
			 * key:sucFileList value:[]
			 */
			Map<String, Object> data = (Map<String, Object>) str.get("data");
			Iterator<?> it = data.entrySet().iterator();
			// 局部变量
			Object value = null;
			Object[] true_obj = null;
			/* 遍历 */
			while (it.hasNext()) {
				/* 从迭代器中获取一个entry对象 */
				Entry<?, ?> entry = (Entry<?, ?>) it.next();
				/* 通过entry.getKey()的方法获取key值 */
				System.out.println("key:" + entry.getKey());
				/* 通过entry.getValue()的方法获取value值 */
				if (entry.getKey().equals("sucFileList")) {
					System.out.println("value:" + entry.getValue());
					value = entry.getValue();
					true_obj = getJsonToArray(value.toString());
				}
			}
			/**
			 * 从json数组中得到相应java数组 JSONArray下的toArray()方法的使用
			 */
			/*
			 * [{"fileName":"1_140411199408210451_20170317090935_0100.MP4"}
			 * {"fileName":"1_140411199408210451_20170317082317_0100.MP4"}]
			 */
			Map<String, Object> ob1 = null;
			if (true_obj.length != 0) {
				for (int i = 0; i < true_obj.length; i++) {
					System.out.println(true_obj[i]);
					ob1 = (Map<String, Object>) true_obj[i];
				}
				for (Object s : ob1.values()) {
					System.out.println(s);
					videonumber = s.toString();
				}
			} else {
				videonumber = "";
			}
		} catch (Exception e) {
			System.out.println("video的getSuccessFile异常");
			videonumber = "";
		}

		return videonumber;

	}

	public static Object[] getJsonToArray(String str) {
		JSONArray jsonArray = JSONArray.parseArray(str);
		return jsonArray.toArray();
	}

	// String videonumber = null;
	// 此处一系列代码皆为脱壳，为的是获取录像编号

	// 获取result的map
	/*
	 * key:code value:200 key:data value:{"failFileList":[{"fileName":
	 * "1_140411199408210451_20170317090935_0100.MP4"
	 * },{"fileName":"1_140411199408210451_20170317082317_0100.MP4"
	 * }],"sucFileList":[]} key:errMsg value:Operate success.
	 */
	// Map<String, Object> str = (Map<String, Object>) JSON.parse(result);
	// Iterator it1 = str.entrySet().iterator();
	// /* 遍历 */
	// while (it1.hasNext()) {
	// /* 从迭代器中获取一个entry对象 */
	// Entry entry = (Entry) it1.next();
	// }

	/*
	 * 次处为data的map key:failFileList
	 * value:[{"fileName":"1_140411199408210451_20170317090935_0100.MP4"
	 * },{"fileName":"1_140411199408210451_20170317082317_0100.MP4"}]
	 * key:sucFileList value:[]
	 */
	// Map<String, Object> data = (Map<String, Object>) str.get("data");
	// Iterator it = data.entrySet().iterator();
	//
	// Object value = null;
	// /* 遍历 */
	// while (it.hasNext()) {
	// /* 从迭代器中获取一个entry对象 */
	// Entry entry = (Entry) it.next();
	// /* 通过entry.getKey()的方法获取key值 */
	// if (entry.getKey().equals("failFileList")) {
	// System.out.println("key:" + entry.getKey());
	// /* 通过entry.getValue()的方法获取value值 */
	// System.out.println("value:" + entry.getValue());
	// value = entry.getValue();
	// } else {
	// //
	// System.out.println("key:" + entry.getKey());
	// /* 通过entry.getValue()的方法获取value值 */
	// System.out.println("value:" + entry.getValue());
	// }
	// }
	// System.out.println(value);
	//
	// /**
	// * 从json数组中得到相应java数组
	// *
	// * JSONArray下的toArray()方法的使用
	// * */
	// /*
	// * {"fileName":"1_140411199408210451_20170317090935_0100.MP4"}
	// * {"fileName":"1_140411199408210451_20170317082317_0100.MP4"}
	// */
	// Map<String, Object> ob1 = null;
	// Object[] obj = getJsonToArray(value.toString());
	// for (int i = 0; i < obj.length; i++) {
	// System.out.println(obj[i]);
	// // 头一个为最近录取录像编号
	// ob1 = (Map<String, Object>) obj[0];
	// }
	// Iterator it3 = ob1.entrySet().iterator();
	// /* 遍历 */
	// while (it3.hasNext()) {
	// /* 从迭代器中获取一个entry对象 */
	// Entry entry = (Entry) it3.next();
	// /* 通过entry.getKey()的方法获取key值 */
	// System.out.println("key:" + entry.getKey());
	// /* 通过entry.getValue()的方法获取value值 */
	// System.out.println("value:" + entry.getValue());
	// // 直接获得------------1_140411199408210451_20170317090935_0100.MP4
	// videonumber = (String) entry.getValue();
	// }
	// return videonumber;
	// for (int i = 1; i <= 3; i++) {
	//
	// Map<String, Object> str = (Map<String, Object>) JSON.parse(result);
	// code = str.get("code");
	// if (code.equals(200)) {
	// System.out.println("查询上传文件状态----结果----------------" + result);
	// // 将结果解析出来，并将文件名和上传完成状态保存
	// // 周鑫
	//
	// break;
	// } else if (i == 3) {
	// throw new IOException("调用SxQueryUploadFileStatus的指令失败，"
	// + "错误代码：" + result);
	// }
	// }

	// private static// public static boolean queryDownloadFileStatu(int
	// band_ID,
	// String identificationCard) throws IOException {
	// String json = packjson(band_ID, identificationCard);
	// String result = "";
	// Object code = "";
	//
	// for (int i = 1; i <= 3; i++) {
	// result = HttpRequest.sendOkMCVPost(PropertiesReadUtils
	// .getRecordConfString("SxQueryUploadFileStatus"), json);
	// Map<String, Object> str = (Map<String, Object>) JSON.parse(result);
	// code = str.get("code");
	// if (code.equals(200)) {
	// System.out.println("查询上传文件状态----结果----------------" + result);
	// // 将结果解析出来，并将文件名和上传完成状态保存
	// // 周鑫
	//
	//
	// break;
	// } else if (i == 3) {
	// throw new IOException("调用SxQueryUploadFileStatus的指令失败，"
	// + "错误代码：" + result);
	// }
	// }
	// return false;
	// }
	public static String startRecording(int band_ID, int room_ID,
			String identificationCard) throws IOException {
		return videoSupport(band_ID, identificationCard, room_ID,
				"StartRecording");
	}

	public static String stopRecording(int band_ID, int room_ID,
			String identificationCard) throws IOException {
		return videoSupport(band_ID, identificationCard, room_ID,
				"StopRecording");
	}

	public static String pauseRecording(int band_ID, int room_ID,
			String identificationCard) throws IOException {
		return videoSupport(band_ID, identificationCard, room_ID,
				"PauseRecording");
	}

	public static String restartRecording(int band_ID, int room_ID,
			String identificationCard) throws IOException {
		return videoSupport(band_ID, identificationCard, room_ID,
				"RestartRecording");
	}

	private static String videoSupport(int band_ID, String identificationCard,
			int room_ID, String command) throws IOException {
		String result = "";
		if (isValid()) {
			String json = packjson(band_ID, identificationCard);// 封装json数据

			if (command.equals("StartRecording")
					|| command.equals("RestartRecording")) {// 切换录制源
				result = switchRecording(band_ID, identificationCard, room_ID);
			}
			// 调用相应的录像指令
			for (int i = 1; i <= 3; i++) {
				result = HttpRequest.sendOkMCVPost(
						PropertiesReadUtils.getRecordConfString(command), json);
				@SuppressWarnings("unchecked")
				Map<String, Object> str = (Map<String, Object>) JSON
						.parse(result);
				if (str.get("code").equals(200))
					break;
				else if (i == 3) {
					throw new IOException("调用" + command + "的指令失败，" + "错误代码："
							+ result);
				}
			}
		}
		return result;
	}

	/**
	 * 切换录制源，不对外暴露
	 * 
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static String switchRecording(int band_ID,
			String identificationCard, int roomId) throws IOException {
		String result = "";
		String json = packjson(band_ID, identificationCard, roomId);
		System.out.println(band_ID+"    "+identificationCard+"      "+roomId);
		for (int i = 1; i <= 3; i++) {
			result = HttpRequest.sendOkMCVPost(
					PropertiesReadUtils.getRecordConfString("SwitchRecording"),
					json);
			Map<String, Object> str = (Map<String, Object>) JSON.parse(result);
			System.out.println("切换录制源-------------------------->" + str);

			if (str.get("code").equals(200))
				break;
			else if (i == 3) {
				throw new IOException("调用切换录制源的指令失败" + result);
			}
		}
		return result;
	}

	/**
	 * 配置FTP服务器的参数
	 * 
	 * @return
	 */
	private static String packjson() {
		Map<String, Object> map = new HashMap<String, Object>();// 存放的是设备ID和身份证号
		map.put("serverIp", PropertiesReadUtils.getRecordConfString("serverIp"));
		map.put("port", PropertiesReadUtils.getRecordConfString("ftpPort"));
		map.put("uploadDir",
				PropertiesReadUtils.getRecordConfString("uploadDir"));
		map.put("userName", PropertiesReadUtils.getRecordConfString("userName"));
		map.put("passWord", PropertiesReadUtils.getRecordConfString("passWord"));
		String json = JSON.toJSONString(map);
		return json;
	}

	/**
	 * 配置远程服务器参数
	 */
	private static String rbsPackjson() {
		Map<String, Object> map = new HashMap<String, Object>();// 存放的是设备ID和身份证号
		map.put("serverIp", PropertiesReadUtils.getRecordConfString("serverIp"));
		map.put("port", PropertiesReadUtils.getRecordConfString("serverPort"));
		map.put("url", PropertiesReadUtils.getRecordConfString("url"));
		String json = JSON.toJSONString(map);
		return json;
	}

	/**
	 * 封装json
	 * 
	 * @param cardReader_ID
	 * @param identificationCard
	 * @return
	 */
	private static String packjson(int band_ID, String identificationCard) {

		Map<String, Object> map = new HashMap<String, Object>();// 存放的是设备ID和身份证号
		map.put("policeId", band_ID);// 设备ID
		map.put("identificationCard", identificationCard);// 身份证号
		String json = JSON.toJSONString(map);
		return json;
	}

	/**
	 * 封装json
	 * 
	 * @param cardReader_ID
	 * @param identificationCard
	 * @param roomID
	 * @return
	 */
	private static String packjson(int band_ID, String identificationCard,
			int roomId) {

		Map<String, Object> map = new HashMap<String, Object>();// 存放的是设备ID和身份证号
		map.put("policeId", band_ID);// 设备ID
		map.put("identificationCard", identificationCard);// 身份证号
		map.put("roomId", roomId);// 身份证号
		String json = JSON.toJSONString(map);
		return json;
	}

	/**
	 * 有效则可以调用摄像头，无效则不能调用摄像头
	 * 
	 */
	private static boolean isValid() {
		DateTimeFormatter format = DateTimeFormat
				.forPattern("yyyy-MM-dd HH:mm:ss");
		DateTime endTime = DateTime.parse(
				PropertiesReadUtils.getRecordConfString("time"), format);// endtime
		DateTime startTime = new DateTime();
		int hours = Hours.hoursBetween(startTime, endTime).getHours();
		// 有效则可以调用摄像头，无效则不能调用摄像头
		return hours > 2;
	}
}
