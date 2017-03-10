package com.haifeiWu.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.entity.PHCSMP_Room;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.Video;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 树莓派与web服务器的接口
 * 
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

	// private static String oldDeviceId = null;//设备号
	// public static boolean isEmpty = true;// 初始时房间为空
	// public static boolean isRecording = false;// 是否处于录像状态，初始状态为否
	// private int roomId = 0;
	// private int bandId = 0;// 手环id
	// private int suspect_ID=0;//嫌疑人id
	// private String identificationCard = "wuhaifei1230343";

	@Autowired
	private SuspectService suspectService;

	@Autowired
	private RoomService roomService;// 查询房间号的dao

	// @Autowired
	// private BandInforDao bandInforDao;
	// public String testSwitch() throws IOException {
	// Video.switchRecording(1, "1", 1);
	// return "ok";
	// }

	public String readRFID() throws IOException {
		// 获取BandID和CardReader_ID
		int cardReader_ID = Integer.parseInt(request.getParameter("deviceId"));// 设备号
		int bandId = Integer.parseInt(request.getParameter("wristId"));//
		// 手环id
		// 通过获取的属性获取嫌疑人当前信息和所在房间的信息
		PHCSMP_Suspect suspect = suspectService.findByBandID(bandId);
		PHCSMP_Room room = roomService.findByCardReaderID(cardReader_ID);
		// 调用录像,并更新录像状态(判断)// 更新嫌疑人信息，房间号、流程号
		VedioCommandAndUpdateMessage(suspect, room);
		// 调用websocket，干嘛用，并没啥用，作用就是像当前房间对应页面自动刷新页面

		return "operateSucess";// 操作成功
	}

	// 0 / 1 的 切 换 在业务逻辑中完成，只需判断
	/**
	 * 根据新需求更改，根据房间号和Record_Status switch和
	 * RoomID改，录像状态的切换，（0/1入区时，1->2RFID中，3结束LeaveAction）
	 * 
	 * @param suspect
	 * @param room
	 * @throws IOException
	 */
	private void VedioCommandAndUpdateMessage(PHCSMP_Suspect suspect,
			PHCSMP_Room room) throws IOException {
		// try {
		if (suspect.getRecordVideo_State() != 0) {// 如果是0，也要进行相应的更新等操作
			if (suspect.getRecordVideo_State() == 1) {// 开始录像指令，置2
				Video.startRecording(room.getCardReader_ID(),
						room.getLine_Number(), suspect.getIdentifyCard_Number());
				suspectService.updateSuspect(room.getRoom_ID(),
						room.getProcess_ID(), 2, suspect.getSuspect_ID());
				// update(suspect);
			} else {// 录像状态2
				if (suspect.getCardReader_Switch() == 0) {// 首次进入一个房间，或者又进入同一房间
					Video.restartRecording(room.getCardReader_ID(),
							room.getLine_Number(),
							suspect.getIdentifyCard_Number());
					suspectService.updateSuspect(room.getRoom_ID(),
							room.getProcess_ID(), suspect.getSuspect_ID());
					// suspect.setRecordVideo_State(2);
					// update(suspect);
				} else {// 发暂停指令,不更新信息
					String result = Video.pauseRecording(
							room.getCardReader_ID(), room.getLine_Number(),
							suspect.getIdentifyCard_Number());
					System.out
							.println("----------------->暂停录像的结果：---" + result);
				}
			}
		} else {// 状态为0，进的时候更新，出的时候不更新
			if (suspect.getCardReader_Switch() == 0) {
				suspectService.updateSuspect(room.getRoom_ID(),
						room.getProcess_ID(), suspect.getSuspect_ID());
			}
		}
		// } catch (Exception e) {
		// System.out.println(e.getMessage());
		// }

	}

	// private void updateSuspect(PHCSMP_Suspect suspect, int roomID, int
	// processID) {
	// suspect.setRoom_Now(roomID);
	// suspect.setProcess_Now(processID);
	// }

	// private void update(PHCSMP_Suspect suspect) {
	// suspectService.updateSuspect(suspect);
	// }

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

	// private void VedioCommandAndUpdateMessage(PHCSMP_Suspect suspect,
	// PHCSMP_Room room) throws IOException {
	// if (suspect.getRecordVideo_State() != 0) {
	// if (suspect.getRecordVideo_State() == 1) {// 开始录像指令，置2
	// Video.startRecording(room.getCardReader_ID(),
	// room.getRoom_ID(), suspect.getIdentifyCard_Number());
	// updateSuspect(suspect, room.getRoom_ID(), room.getProcess_ID(),
	// 2);
	// } else if (suspect.getRecordVideo_State() == 2) {
	// // 房间号与之前一致,录像状态2，发暂停指令，置3
	// if (suspect.getRoom_Now() == room.getRoom_ID()) {
	// Video.pauseRecording(room.getCardReader_ID(),
	// room.getRoom_ID(), suspect.getIdentifyCard_Number());
	// updateSuspect(suspect, room.getRoom_ID(),
	// room.getProcess_ID(), 3);
	// } else {// 房间号与之前不一致，发重新开始指令，置2
	// Video.restartRecording(room.getCardReader_ID(),
	// room.getRoom_ID(), suspect.getIdentifyCard_Number());
	// updateSuspect(suspect, room.getRoom_ID(),
	// room.getProcess_ID(), 2);
	// }
	// } else {// 这时录像状态为3
	// // 房间号与之前一致,发重新开始指令，置2
	// // 房间号与之前不一致，发重新开始指令，置2
	// Video.restartRecording(room.getCardReader_ID(),
	// room.getRoom_ID(), suspect.getIdentifyCard_Number());
	// updateSuspect(suspect, room.getRoom_ID(), room.getProcess_ID(),
	// 2);
	// }
	// }
	// }
}
