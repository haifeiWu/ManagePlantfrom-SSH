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

	private Integer staff_ID;
	private Integer role_ID;

	public Integer getStaff_ID() {
		return staff_ID;
	}

	public void setStaff_ID(Integer staff_ID) {
		this.staff_ID = staff_ID;
	}

	public Integer getRole_ID() {
		return role_ID;
	}

	public void setRole_ID(Integer role_ID) {
		this.role_ID = role_ID;
	}

}
