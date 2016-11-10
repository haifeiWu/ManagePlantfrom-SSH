package com.manageplantfrom.entity;

import java.io.Serializable;

/**
 * 人身检查记录字典表
 * 体表有伤痕、有饮酒、有拍照
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class PHCSMP_Dic_Inspection_Situation implements Serializable {
	private int Situation_ID;
	private String Situation_Name;
	
	
	public int getSituation_ID() {
		return Situation_ID;
	}
	public void setSituation_ID(int situation_ID) {
		Situation_ID = situation_ID;
	}
	public String getSituation_Name() {
		return Situation_Name;
	}
	public void setSituation_Name(String situation_Name) {
		Situation_Name = situation_Name;
	}
	
	
}
