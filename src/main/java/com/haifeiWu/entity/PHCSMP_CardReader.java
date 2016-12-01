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
