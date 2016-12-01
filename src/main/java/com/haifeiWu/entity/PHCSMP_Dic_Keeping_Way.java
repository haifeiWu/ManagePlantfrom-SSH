package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 随身物品保管措施字典表 （扣押、暂存、代保管）
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_Dic_Keeping_Way implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1779968749114998768L;

	private int keeping_ID;
	private String keeping_Name;

	public int getKeeping_ID() {
		return keeping_ID;
	}

	public void setKeeping_ID(int keeping_ID) {
		this.keeping_ID = keeping_ID;
	}

	public String getKeeping_Name() {
		return keeping_Name;
	}

	public void setKeeping_Name(String keeping_Name) {
		this.keeping_Name = keeping_Name;
	}

}
