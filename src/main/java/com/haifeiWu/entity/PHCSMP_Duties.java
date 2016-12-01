package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 职务信息类
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_Duties implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8968707978691426277L;

	private int duties_ID;// 职务ID
	private String duties_Name;// 职务名称
	private String duties_Desc;// 职务简介

	public int getDuties_ID() {
		return duties_ID;
	}

	public void setDuties_ID(int duties_ID) {
		this.duties_ID = duties_ID;
	}

	public String getDuties_Name() {
		return duties_Name;
	}

	public void setDuties_Name(String duties_Name) {
		this.duties_Name = duties_Name;
	}

	public String getDuties_Desc() {
		return duties_Desc;
	}

	public void setDuties_Desc(String duties_Desc) {
		this.duties_Desc = duties_Desc;
	}

}
