package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 房间信息表
 * 
 * @author wuhaifei
 * @d2016年9月8日
 */
public class PHCSMP_Room implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5462954138759630229L;

	private int room_ID;
	private String room_Name;
	private String cardReader_ID;

	public int getRoom_ID() {
		return room_ID;
	}

	public void setRoom_ID(int room_ID) {
		this.room_ID = room_ID;
	}

	public String getRoom_Name() {
		return room_Name;
	}

	public void setRoom_Name(String room_Name) {
		this.room_Name = room_Name;
	}

	public String getCardReader_ID() {
		return cardReader_ID;
	}

	public void setCardReader_ID(String cardReader_ID) {
		this.cardReader_ID = cardReader_ID;
	}

}