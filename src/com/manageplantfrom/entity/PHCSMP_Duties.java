package com.manageplantfrom.entity;

import java.io.Serializable;

/**
 * 职务信息类
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class PHCSMP_Duties implements Serializable{
	private int Duties_ID;//职务ID
	private String Duties_Name;//职务名称
	private String Duties_Desc;//职务简介
	
	public int getDuties_ID() {
		return Duties_ID;
	}
	public void setDuties_ID(int duties_ID) {
		Duties_ID = duties_ID;
	}
	public String getDuties_Name() {
		return Duties_Name;
	}
	public void setDuties_Name(String duties_Name) {
		Duties_Name = duties_Name;
	}
	public String getDuties_Desc() {
		return Duties_Desc;
	}
	public void setDuties_Desc(String duties_Desc) {
		Duties_Desc = duties_Desc;
	}
}
