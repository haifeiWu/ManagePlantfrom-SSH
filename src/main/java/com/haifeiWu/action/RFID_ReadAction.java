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

	public String readRFID() throws IOException {
		// 获取BandID和CardReader_ID
		int cardReader_ID = Integer.parseInt(request.getParameter("deviceId"));// 设备号
		int bandId = Integer.parseInt(request.getParameter("wristId"));// 手环id
		// 通过获取的属性获取嫌疑人当前信息和所在房间的信息
		PHCSMP_Suspect suspect = suspectService.findByBandID(bandId);
		PHCSMP_Room room = roomService.findByCardReaderID(cardReader_ID);
		// 调用录像,并更新录像状态(判断)// 更新嫌疑人信息，房间号、流程号
		VedioCommandAndUpdateMessage(suspect, room);
		// 调用websocket，干嘛用，并没啥用，作用就是像当前房间对应页面自动刷新页面
		return null;// 操作成功
	}

	private void VedioCommandAndUpdateMessage(PHCSMP_Suspect suspect,
			PHCSMP_Room room) throws IOException {
		if (suspect.getRecordVideo_State() != 0) {
			if (suspect.getRecordVideo_State() == 1) {// 开始录像指令，置2
				Video.startRecording(room.getCardReader_ID(),
						room.getRoom_ID(), suspect.getIdentifyCard_Number());
				updateSuspect(suspect, room.getRoom_ID(), room.getProcess_ID(),
						2);
			} else if (suspect.getRecordVideo_State() == 2) {
				// 房间号与之前一致,录像状态2，发暂停指令，置3
				if (suspect.getRoom_Now() == room.getRoom_ID()) {
					Video.pauseRecording(room.getCardReader_ID(),
							room.getRoom_ID(), suspect.getIdentifyCard_Number());
					updateSuspect(suspect, room.getRoom_ID(),
							room.getProcess_ID(), 3);
				} else {// 房间号与之前不一致，发重新开始指令，置2
					Video.restartRecording(room.getCardReader_ID(),
							room.getRoom_ID(), suspect.getIdentifyCard_Number());
					updateSuspect(suspect, room.getRoom_ID(),
							room.getProcess_ID(), 2);
				}
			} else {// 这时录像状态为3
				// 房间号与之前一致,发重新开始指令，置2
				// 房间号与之前不一致，发重新开始指令，置2
				Video.restartRecording(room.getCardReader_ID(),
						room.getRoom_ID(), suspect.getIdentifyCard_Number());
				updateSuspect(suspect, room.getRoom_ID(), room.getProcess_ID(),
						2);
			}
		}
	}

	private void updateSuspect(PHCSMP_Suspect suspect, int roomID,
			int processID, int recordVideo_State) {
		suspect.setRoom_Now(roomID);
		suspect.setProcess_Now(processID);
		suspect.setRecordVideo_State(recordVideo_State);
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
