package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 人身检查记录字典表 体表有伤痕、有饮酒、有拍照
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_Dic_Inspection_Situation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8330438266134878347L;

	private int situation_ID;
	private String situation_Name;

	public int getSituation_ID() {
		return situation_ID;
	}

	public void setSituation_ID(int situation_ID) {
		this.situation_ID = situation_ID;
	}

	public String getSituation_Name() {
		return situation_Name;
	}

	public void setSituation_Name(String situation_Name) {
		this.situation_Name = situation_Name;
	}

}
