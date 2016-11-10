package com.manageplantfrom.entity;

import java.io.Serializable;

/**
 * 身份证件种类字典表
 * （身份证、军官证。。。。？）
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class PHCSMP_Dic_IdentifyCard_Type implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int type_ID;
	private String type_Name;
	
	
	
	public int getType_ID() {
		return type_ID;
	}

	public void setType_ID(int type_ID) {
		this.type_ID = type_ID;
	}

	public String getType_Name() {
		return type_Name;
	}

	public void setType_Name(String type_Name) {
		this.type_Name = type_Name;
	}

	@Override
	public String toString() {
		return this.type_ID+" "+this.type_Name;
	}
}
