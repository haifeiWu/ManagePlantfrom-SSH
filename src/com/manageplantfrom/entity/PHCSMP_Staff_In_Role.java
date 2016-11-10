package com.manageplantfrom.entity;

import java.io.Serializable;

/**
 * 用户所属角色表
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class PHCSMP_Staff_In_Role implements Serializable {
	
	private int Staff_ID;
	private int Role_ID;
	
	
	public int getRole_ID() {
		return Role_ID;
	}
	public void setRole_ID(int role_ID) {
		Role_ID = role_ID;
	}
	public int getStaff_ID() {
		return Staff_ID;
	}
	public void setStaff_ID(int staff_ID) {
		Staff_ID = staff_ID;
	}
}
