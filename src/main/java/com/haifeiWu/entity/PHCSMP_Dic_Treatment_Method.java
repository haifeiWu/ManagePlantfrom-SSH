package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 随身物品处理方式字典表 全部返还、部分返还。
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_Dic_Treatment_Method implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8753863420317160895L;

	private int treatment_ID;
	private String treatment_Name;

	public int getTreatment_ID() {
		return treatment_ID;
	}

	public void setTreatment_ID(int treatment_ID) {
		this.treatment_ID = treatment_ID;
	}

	public String getTreatment_Name() {
		return treatment_Name;
	}

	public void setTreatment_Name(String treatment_Name) {
		this.treatment_Name = treatment_Name;
	}

}
