package com.haifeiWu.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.alibaba.fastjson.JSON;

public class Video {
	/**
	 * 配置ftp服务器的参数
	 * 
	 * @return
	 * @throws Exception
	 */
	public static void setFtpServerCfg(int band_ID, String identificationCard)
			throws Exception {
		// 配置FTP服务器的参数
		String configJson = packjson();
		String configResult = HttpRequest.sendOkMCVPost(
				PropertiesReadUtils.getString("SxSetFtpServerCfg"), configJson);
		System.out.println("配置FTP服务器的----结果----------------" + configResult);
		// 请求ftp服务器，上传指定id和身份信息的人员的录制文件
		String json = packjson(band_ID, identificationCard);
		String result = HttpRequest.sendOkMCVPost(
				PropertiesReadUtils.getString("SxUploadRecFile"), json);
		System.out.println("请求ftp服务器 开始上传----结果----------------" + result);
	}

	/**
	 * 配置远程服务器
	 * 
	 * */
	public static void setRBServerCfg() throws Exception {
		String configJson = RBSpackjson();
		String configResult = HttpRequest.sendOkMCVPost(
				PropertiesReadUtils.getString("SxSetWebServerCfg"), configJson);
		System.out.println("配置远程服务器----结果----------------" + configResult);

	}

	private static String packjson() {
		Map<String, Object> map = new HashMap<String, Object>();// 存放的是设备ID和身份证号
		map.put("serverIp", "192.168.1.161");
		map.put("port", 21);
		map.put("uploadDir", "\\tty");
		map.put("userName", "dell");
		map.put("passWord", "ghjk");
		String json = JSON.toJSONString(map);
		return json;
	}

	/**
	 * 远程服务器参数
	 * */
	private static String RBSpackjson() {
		Map<String, Object> map = new HashMap<String, Object>();// 存放的是设备ID和身份证号
		map.put("serverIp", "192.168.1.161");
		map.put("port", 8888);
		map.put("url", "ManagePlantfrom-SSH/fileStatus.action");
		String json = JSON.toJSONString(map);
		return json;
	}

	public static String queryDownloadFileStatu(int band_ID,
			String identificationCard) throws IOException {
		String json = packjson(band_ID, identificationCard);
		String result = HttpRequest.sendOkMCVPost(
				PropertiesReadUtils.getString("SxQueryUploadFileStatus"), json);
		System.out.println("查询上传文件状态----结果----------------" + result);
		// Map<String, Object> str = (Map<String, Object>) JSON.parse(result);
		// Iterator it1 = str.entrySet().iterator();
		//
		// /* 遍历 */
		// while (it1.hasNext()) {
		// /* 从迭代器中获取一个entry对象 */
		// Entry entry = (Entry) it1.next();
		// /* 通过entry.getKey()的方法获取key值 */
		// System.out.println("key:" + entry.getKey());
		// /* 通过entry.getValue()的方法获取value值 */
		//
		// System.out.println("value:" + entry.getValue());
		//
		// }
		// System.out
		// .println("++++++++++++++++++++++++++++++++++++++++++++++++++");

		// Map<String, Object> M3 = (Map<String, Object>) str.get("data");
		// System.out.println("---------查询文件data----------------" + M3);
		// ArrayList sucFileList = (ArrayList) M3.get("sucFileList");
		// System.out.println("-------------sucFileList----------------"
		// + sucFileList);
		// ArrayList failFileList = (ArrayList) M3.get("failFileList");
		// System.out.println("-------------failFileList----------------"
		// + failFileList);
		// Iterator it = M3.entrySet().iterator();
		//
		// /* 遍历 */
		// while (it.hasNext()) {
		// /* 从迭代器中获取一个entry对象 */
		// Entry entry = (Entry) it.next();
		// /* 通过entry.getKey()的方法获取key值 */
		// System.out
		// .println("-------------------data--key:" + entry.getKey());
		// /* 通过entry.getValue()的方法获取value值 */
		//
		// System.out.println("-------------------data--value:"
		// + entry.getValue());
		//
		// }
		// List<String> videolist = (List<String>) M3.get("value");
		// String videonumber = null;
		// for (String string : videolist) {
		// // videonumber=videonumber+string;
		// System.out.println(string);
		// }
		return "";
	}

	public static String startRecording(int cardReader_ID, int room_ID,
			String identificationCard) throws IOException {
		return videoSupport(cardReader_ID, identificationCard, room_ID,
				"StartRecording");
	}

	public static String stopRecording(int cardReader_ID, int room_ID,
			String identificationCard) throws IOException {
		return videoSupport(cardReader_ID, identificationCard, room_ID,
				"StopRecording");
	}

	public static String pauseRecording(int cardReader_ID, int room_ID,
			String identificationCard) throws IOException {
		return videoSupport(cardReader_ID, identificationCard, room_ID,
				"PauseRecording");
	}

	public static String restartRecording(int cardReader_ID, int room_ID,
			String identificationCard) throws IOException {
		return videoSupport(cardReader_ID, identificationCard, room_ID,
				"RestartRecording");
	}

	private static String videoSupport(int cardReader_ID,
			String identificationCard, int room_ID, String command)
			throws IOException {
		String result = "";
		if (isValid()) {
			String json = packjson(cardReader_ID, identificationCard);// 封装json数据

			if (command.equals("StartRecording")
					|| command.equals("RestartRecording")) {// 切换录制源
				result = switchRecording(cardReader_ID, identificationCard,
						room_ID);
			}
			// 调用相应的录像指令
			for (int i = 1; i <= 3; i++) {
				result = HttpRequest.sendOkMCVPost(
						PropertiesReadUtils.getString(command), json);
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
	public static String switchRecording(int cardReader_ID,
			String identificationCard, int roomId) throws IOException {
		String result = "";
		String json = packjson(cardReader_ID, identificationCard, roomId);
		for (int i = 1; i <= 3; i++) {
			result = HttpRequest.sendOkMCVPost(
					PropertiesReadUtils.getString("SwitchRecording"), json);
			Map<String, Object> str = (Map<String, Object>) JSON.parse(result);
			System.out.println("-------------------------->" + str);

			if (str.get("code").equals(200))
				break;
			else if (i == 3) {
				throw new IOException("调用切换录制源的指令失败" + result);
			}
		}
		return result;
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
	private static String packjson(int cardReader_ID,
			String identificationCard, int roomId) {

		Map<String, Object> map = new HashMap<String, Object>();// 存放的是设备ID和身份证号
		map.put("policeId", cardReader_ID);// 设备ID
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
				PropertiesReadUtils.getString("time"), format);// endtime
		DateTime startTime = new DateTime();
		int hours = Hours.hoursBetween(startTime, endTime).getHours();
		// 有效则可以调用摄像头，无效则不能调用摄像头
		return hours > 2;
	}

}
