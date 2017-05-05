package com.haifeiWu.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haifeiWu.entity.PHCSMP_Room;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.BandService;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.Video;
import com.haifeiWu.utils.WebSocketUtils;

/**
 * 树莓派与web服务器的接口
 * 
 * @author wuhaifei
 * @d2016年9月7日
 */
@Controller
@Scope("prototype")
public class RFID_ReadAction {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;
	private static WebSocketUtils ws = new WebSocketUtils();

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;

	@Autowired
	private SuspectService suspectService;

	@Autowired
	private RoomService roomService;// 查询房间号的dao
	@Autowired
	private BandService bandService;

	/**
	 * 这里的异常最好用try catch块处理一下，不然抛出异常的时候就会出现在树莓派上不好处理
	 * 
	 * @author wuhaifei
	 * @d2017年4月16日
	 */
	@RequestMapping(value = "/readRfid.action")
	public String readRFID(HttpServletRequest request) throws IOException {
		/**
		 * 控制设备发出不同的声音的话，应该在这里做一下应该返回的参数，建议用json格式的数据
		 * 
		 * 然后树莓派根据返回参数，通过判断来实现发出不同的声音
		 */

		// 获取BandID和CardReader_ID
		String cardReader_Name = request.getParameter("deviceId");// 设备号
		String remark = request.getParameter("wristId");
		// System.out.println(request.getParameter("deviceId")
		// + "-------------------cardReader_Name-----------");
		// System.out.println(request.getParameter("wristId")
		// + "-------------------remark-----------");

		System.out.println(cardReader_Name + "------cardReader_Name------");
		System.out.println(remark + "--------remark-----------");
		// 通过获取的属性获取嫌疑人当前信息和所在房间的信息
		int bandId = bandService.findByRemark(remark).getBand_ID();
		int cardReader_ID = roomService.findByCardReaderName(cardReader_Name)
				.getCardReader_ID();
		PHCSMP_Suspect suspect = suspectService.findByBandID(bandId);
		request.setAttribute("suspect", suspect);
		PHCSMP_Room room = roomService.findByCardReaderID(cardReader_ID);
		request.setAttribute("room", room);
		/**
		 * 先判断状态，发对应的录像指令，更新房间和流程号/state
		 * 
		 * 再判断是否触发websocket更新页面（排除几个不需要的更新页面的房间）
		 * 
		 * 更改switch
		 */
		// 1.先判断状态，发对应的录像指令，更新房间和流程号/state，对switch进行校准
		VedioCommandAndUpdateMessage(suspect, room, bandId);
		// 2. 再判断是否触发websocket更新页面（排除几个不需要的更新页面的流程）
		if (suspect.getCardReader_Switch() == 0) {
			// 如果流程等于0或4，不触发
			if (!(room.getProcess_ID() == 0 || room.getProcess_ID() == 4)) {
				triggerWebsocket(room, suspect.getSuspect_ID());
			}
		}
		// 3.更改switch
		if (suspect.getCardReader_Switch() == 0) {
			suspectService.updateSwitch(1, suspect.getSuspect_ID());
		} else {
			suspectService.updateSwitch(0, suspect.getSuspect_ID());
		}
		return "operateSucess";// 操作成功
	}

	private void triggerWebsocket(PHCSMP_Room room, String suspectID) {
		System.out.println("triggerWebsocket---------------suspectID="
				+ suspectID);
		// flushpage时传递两个信息，一个是IP，另一个是嫌疑人编号，用*作为分割符
		switch (room.getProcess_ID()) {
		// case 0:// 0是入区登记，不刷卡以及录像
		// break;
		case 1:// 人身检查
			ws.flushPage(room.getRoom_IPAddress() + "*"
					+ "/check/load?suspectID=" + suspectID);
			break;
		case 2:// 信息采集
			ws.flushPage(room.getRoom_IPAddress() + "*"
					+ "collect/load?suspectID=" + suspectID);
			break;
		case 3:// 询问讯问，
			ws.flushPage(room.getRoom_IPAddress() + "*"
					+ "/activity/load?suspectID=" + suspectID);
			break;
		// case 4:// 侯问，不刷新页面
		case 5:// 出区离区
			ws.flushPage(room.getRoom_IPAddress() + "*"
					+ "/leave/load?suspectID=" + suspectID);
			break;
		default:
			break;
		}
	}

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
				// 校准录像状态
				if (suspect.getCardReader_Switch() == 1) {
					repairSwitch(0, suspect.getSuspect_ID());
				}
				System.out.println("----------------->调用开始录像的结果：---" + result);
				// update(suspect);
			} else {// 录像状态2
				// 房间号有变化或者标志位为0，开始指令
				if (suspect.getRoom_Now() != room.getRoom_ID()
						|| suspect.getCardReader_Switch() == 0) {// 首次进入一个房间，或者又进入同一房间
					String result = Video.restartRecording(bandId,
							room.getLine_Number(),
							suspect.getIdentifyCard_Number());

					suspectService.updateSuspect(room.getRoom_ID(),
							room.getProcess_ID(), suspect.getSuspect_ID());
					System.out.println("----------------->调用重新开始录像的结果：---"
							+ result);
					// 校准录像状态
					if (suspect.getCardReader_Switch() == 1) {
						repairSwitch(0, suspect.getSuspect_ID());
					}

				} else {// 发暂停指令,更新录像状态位为0
					String result = Video.pauseRecording(bandId,
							room.getLine_Number(),
							suspect.getIdentifyCard_Number());

					System.out.println("----------------->调用暂停录像的结果：---"
							+ result);

				}
			}
		} else {// 状态为0，进的时候更新，出的时候不更新
			if (suspect.getRoom_Now() != room.getRoom_ID()
					|| suspect.getCardReader_Switch() == 0) {

				suspectService.updateSuspect(room.getRoom_ID(),
						room.getProcess_ID(), suspect.getSuspect_ID());
			}
		}
	}

	private void repairSwitch(int cardReader_Switch, String suspect_ID) {
		suspectService.updateSwitch(cardReader_Switch, suspect_ID);
	}

}
