package com.haifeiWu.utils;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;

import com.haifeiWu.service.RoomService;

/**
 * websocket的服务器端
 * 
 * @author WXY
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 *                 客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket")
public class WebSocketUtils {

	/**
	 * 建立连接之后，如果RFID_ReadAction获取成功，则将信息传递到这里，进行处理之后，传递给客户端
	 * 
	 * 嫌疑人表中有当前手环信息，流程和房间号，房间表中有IP webSocket应在连接上的时候查出房间号，用房间号标识每个连接
	 */
	@Autowired
	private RoomService roomService;

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private ConcurrentMap<String, WebSocketUtils> map = new ConcurrentHashMap<String, WebSocketUtils>();

	private Session session;

	/**
	 * 树莓派传递过来的手环ID和读卡器 该方法会调用WebSocket的onmessage方法，将对应页面传递到客户端，客户端发出相应的请求
	 */
	public void flushPage(String bandID, String cardReader_ID) {

	}

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		// 根据当前对象去获取key，然后删除
		// map.remove(this); //从set中删除 //在线数减1
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息,ip地址或者RoomID
	 * @param session
	 *            可选的参数
	 */
	@OnMessage
	public void onMessage(String message) {
		if (message.split("\\.").length != 4) {
			System.out.println("消息是刷新的消息");

			int roomId = 0;
			roomId = Integer.parseInt(message);

			System.out.println(roomId);

			if (roomService == null) {
				System.out
						.println("----------------->roomService---====null<--------------------");
			}

			// PHCSMP_Room room=roomService.findByRoomID(roomId);
			// System.out.println("roomId:----->>>>>>>>>>>>> "+room.getRoom_ID());

			String ip = "127.0.0.1";// room.getRoom_IPAddress();
			int processID = 1;// room.getProcess_ID();
			String result = "";
			switch (processID) {
			case 1:
				result = "suspect";
				break;
			case 2:
				result = "suspect";
				break;
			case 3:
				result = "suspect";
				break;
			case 4:
				result = "suspect";
				break;
			case 5:
				result = "suspect";
				break;
			}
			WebSocketUtils item = map.get(ip);// 对对象为空的情况主动处理
			try {
				item.session.getBasicRemote().sendText(result);
			} catch (IOException e) {
				System.out.println("没有找到相应的客户端");
				// e.printStackTrace();
				// continue;
			}
		} else {
			System.out.println("来自客户端的消息:" + message);
			map.put(message, this);
		}
	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

}
