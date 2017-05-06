package com.haifeiWu.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haifeiWu.entity.PHCSMP_Room;
import com.haifeiWu.entity.RoomList;
import com.haifeiWu.service.RoomService;

//import com.sun.tools.internal.ws.processor.model.Request;

/**
 * 房间管理Action
 * 
 * @author litt
 * @d2017年3月6日
 */
@Controller
@RequestMapping("/room")
@Scope("prototype")
public class RoomManageAction {

	private static final long serialVersionUID = 4291137271901380604L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;

	private String query;
	private String room_ID;
	private String room_Name;
	private int cardReader_ID;
	// wxy增加V1.2版本的字段
	private int process_ID;// 房间是属于哪个流程的
	private int line_Number;// 连接录播系统线路编号（1-Line_Count）
	private String room_IPAddress;
	private String roomIdList;
	private String roomIdArray;

	@Autowired
	private RoomService roomService; // 房间信息

	/**
	 * 查询所有的房间信息
	 */
	@RequestMapping(value = "/load")
	public String RM_loadInfor(HttpServletRequest request) {
		System.out.println("查找所有的房间信息");
		// 获取所有房间的信息
		List<PHCSMP_Room> roomCheckInfo = roomService.findAllRoom();
		if (roomCheckInfo == null) {
			return "NULL";// 处理空的情况
		} else {
			request.setAttribute("roomCheckInfo", roomCheckInfo);
			for (PHCSMP_Room phcsmp_Room : roomCheckInfo) {
				System.out.println(phcsmp_Room.toString());
			}
			
			return "/WEB-INF/jsp/roommanage/room";
		}

	}
	/**
	 * 房间初始化
	 */
	@RequestMapping(value = "/updateRoom")
	public String updateRoom(RoomList roomList,HttpServletRequest request){
		System.out.println("进入房间初始化方法");
		for(PHCSMP_Room pHCSMP_Room :roomList.getRoomList()){
			System.out.println(pHCSMP_Room.getRoom_ID());
			System.out.println(pHCSMP_Room.getRoom_Name());
			System.out.println(pHCSMP_Room.getCardReader_ID());
			System.out.println(pHCSMP_Room.getRoom_IPAddress());
			roomService.updateprocess_IDById(pHCSMP_Room.getCardReader_ID(), pHCSMP_Room.getRoom_ID(), pHCSMP_Room.getRoom_IPAddress());
		}
		
		return RM_loadInfor(request);
	}
	/**
	 * 按条件查找房间信息
	 */
	public String loadInforByRoomId() {
		List<PHCSMP_Room> roomCheckInfo = new ArrayList<>();
		try {
			System.out.println("按ID查找");
			int roomId = 0;
			// String query = request.getParameter("query");
			System.out.println("q" + query);
			if (query != "") {
				roomId = Integer.parseInt(query);
			}
			System.out.println("r" + roomId);
			roomCheckInfo = roomService.findListByRoomID(roomId);
			if (roomCheckInfo.get(0) == null) {
				return "NULL";// 处理空的情况
			} else {
				request.setAttribute("roomCheckInfo", roomCheckInfo);
				System.out.println(roomCheckInfo);

				return "loadInfor";
			}
		} catch (Exception e) {
			if (roomCheckInfo == null) {
				return "null";// 处理空的情况
			}
			request.setAttribute("result", "不存在的查询条件！");
			return "error";
		}

	}

	public String loadInforByCardReaderID() {
		try {
			System.out.println("按设备ID查找");
			System.out.println("q" + query);
			int roomId = Integer.parseInt(query);
			System.out.println("r" + roomId);
			List<PHCSMP_Room> roomCheckInfo = roomService
					.findListByCardReaderID(roomId);
			if (roomCheckInfo.get(0) == null) {
				return "NULL";// 处理空的情况
			} else {
				request.setAttribute("roomCheckInfo", roomCheckInfo);
				System.out.println(roomCheckInfo);

				return "loadInfor";
			}
		} catch (Exception e) {
			request.setAttribute("result", "不存在的查询条件！");
			return "error";
		}

	}

	//
	public String loadInforByIp() {
		try {
			System.out.println("按Ip查找");
			System.out.println("q" + query);
			String roomId = query;
			System.out.println("r" + roomId);
			List<PHCSMP_Room> roomCheckInfo = roomService.findListbyIp(roomId);
			if (roomCheckInfo.get(0) == null) {
				return "NULL";// 处理空的情况
			} else {
				request.setAttribute("roomCheckInfo", roomCheckInfo);
				System.out.println(roomCheckInfo);

				return "loadInfor";
			}
		} catch (Exception e) {
			request.setAttribute("result", "不存在的查询条件！");
			return "error";
		}
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @return
	 */
	public String toUpdateInfor() {
		try {
			int roomid = Integer.parseInt(room_ID);
			PHCSMP_Room room = roomService.findByRoomID(roomid);
			request.setAttribute("room", room);
			System.out.println(room);
			return "update";
		} catch (Exception e) {
			request.setAttribute("result", "服务器错误，未能显示！");
			return "error";
		}

	}

	/**
	 * 跳转到批修改
	 */
	public String tobatchUpdate() {
		try {
			request.setAttribute("roomIdList", roomIdList);
			System.out.println("s" + roomIdList);
			return "batchupdate";
		} catch (Exception e) {
			request.setAttribute("result", "服务器出错，页面跳转失败！");
			return "error";
		}
	}


	/*
	 * setter getter
	 */

	public String getQuery() {
		return query;
	}

	public String getRoomIdArray() {
		return roomIdArray;
	}

	public void setRoomIdArray(String roomIdArray) {
		this.roomIdArray = roomIdArray;
	}

	public String getRoomIdList() {
		return roomIdList;
	}

	public void setRoomIdList(String roomIdList) {
		this.roomIdList = roomIdList;
	}

	public String getRoom_Name() {
		return room_Name;
	}

	public void setRoom_Name(String room_Name) {
		this.room_Name = room_Name;
	}

	public int getCardReader_ID() {
		return cardReader_ID;
	}

	public void setCardReader_ID(int cardReader_ID) {
		this.cardReader_ID = cardReader_ID;
	}

	public int getProcess_ID() {
		return process_ID;
	}

	public void setProcess_ID(int process_ID) {
		this.process_ID = process_ID;
	}

	public int getLine_Number() {
		return line_Number;
	}

	public void setLine_Number(int line_Number) {
		this.line_Number = line_Number;
	}

	public String getRoom_IPAddress() {
		return room_IPAddress;
	}

	public void setRoom_IPAddress(String room_IPAddress) {
		this.room_IPAddress = room_IPAddress;
	}

	public String getRoom_ID() {
		return room_ID;
	}

	public void setRoom_ID(String room_ID) {
		this.room_ID = room_ID;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	// @Override
	// public void setServletContext(ServletContext application) {
	// this.application = application;
	// }
	//
	// @Override
	// public void setServletResponse(HttpServletResponse response) {
	// this.response = response;
	// }
	//
	// @Override
	// public void setServletRequest(HttpServletRequest request) {
	// this.request = request;
	// }

}
