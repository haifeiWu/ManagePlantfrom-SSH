package com.manageplantfrom.entity;

import java.io.Serializable;

/**
 * 角色信息
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class PHCSMP_Role implements Serializable{
	
	private int Role_ID;//角色ID
	private String Role_Name;//角色名称
	private String Role_Description;//角色说明
	private String Is_Default_Role;
	
	public int getRole_ID() {
		return Role_ID;
	}
	public void setRole_ID(int role_ID) {
		Role_ID = role_ID;
	}
	public String getRole_Name() {
		return Role_Name;
	}
	public void setRole_Name(String role_Name) {
		Role_Name = role_Name;
	}
	public String getRole_Description() {
		return Role_Description;
	}
	public void setRole_Description(String role_Description) {
		Role_Description = role_Description;
	}
	public String getIs_Default_Role() {
		return Is_Default_Role;
	}
	public void setIs_Default_Role(String is_Default_Role) {
		Is_Default_Role = is_Default_Role;
	}
	
	
	
	
	
}
