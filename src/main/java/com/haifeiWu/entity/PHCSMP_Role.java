package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 角色信息
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5151854432225451697L;

	private Integer role_ID;// 角色ID
	private String role_Name;// 角色名称
	private String role_Description;// 角色说明
	private String is_Default_Role;

	public Integer getRole_ID() {
		return role_ID;
	}

	public void setRole_ID(Integer role_ID) {
		this.role_ID = role_ID;
	}

	public String getRole_Name() {
		return role_Name;
	}

	public void setRole_Name(String role_Name) {
		this.role_Name = role_Name;
	}

	public String getRole_Description() {
		return role_Description;
	}

	public void setRole_Description(String role_Description) {
		this.role_Description = role_Description;
	}

	public String getIs_Default_Role() {
		return is_Default_Role;
	}

	public void setIs_Default_Role(String is_Default_Role) {
		this.is_Default_Role = is_Default_Role;
	}

}
