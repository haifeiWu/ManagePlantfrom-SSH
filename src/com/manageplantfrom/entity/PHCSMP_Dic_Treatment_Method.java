package com.manageplantfrom.entity;

import java.io.Serializable;

/**
 * 随身物品处理方式字典表
 * 全部返还、部分返还。
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class PHCSMP_Dic_Treatment_Method implements Serializable {
	private int Treatment_ID;
	private String Treatment_Name;
	
	public int getTreatment_ID() {
		return Treatment_ID;
	}
	public void setTreatment_ID(int treatment_ID) {
		Treatment_ID = treatment_ID;
	}
	public String getTreatment_Name() {
		return Treatment_Name;
	}
	public void setTreatment_Name(String treatment_Name) {
		Treatment_Name = treatment_Name;
	}
}
