package com.manageplantfrom.entity;

import java.io.Serializable;

/**
 * 手环信息表：
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class PHCSMP_Band implements Serializable {
	
	/**
	 * 序列化的字段
	 */
	private static final long serialVersionUID = 667217033307265651L;
	
	private int band_ID;//手环ID
	private String remark;//备注
	
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
	
	@Override
	public String toString() {
		return this.band_ID+" "+this.remark;
	}
}
