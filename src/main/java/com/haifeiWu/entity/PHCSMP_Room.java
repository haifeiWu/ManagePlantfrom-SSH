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
	//wxy增加V1.2版本的字段
	private int process_ID;//房间是属于哪个流程的
	private int line_Number;//连接录播系统线路编号（1-Line_Count）
	private String room_IPAddress;//房间IP地址及通讯端口
	
	
	
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