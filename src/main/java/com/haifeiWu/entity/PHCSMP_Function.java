package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 描述该系统的功能以及功能之间的关系
 * 
 * @author WXY
 * 
 */
public class PHCSMP_Function implements Serializable {

	private static final long serialVersionUID = -662395897996347411L;
	private Integer function_id;
	private String function_name;
	private String function_desc;
	private Integer function_parentId;// 如果是父功能，此项为空；如果是子功能，则根据此项寻找父功能
	private String url;// url的格式为请求的controller的格式

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getFunction_id() {
		return function_id;
	}

	public void setFunction_id(Integer function_id) {
		this.function_id = function_id;
	}

	public String getFunction_name() {
		return function_name;
	}

	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}

	public String getFunction_desc() {
		return function_desc;
	}

	public void setFunction_desc(String function_desc) {
		this.function_desc = function_desc;
	}

	public Integer getFunction_parentId() {
		return function_parentId;
	}

	public void setFunction_parentId(Integer function_parentId) {
		this.function_parentId = function_parentId;
	}

	@Override
	public String toString() {
		return "PHCSMP_Function [function_id=" + function_id
				+ ", function_name=" + function_name + ", function_desc="
				+ function_desc + ", function_parentId=" + function_parentId
				+ "]";
	}

}
