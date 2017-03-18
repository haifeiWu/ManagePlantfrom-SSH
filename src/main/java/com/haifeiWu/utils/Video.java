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
	public static void setFtpServerCfg(int cardReader_ID,
			String identificationCard) throws Exception {
		String configJson = packjson();
		String configResult = HttpRequest.sendOkMCVPost(
				PropertiesReadUtils.getString("SxSetFtpServerCfg"), configJson);
		System.out.println("----------------" + configResult);
		String json = packjson(cardReader_ID, identificationCard);
		String result = HttpRequest.sendOkMCVPost(
				PropertiesReadUtils.getString("SxUploadRecFile"), json);
		System.out.println("----------------" + result);
		// FTPClientUtils ftp = new FTPClientUtils();
		// ftp.setHost("192.168.1.108");
		// ftp.setPort(21);
		// ftp.setBinaryTransfer(true);
		// ftp.setPassiveMode(true);
		// ftp.setEncoding("utf-8");
		// ftp.setUsername("anonymous");
		// ftp.setPassword("192.168.1.161");

		// 注意并未设置文件的绝对路径
		// FTPClient ftpClient = ftp.getFTPClient();
		// ftp
		// if (ftp.connect(ftpClient)) {

		// File localFile = new File("C:\\Users\\Dell\\Desktop");
		// FileOutputStream fos = new FileOutputStream(localFile);
		//
		// ftpClient.retrieveFile(, fos);
		// System.out.println("download the remote files.");
		// fos.close();

		// }
	}

	private static String packjson() {
		Map<String, Object> map = new HashMap<String, Object>();// 存放的是设备ID和身份证号
		map.put("serverIp", "192.168.1.108");
		map.put("port", 21);
		map.put("uploadDir", "C:\\Users\\Dell\\Desktop");
		map.put("userName", "anonymous");
		map.put("passWord", "192.168.1.161");
		String json = JSON.toJSONString(map);
		return json;
	}

	public static void queryDownloadFileStatu(int cardReader_ID,
			String identificationCard) throws IOException {
		String json = packjson(cardReader_ID, identificationCard);
		String result = HttpRequest.sendOkMCVPost(
				PropertiesReadUtils.getString("SxQueryUploadFileStatus"), json);
		System.out.println("----------------" + result);

	}

	// public static String downloadRecFile(int cardReader_ID,
	// String identificationCard) {
	//
	// return "";
	// }

	// private static String packFtpServerJson() {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("serverIp", "192.168.1.161");
	// map.put("port", "8888");
	// map.put("uploadDir", "C:\\Users\\Dell\\Desktop");
	// map.put("userName", "anonymous");
	// map.put("passWord", "");
	// String json = JSON.toJSONString(map);
	// return json;
	// }

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
	private static String packjson(int cardReader_ID, String identificationCard) {

		Map<String, Object> map = new HashMap<String, Object>();// 存放的是设备ID和身份证号
		map.put("policeId", cardReader_ID);// 设备ID
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
