package com.manageplantfrom.entity;

import java.io.Serializable;

/**
 * 设备信息表
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class PHCSMP_CardReader implements Serializable {
	private int CardReader_ID;//设备ID（主键）
	private String CardReader_Name;//设备名称
	
	
	public int getCardReader_ID() {
		return CardReader_ID;
	}
	public void setCardReader_ID(int cardReader_ID) {
		CardReader_ID = cardReader_ID;
	}
	public String getCardReader_Name() {
		return CardReader_Name;
	}
	public void setCardReader_Name(String cardReader_Name) {
		CardReader_Name = cardReader_Name;
	}
	
}
