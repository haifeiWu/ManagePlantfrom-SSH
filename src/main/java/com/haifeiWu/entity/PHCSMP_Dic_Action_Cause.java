package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 案由字典表 投案自首、治安传唤、刑事传唤。。。。
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_Dic_Action_Cause implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6169584587139560241L;

	private int cause_ID;
	private String cause_Name;

	public int getCause_ID() {
		return cause_ID;
	}

	public void setCause_ID(int cause_ID) {
		this.cause_ID = cause_ID;
	}

	public String getCause_Name() {
		return cause_Name;
	}

	public void setCause_Name(String cause_Name) {
		this.cause_Name = cause_Name;
	}

	@Override
	public String toString() {
		return this.cause_ID + " " + this.cause_Name;
	}
}
