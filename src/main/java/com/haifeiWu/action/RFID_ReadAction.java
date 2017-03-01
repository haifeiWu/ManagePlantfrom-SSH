package com.haifeiWu.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.haifeiWu.dao.BandInforDao;
import com.haifeiWu.dao.RoomInforDao;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.HttpRequest;
import com.haifeiWu.utils.PropertiesReadUtils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 树莓派与web服务器的接口
 * @author wuhaifei
 * @d2016年9月7日
 */
@Controller
@Scope("prototype")
public class RFID_ReadAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;
	private static String oldDeviceId = null;//设备号
	public static boolean isEmpty = true;// 初始时房间为空
	public static boolean isRecording = false;// 是否处于录像状态，初始状态为否
	private int roomId = 0;
	private int bandId = 0;// 手环id
	private int suspect_ID=0;//嫌疑人id
	private String identificationCard = "wuhaifei1230343";

	@Autowired
	private SuspectService suspectService;
	@Autowired
	private RoomInforDao roomInforDao;// 查询房间号的dao
	@Autowired
	private BandInforDao bandInforDao;

	public String readRFID() throws IOException {

		String deviceId = request.getParameter("deviceId");// 设备号
		String wristId = request.getParameter("wristId");// 手环id
		/**
		 * 读卡，发送录像指令，webSocket刷新页面（注意只有开始指令刷新），几乎是同时进行的，
		 */
//		DateTimeFormatter format = DateTimeFormat
//				.forPattern("yyyy-MM-dd HH:mm:ss");
//		DateTime endTime = DateTime.parse(
//				PropertiesReadUtils.getString("time"), format);//endtime
//		DateTime startTime = new DateTime();
//		int hours = Hours.hoursBetween(startTime, endTime).getHours();
//		/**
//		 * 获取嫌疑人ID，判断录像状态，调用录像指令
//		 * 更新其房间号，ProcessID，
//		 * 
//		 */
//		if (hours > 2) {// 正常状态
//
//			Map<String, String> map = new HashMap<String, String>();//存放的是设备ID和身份证号
//			map.put("policeId", deviceId);//设备ID
//			map.put("identificationCard", identificationCard);//身份证号
//
//			Map<String, Object> map1 = new HashMap<String, Object>();//enableMp 0    showMpMode 0    subPicInfo  map2
//			Map<String, String> map2 = new HashMap<String, String>();//expandMode 0   input 4.1/4.2/4.3
//			map1.put("enableMp", "0");
//			map1.put("showMpMode", "0");
//
//			map2.put("expandMode", "0");
//
//			if (RFID_ReadAction.isEmpty == true) {// 如果房间为空，按照之前的版本，就是第一个流程，
//				RFID_ReadAction.isEmpty = false;
//				// 查找房间号
//				roomId = roomInforDao.findRoomIDByCardReaderID(deviceId);
//				//roomId = roomInforDao.findRoomIDByDeviceId(deviceId);
//				// 查找手环id
//				bandId = bandInforDao.findBandIdByWristId(wristId);
//				// 将房间的编号放入的session域中
//				oldDeviceId = deviceId;
//				if (roomId == 1) {// 如果房间号为1，则……
//					// 更新嫌疑人所在的房间
//					suspectService.updateSuspectInforByBandId(bandId, roomId);
//					PHCSMP_Suspect person = suspectService.findByBandID(bandId);
//					map2.put("input", "4.1");
//					map1.put("subPicInfo", map2);
//					String json2 = JSON.toJSONString(map1);// 封装json字符串，使用fastjson    将map1封装成json字符
//					json2 = json2.substring(0, 31) + "["
//							+ json2.substring(31, 63) + "]"
//							+ json2.substring(63, 79);// 封装json字符串，使用fastjson   json2是激活摄像头的
//					// 激活第一个摄像头
//					String str1 = HttpRequest.sendOkMCVPost(
//							PropertiesReadUtils.getString("SetSplitType"),
//							json2);//str1是请求激活摄像头的响应
//					System.out.println("激活第一个摄像头" + str1);
//					response.getWriter().write("first room:" + str1);
//					// 打印身份证号
//					System.out.println("身份证号："
//							+ person.getIdentifyCard_Number());
//					if (person.getIdentifyCard_Number() != null) {// 开始录制视频
//						System.out.println(person.getIdentifyCard_Number());
//						// 将身份号放入map中
//						map.put("identificationCard",
//								person.getIdentifyCard_Number());
//						String json = JSON.toJSONString(map);
//						str1 = HttpRequest
//								.sendOkMCVPost(PropertiesReadUtils
//										.getString("StartRecording"), json);//发送开始录像请求,并获取响应
//						response.getWriter().write("start record:" + str1);
//						System.out.println("开始录像：" + str1);
//						isRecording = true;
//					}
//				}//房间号不为1
//			} else {//房间不为空
//				if (wristId.equals("FFFFFFFF") && roomId == 4) {// 当嫌疑出区后，自动停止录播系统录像
//					// 发停止录像指令
//					String str = HttpRequest.sendOkMCVPost(
//							PropertiesReadUtils.getString("StopRecording"),
//							null);
//					System.out.println("发停止录像指令：" + str);
//					RFID_ReadAction.isEmpty = true;// 释放房间
//					return "stopRecord";// 停止录像
//				}
//				if (!oldDeviceId.equals(deviceId)) {// 如果设备号发生改变
//					RFID_ReadAction.oldDeviceId = deviceId;// 更新设备号
//					// 根据设备id查找房间id，然后根据房间id，向录播设备发送信息开启
//					roomId = roomInforDao.findRoomIDByCardReaderID(deviceId);
//					bandId = bandInforDao.findBandIdByWristId(wristId);
//					// 相应房间的设备开始录像
//					isRecording = true;
//					if (roomId == 2) {
//						// 更新嫌疑人所在的房间
//						suspectService.updateSuspectInforByBandId(bandId,
//								roomId);
//						map2.put("input", "4.2");
//						map1.put("subPicInfo", map2);
//						String json2 = JSON.toJSONString(map1);
//						json2 = json2.substring(0, 31) + "["
//								+ json2.substring(31, 63) + "]"
//								+ json2.substring(63, 79);
//						String str1 = HttpRequest.sendOkMCVPost(
//								PropertiesReadUtils.getString("SetSplitType"),
//								json2);
//						System.out.println("第二个房间开始录像：" + str1);
//					} else if (roomId == 3) {
//						// 更新嫌疑人所在的房间
//						suspectService.updateSuspectInforByBandId(bandId,
//								roomId);
//
//						map2.put("input", "4.3");
//						map1.put("subPicInfo", map2);
//						String json2 = JSON.toJSONString(map1);
//						json2 = json2.substring(0, 31) + "["
//								+ json2.substring(31, 63) + "]"
//								+ json2.substring(63, 79);
//						String str1 = HttpRequest.sendOkMCVPost(
//								PropertiesReadUtils.getString("SetSplitType"),
//								json2);
//						System.out.println("第三个房间开始录像：" + str1);
//					} else if (roomId == 4) {
//						// 更新嫌疑人所在的房间
//						suspectService.updateSuspectInforByBandId(bandId,
//								roomId);
//						map2.put("input", "4.2");
//						map1.put("subPicInfo", map2);
//						String json2 = JSON.toJSONString(map1);
//						json2 = json2.substring(0, 31) + "["
//								+ json2.substring(31, 63) + "]"
//								+ json2.substring(63, 79);
//						String str1 = HttpRequest.sendOkMCVPost(
//								PropertiesReadUtils.getString("SetSplitType"),
//								json2);
//						System.out.println("第四个房间开始录像：" + str1);
//					}
//				} else {// 设备号未发生变化，说明还在同一房间进行了刷卡。
//					// 则有两种情况：1：处于正在录像状态，需发送暂停录像指令；2：处于暂停录像状态，需发重新开始录像指令
//					if (isRecording == true) {
//						isRecording = false;
//						// 发送暂停录像指令
//						String str1 = HttpRequest
//								.sendOkMCVPost(PropertiesReadUtils
//										.getString("PauseRecording"), null);
//						// 打印返回的状态码
//						System.out.println("暂停录像:" + str1);
//					} else {
//						// 发送重新开始录像指令
//						isRecording = true;
//						String str1 = HttpRequest.sendOkMCVPost(
//								PropertiesReadUtils
//										.getString("RestartRecording"), null);
//						// 打印返回的状态码
//						System.out.println("重新开始录像:" + str1);
//					}
//				}
//			}
			return "operateSucess";// 操作成功
//		} else {// 设备出现故障
//			return "operateSucess";// 操作成功
//		}
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
