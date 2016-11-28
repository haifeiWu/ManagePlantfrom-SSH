package com.haifeiWu.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

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

	private int Information_Collection_ID;
	private String Suspect_ID;// 档案编号
	private String Is_Collected;// 是否进行采集
	private String Collected_Item;// 采集项目（采集项目字典表中ID逗号隔开，只有Is_Collected为真时有效）
	private String Is_Storaged;// 是否入库
	private String Is_Checked;// 是否检查对比

	// 人身检查日期
	private Date IC_Date;
	private Time start_time;
	private Time end_time;

	private int Staff_ID;// 办案民警
	private int Room_ID;// 信息登记房间

	private int total_record = 60;// 需要填写的总记录数
	private int fill_record;// 当前填写的记录数

	public String getSuspect_ID() {
		return Suspect_ID;
	}

	public void setSuspect_ID(String suspect_ID) {
		Suspect_ID = suspect_ID;
	}

	public String getIs_Collected() {
		return Is_Collected;
	}

	public void setIs_Collected(String is_Collected) {
		Is_Collected = is_Collected;
	}

	public String getCollected_Item() {
		return Collected_Item;
	}

	public void setCollected_Item(String collected_Item) {
		Collected_Item = collected_Item;
	}

	public String getIs_Storaged() {
		return Is_Storaged;
	}

	public void setIs_Storaged(String is_Storaged) {
		Is_Storaged = is_Storaged;
	}

	public String getIs_Checked() {
		return Is_Checked;
	}

	public void setIs_Checked(String is_Checked) {
		Is_Checked = is_Checked;
	}

	public int getStaff_ID() {
		return Staff_ID;
	}

	public void setStaff_ID(int staff_ID) {
		Staff_ID = staff_ID;
	}

	public int getRoom_ID() {
		return Room_ID;
	}

	public void setRoom_ID(int room_ID) {
		Room_ID = room_ID;
	}

	public int getInformation_Collection_ID() {
		return Information_Collection_ID;
	}

	public void setInformation_Collection_ID(int information_Collection_ID) {
		Information_Collection_ID = information_Collection_ID;
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

	public Date getIC_Date() {
		return IC_Date;
	}

	public void setIC_Date(Date iC_Date) {
		IC_Date = iC_Date;
	}

	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}
}
