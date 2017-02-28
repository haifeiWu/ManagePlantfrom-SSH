package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 设备信息表
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_CardReader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4575772948745734373L;

	private int cardReader_ID;// 设备ID（主键）
	private String cardReader_Name;// 设备名称
	//wxy增加V1.2版本的字段
	private int cardReader_Type;//设备类型（0：RFID、1：ID）
	
	public int getCardReader_Type() {
		return cardReader_Type;
	}

	public void setCardReader_Type(int cardReader_Type) {
		this.cardReader_Type = cardReader_Type;
	}

	public int getCardReader_ID() {
		return cardReader_ID;
	}

	public void setCardReader_ID(int cardReader_ID) {
		this.cardReader_ID = cardReader_ID;
	}

	public String getCardReader_Name() {
		return cardReader_Name;
	}

	public void setCardReader_Name(String cardReader_Name) {
		this.cardReader_Name = cardReader_Name;
	}

}
