package com.haifeiWu.entity;

import java.util.List;

/**
 * 设备初始化需向后台传list，spring后台不能直接接收list，所以定义
 * 一个带list成员的实体类，通过在后台定义与此实体类成员roomList同名的
 * 对象实例，便可将将传到后台的list注入成员roomList中
 * @author 20586
 *
 */
public class RoomList {
private List<PHCSMP_Room> roomList;

public List<PHCSMP_Room> getRoomList() {
	return roomList;
}

public void setRoomList(List<PHCSMP_Room> roomList) {
	this.roomList = roomList;
}

}
