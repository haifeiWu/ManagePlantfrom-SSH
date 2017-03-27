package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 进入人员人身检查信息表
 * 
 * @author wuhaifei
 * 
 * @String 2016年8月9日
 */
public class PHCSMP_Personal_Check implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6009668148816403977L;

	private int check_ID;// 安全检查信息ID（主键）
	private String suspect_ID;// 档案编号
	private String self_ReportS;// 自述症状
	private String check_ReportS;// 检查情况
	private String check_Situation;// 人身检查状态（人身检查记录字典表中ID逗号分隔）
	private String is_Drink;// 是否饮酒
	private String is_Diseases;// 是否生病
	private String check_StartTime;// 人身检查起始时间
	private String check_EndTime;// 人身检查借宿时间

	private String staff_ID;// 办案民警
	private int room_ID;// 信息登记房间
	private int total_record = 60;// 需要填写的总记录数
	private int fill_record;// 当前填写的记录数

	public String getIs_Drink() {
		return is_Drink;
	}

	public void setIs_Drink(String is_Drink) {
		this.is_Drink = is_Drink;
	}

	public String getIs_Diseases() {
		return is_Diseases;
	}

	public void setIs_Diseases(String is_Diseases) {
		this.is_Diseases = is_Diseases;
	}

	public String getCheck_StartTime() {
		return check_StartTime;
	}

	public void setCheck_StartTime(String check_StartTime) {
		this.check_StartTime = check_StartTime;
	}

	public String getCheck_EndTime() {
		return check_EndTime;
	}

	public void setCheck_EndTime(String check_EndTime) {
		this.check_EndTime = check_EndTime;
	}

	public int getCheck_ID() {
		return check_ID;
	}

	public void setCheck_ID(int check_ID) {
		this.check_ID = check_ID;
	}

	public String getSuspect_ID() {
		return suspect_ID;
	}

	public void setSuspect_ID(String suspect_ID) {
		this.suspect_ID = suspect_ID;
	}

	public String getSelf_ReportS() {
		return self_ReportS;
	}

	public void setSelf_ReportS(String self_ReportS) {
		this.self_ReportS = self_ReportS;
	}

	public String getCheck_ReportS() {
		return check_ReportS;
	}

	public void setCheck_ReportS(String check_ReportS) {
		this.check_ReportS = check_ReportS;
	}

	public String getCheck_Situation() {
		return check_Situation;
	}

	public void setCheck_Situation(String check_Situation) {
		this.check_Situation = check_Situation;
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

	@Override
	public String toString() {
		return "PHCSMP_Personal_Check [check_ID=" + check_ID + ", suspect_ID="
				+ suspect_ID + ", self_ReportS=" + self_ReportS
				+ ", check_ReportS=" + check_ReportS + ", check_Situation="
				+ check_Situation + ", is_Drink=" + is_Drink + ", is_Diseases="
				+ is_Diseases + ", check_StartTime=" + check_StartTime
				+ ", check_EndTime=" + check_EndTime + ", staff_ID=" + staff_ID
				+ ", room_ID=" + room_ID + ", total_record=" + total_record
				+ ", fill_record=" + fill_record + "]";
	}

	// @Override
	// public String toString() {
	// return this.suspect_ID + " " + this.check_Situation + " "
	// + this.check_StartTime;
	// }
}
