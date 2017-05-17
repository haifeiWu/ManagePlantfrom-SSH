package com.haifeiWu.entity;

import java.io.Serializable;

public class PHCSMP_RoleFunction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4247947396857002233L;
	private Integer id;
	private Integer roleId;
	private Integer functionId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

}
