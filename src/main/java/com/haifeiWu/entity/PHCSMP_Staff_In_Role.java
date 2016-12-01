package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 用户所属角色表
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_Staff_In_Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3248780924836169545L;

	private int staff_ID;
	private int role_ID;

	public int getStaff_ID() {
		return staff_ID;
	}

	public void setStaff_ID(int staff_ID) {
		this.staff_ID = staff_ID;
	}

	public int getRole_ID() {
		return role_ID;
	}

	public void setRole_ID(int role_ID) {
		this.role_ID = role_ID;
	}

}
