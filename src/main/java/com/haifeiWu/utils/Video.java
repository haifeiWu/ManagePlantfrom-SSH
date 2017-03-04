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

	// @Autowired
	// private SuspectService suspectService;
	// @Autowired
	// private RoomService roomService;

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
			result = HttpRequest.sendOkMCVPost(
					PropertiesReadUtils.getString(command), json);
		}
		return result;
	}

	/**
	 * 切换录制源，不对外暴露
	 * 
	 * @return
	 * @throws IOException
	 */
	private static String switchRecording(int cardReader_ID,
			String identificationCard, int roomId) throws IOException {
		String result = "";
		String json = packjson(cardReader_ID, identificationCard, roomId);
		result = HttpRequest.sendOkMCVPost(
				PropertiesReadUtils.getString("switchRecording"), json);
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
