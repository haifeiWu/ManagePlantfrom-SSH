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
	public static String setFtpServerCfg(int band_ID, String identificationCard)
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
				PropertiesReadUtils.getRecordConfString("Sx"), json);
		System.out.println("请求ftp服务器 开始上传----结果----------------" + result);
		return result;
	}

	/**
	 * 配置远程服务器
	 * 
	 * */
	public static void setRBServerCfg() throws Exception {
		String configResult = HttpRequest.sendOkMCVPost(
				PropertiesReadUtils.getRecordConfString("SxSetWebServerCfg"),
				rbsPackjson());
		System.out.println("配置远程服务器----结果----------------" + configResult);

	}

	/**
	 * 查询文件上传状态
	 * 
	 * @param band_ID
	 * @param identificationCard
	 * @return
	 * @throws IOException
	 */
	public static boolean queryDownloadFileStatu(int band_ID,
			String identificationCard) throws IOException {
		String json = packjson(band_ID, identificationCard);
		String result = "";
		Object code = "";

		for (int i = 1; i <= 3; i++) {
			result = HttpRequest.sendOkMCVPost(PropertiesReadUtils
					.getRecordConfString("SxQueryUploadFileStatus"), json);
			Map<String, Object> str = (Map<String, Object>) JSON.parse(result);
			code = str.get("code");
			if (code.equals(200)) {
				System.out.println("查询上传文件状态----结果----------------" + result);
				// 将结果解析出来，并将文件名和上传完成状态保存
				// 周鑫

				break;
			} else if (i == 3) {
				throw new IOException("调用SxQueryUploadFileStatus的指令失败，"
						+ "错误代码：" + result);
			}
		}
		return false;
	}

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
	public static String switchRecording(int band_ID,
			String identificationCard, int roomId) throws IOException {
		String result = "";
		String json = packjson(band_ID, identificationCard, roomId);
		for (int i = 1; i <= 3; i++) {
			result = HttpRequest.sendOkMCVPost(
					PropertiesReadUtils.getRecordConfString("SwitchRecording"),
					json);
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
	 * */
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
