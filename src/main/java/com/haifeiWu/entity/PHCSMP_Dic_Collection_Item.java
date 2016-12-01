package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 采集项目字典表 身份信息、指纹、血液。。。
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_Dic_Collection_Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6567081619715861840L;

	private int item_ID;
	private String item_Name;

	public int getItem_ID() {
		return item_ID;
	}

	public void setItem_ID(int item_ID) {
		this.item_ID = item_ID;
	}

	public String getItem_Name() {
		return item_Name;
	}

	public void setItem_Name(String item_Name) {
		this.item_Name = item_Name;
	}

}
