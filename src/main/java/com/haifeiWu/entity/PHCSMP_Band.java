package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 手环信息表：
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_Band implements Serializable {

	/**
	 * 序列化的字段
	 */
	private static final long serialVersionUID = 667217033307265651L;

	private int band_ID;// 手环ID
	private String remark;// 备注
	private int is_Used;
	// wxy增加V1.2版本的字段
	private int band_Type;// 手环类型（0：RFID、1：ID）

	public int getBand_Type() {
		return band_Type;
	}

	public void setBand_Type(int band_Type) {
		this.band_Type = band_Type;
	}

	public int getBand_ID() {
		return band_ID;
	}

	public void setBand_ID(int band_ID) {
		this.band_ID = band_ID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getIs_Used() {
		return is_Used;
	}

	public void setIs_Used(int is_Used) {
		this.is_Used = is_Used;
	}

	@Override
	public String toString() {
		return this.band_ID + " " + this.remark + " " + this.is_Used;
	}
}
