package com.haifeiWu.entity;

import java.io.Serializable;

/***
 * 进入人员信息采集表
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_Information_Collection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1756540490806828477L;

	private int information_Collection_ID;
	private String suspect_ID;// 档案编号
	private String is_Collected;// 是否进行采集
	private String collected_Item;// 采集项目（采集项目字典表中ID逗号隔开，只有Is_Collected为真时有效）
	private String is_Storaged;// 是否入库
	private String is_Checked;// 是否检查对比
	private String staff_ID;// 办案民警
	private int room_ID;// 信息登记房间

	private int total_record = 60;// 需要填写的总记录数
	private int fill_record;// 当前填写的记录数

	// 人身检查日期
	private String ic_StartTime;
	private String ic_EndTime;

	public int getInformation_Collection_ID() {
		return information_Collection_ID;
	}

	public void setInformation_Collection_ID(int information_Collection_ID) {
		this.information_Collection_ID = information_Collection_ID;
	}

	public String getSuspect_ID() {
		return suspect_ID;
	}

	public void setSuspect_ID(String suspect_ID) {
		this.suspect_ID = suspect_ID;
	}

	public String getIs_Collected() {
		return is_Collected;
	}

	public void setIs_Collected(String is_Collected) {
		this.is_Collected = is_Collected;
	}

	public String getCollected_Item() {
		return collected_Item;
	}

	public void setCollected_Item(String collected_Item) {
		this.collected_Item = collected_Item;
	}

	public String getIs_Storaged() {
		return is_Storaged;
	}

	public void setIs_Storaged(String is_Storaged) {
		this.is_Storaged = is_Storaged;
	}

	public String getIs_Checked() {
		return is_Checked;
	}

	public void setIs_Checked(String is_Checked) {
		this.is_Checked = is_Checked;
	}

	

	public String getStaff_ID() {
		return staff_ID;
	}

	public void setStaff_ID(String staff_ID) {
		this.staff_ID = staff_ID;
	}

	public int getRoom_ID() {
		return room_ID;
	}

	public void setRoom_ID(int room_ID) {
		this.room_ID = room_ID;
	}

	public int getTotal_record() {
		return total_record;
	}

	public void setTotal_record(int total_record) {
		this.total_record = total_record;
	}

	public int getFill_record() {
		return fill_record;
	}

	public void setFill_record(int fill_record) {
		this.fill_record = fill_record;
	}

	public String getIc_StartTime() {
		return ic_StartTime;
	}

	public void setIc_StartTime(String ic_StartTime) {
		this.ic_StartTime = ic_StartTime;
	}

	public String getIc_EndTime() {
		return ic_EndTime;
	}

	public void setIc_EndTime(String ic_EndTime) {
		this.ic_EndTime = ic_EndTime;
	}

}
