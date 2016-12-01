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
	// 保存随身物品信息
	// private String Belonging_Number;// 物品编号
	// private String Belonging_Name_Character;// 物品名称及特征
	// private int Belonging_Count;// 物品数量
	// private String Belonging_Unit;// 物品单位
	// private int Keeping_ID;// 保管措施（随身物品保管措施字典表中ID逗号隔开）
	// private String Cabinet_Number;// 保管柜编号
	// 人身检查日期
	private String check_Date;

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

	public String getCheck_Date() {
		return check_Date;
	}

	public void setCheck_Date(String check_Date) {
		this.check_Date = check_Date;
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

	private String start_time;
	private String end_time;
	// 自述症状
	private String isDrink;// 是否饮酒
	private String isDiseases;// 是否传染性疾病

	private String staff_ID;// 办案民警
	private int room_ID;// 信息登记房间
	private int total_record = 60;// 需要填写的总记录数
	private int fill_record;// 当前填写的记录数

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

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getIsDrink() {
		return isDrink;
	}

	public void setIsDrink(String isDrink) {
		this.isDrink = isDrink;
	}

	public String getIsDiseases() {
		return isDiseases;
	}

	public void setIsDiseases(String isDiseases) {
		this.isDiseases = isDiseases;
	}

	@Override
	public String toString() {
		return this.suspect_ID + " " + this.check_Situation + " "
				+ this.check_Date;
	}
}
