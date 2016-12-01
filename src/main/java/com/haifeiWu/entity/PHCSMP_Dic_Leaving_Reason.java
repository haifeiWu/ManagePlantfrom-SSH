package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 离开原因字典表 查证结束、刑拘、行政拘留。
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_Dic_Leaving_Reason implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7404232264282641090L;

	private int leaving_ID;
	private String leaving_Name;

	public int getLeaving_ID() {
		return leaving_ID;
	}

	public void setLeaving_ID(int leaving_ID) {
		this.leaving_ID = leaving_ID;
	}

	public String getLeaving_Name() {
		return leaving_Name;
	}

	public void setLeaving_Name(String leaving_Name) {
		this.leaving_Name = leaving_Name;
	}

}
