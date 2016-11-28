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

	private int Cause_ID;
	private String Cause_Name;

	public int getCause_ID() {
		return Cause_ID;
	}

	public void setCause_ID(int cause_ID) {
		Cause_ID = cause_ID;
	}

	public String getCause_Name() {
		return Cause_Name;
	}

	public void setCause_Name(String cause_Name) {
		Cause_Name = cause_Name;
	}

	@Override
	public String toString() {
		return this.Cause_ID + " " + this.Cause_Name;
	}
}
