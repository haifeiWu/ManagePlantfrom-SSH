package com.manageplantfrom.entity;

import java.io.Serializable;

/**
 * 采集项目字典表
 * 身份信息、指纹、血液。。。
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class PHCSMP_Dic_Collection_Item implements Serializable {
	private int Item_ID;
	private String Item_Name;
	
	public int getItem_ID() {
		return Item_ID;
	}
	public void setItem_ID(int item_ID) {
		Item_ID = item_ID;
	}
	public String getItem_Name() {
		return Item_Name;
	}
	public void setItem_Name(String item_Name) {
		Item_Name = item_Name;
	}
	
}
