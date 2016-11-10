package com.manageplantfrom.entity;

import java.io.Serializable;

/**
 * 离开原因字典表
 * 查证结束、刑拘、行政拘留。
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class PHCSMP_Dic_Leaving_Reason implements Serializable {
	
	private int Leaving_ID;
	private String Leaving_Name;
	
	public int getLeaving_ID() {
		return Leaving_ID;
	}
	public void setLeaving_ID(int leaving_ID) {
		Leaving_ID = leaving_ID;
	}
	public String getLeaving_Name() {
		return Leaving_Name;
	}
	public void setLeaving_Name(String leaving_Name) {
		Leaving_Name = leaving_Name;
	}
	
}
