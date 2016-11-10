package com.manageplantfrom.entity;

import java.io.Serializable;

/**
 * 房间信息表
 * @author wuhaifei
 * @d2016年9月8日
 */
public class PHCSMP_Room implements Serializable {
	private int Room_ID;
	private String Room_Name;
	private String CardReader_ID;

	public int getRoom_ID() {
		return Room_ID;
	}

	public void setRoom_ID(int room_ID) {
		Room_ID = room_ID;
	}

	public String getRoom_Name() {
		return Room_Name;
	}

	public void setRoom_Name(String room_Name) {
		Room_Name = room_Name;
	}

	public String getCardReader_ID() {
		return CardReader_ID;
	}

	public void setCardReader_ID(String cardReader_ID) {
		CardReader_ID = cardReader_ID;
	}
}
