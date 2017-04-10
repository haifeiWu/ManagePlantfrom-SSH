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
import com.haifeiWu.service.BandService;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.Video;
import com.haifeiWu.utils.WebSocketUtils;
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
	private static WebSocketUtils ws = new WebSocketUtils();

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
	@Autowired
	private BandService bandService;

	// @Autowired
	// private BandInforDao bandInforDao;
	// public String testSwitch() throws IOException {
	// Video.switchRecording(1, "1", 1);
	// return "ok";
	// }

	public String readRFID() throws IOException, InterruptedException {
		// 获取BandID和CardReader_ID
		String cardReader_Name = request.getParameter("deviceId");// 设备号
		String remark = request.getParameter("wristId");
		System.out.println(request.getParameter("deviceId")
				+ "-------------------cardReader_Name-----------");
		System.out.println(request.getParameter("wristId")
				+ "-------------------remark-----------");
		// 通过获取的属性获取嫌疑人当前信息和所在房间的信息
		int bandId = bandService.findByRemark(remark).getBand_ID();
		int cardReader_ID = roomService.findByCardReaderName(cardReader_Name)
				.getCardReader_ID();
		PHCSMP_Suspect suspect = suspectService.findByBandID(bandId);
		PHCSMP_Room room = roomService.findByCardReaderID(cardReader_ID);
		// 调用录像,并更新录像状态(判断)// 更新嫌疑人信息，房间号、流程号
		VedioCommandAndUpdateMessage(suspect, room, bandId);
		// 当前房间对应页面自动刷新页面， 刷新页面的时机，不是侯问室，不是出门刷卡
		if (suspect.getCardReader_Switch() == 0 && room.getProcess_ID() != 4) {
			triggerWebsocket(room, suspect.getSuspect_ID());
		}
		return "operateSucess";// 操作成功
	}

	private void triggerWebsocket(PHCSMP_Room room, String suspectID) {
		System.out.println("triggerWebsocket---------------suspectID="
				+ suspectID);
		// flushpage时传递两个信息，一个是IP，另一个是嫌疑人编号，用*作为分割符
		switch (room.getProcess_ID()) {
		// case 0:// 0是入区登记，不刷卡以及录像
		// ws.flushPage(room.getRoom_IPAddress());
		// break;
		case 1:// 人身检查
			ws.flushPage(room.getRoom_IPAddress() + "*"
					+ "personalCheck_loadInfor.action?suspectID=" + suspectID);
			break;
		case 2:// 信息采集
			ws.flushPage(room.getRoom_IPAddress() + "*"
					+ "IC_loadInfor.action?suspectID=" + suspectID);
			break;
		case 3:// 询问讯问，
			ws.flushPage(room.getRoom_IPAddress() + "*"
					+ "AR_loadInfor.action?suspectID=" + suspectID);
			break;
		// case 4:// 侯问，不刷新页面
		// ws.flushPage("personalCheck" + "&" + room.getRoom_IPAddress());
		// break;
		case 5:// 出区离区
			ws.flushPage(room.getRoom_IPAddress() + "*"
					+ "LR_loadInfor.action?suspectID=" + suspectID);
			break;
		default:
			break;
		}
	}

	// 0 / 1 的 切 换 在业务逻辑中完成，只需判断
	/**
	 * 根据新需求更改，根据房间号和Record_Status switch和
	 * RoomID改，录像状态的切换，（0/1入区时，1->2RFID中，3结束LeaveAction）
	 * 
	 * 刷卡如果不准，会对录像字段更新，或产生误判，
	 * 
	 * @param suspect
	 * @param room
	 * @throws IOException
	 */
	private void VedioCommandAndUpdateMessage(PHCSMP_Suspect suspect,
			PHCSMP_Room room, int bandId) throws IOException {
		// try {
		if (suspect.getRecordVideo_State() != 0) {// 如果是0，也要进行相应的更新等操作
			if (suspect.getRecordVideo_State() == 1) {// 开始录像指令，置2
				String result = Video
						.startRecording(bandId, room.getLine_Number(),
								suspect.getIdentifyCard_Number());
				suspectService.updateSuspect(room.getRoom_ID(),
						room.getProcess_ID(), 2, suspect.getSuspect_ID());
				System.out.println("----------------->调用开始录像的结果：---" + result);
				// update(suspect);
			} else {// 录像状态2
				// 房间号有变化或者标志位为0，开始指令
				if (suspect.getRoom_Now() != room.getRoom_ID()
						|| suspect.getCardReader_Switch() == 0) {// 首次进入一个房间，或者又进入同一房间
					String result = Video.restartRecording(bandId,
							room.getLine_Number(),
							suspect.getIdentifyCard_Number());
					suspectService.updateSuspect(bandId, room.getProcess_ID(),
							suspect.getSuspect_ID());
					System.out.println("----------------->调用重新开始录像的结果：---"
							+ result);
					// suspect.setRecordVideo_State(2);
					// update(suspect);
				} else {// 发暂停指令,更新录像状态位为0
					String result = Video.pauseRecording(bandId,
							room.getLine_Number(),
							suspect.getIdentifyCard_Number());
					System.out.println("----------------->调用暂停录像的结果：---"
							+ result);
					suspectService.updateSwitch(0, suspect.getSuspect_ID());
				}
			}
		} else {// 状态为0，进的时候更新，出的时候不更新
			if (suspect.getRoom_Now() != room.getRoom_ID()
					|| suspect.getCardReader_Switch() == 0) {
				suspectService.updateSuspect(bandId, room.getProcess_ID(),
						suspect.getSuspect_ID());
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
