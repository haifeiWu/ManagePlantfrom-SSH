package com.manageplantfrom.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * 进入人员人身检查信息表
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_Personal_Check implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Check_ID;// 安全检查信息ID（主键）
	private String Suspect_ID;// 档案编号
	private String Self_ReportS;// 自述症状
	private String Check_ReportS;// 检查情况
	private String Check_Situation;// 人身检查状态（人身检查记录字典表中ID逗号分隔）
	//保存随身物品信息
//	private String Belonging_Number;// 物品编号
//	private String Belonging_Name_Character;// 物品名称及特征
//	private int Belonging_Count;// 物品数量
//	private String Belonging_Unit;// 物品单位
//	private int Keeping_ID;// 保管措施（随身物品保管措施字典表中ID逗号隔开）
//	private String Cabinet_Number;// 保管柜编号
	//人身检查日期
	private Date Check_Date;
	private Time start_time;
	private Time end_time;
	//自述症状
	private String isDrink;//是否饮酒
	private String isDiseases;//是否传染性疾病
	
	private String Staff_ID;// 办案民警
	private int Room_ID;// 信息登记房间
	private int total_record = 60;// 需要填写的总记录数
	private int fill_record;// 当前填写的记录数

	public int getCheck_ID() {
		return Check_ID;
	}

	public void setCheck_ID(int check_ID) {
		Check_ID = check_ID;
	}

	public String getSuspect_ID() {
		return Suspect_ID;
	}

	public void setSuspect_ID(String suspect_ID) {
		Suspect_ID = suspect_ID;
	}

	public String getSelf_ReportS() {
		return Self_ReportS;
	}

	public void setSelf_ReportS(String self_ReportS) {
		Self_ReportS = self_ReportS;
	}

	public String getCheck_ReportS() {
		return Check_ReportS;
	}

	public void setCheck_ReportS(String check_ReportS) {
		Check_ReportS = check_ReportS;
	}

	public String getCheck_Situation() {
		return Check_Situation;
	}

	public void setCheck_Situation(String check_Situation) {
		Check_Situation = check_Situation;
	}

	public String getStaff_ID() {
		return Staff_ID;
	}

	public void setStaff_ID(String staff_ID) {
		Staff_ID = staff_ID;
	}

	public int getRoom_ID() {
		return Room_ID;
	}

	public void setRoom_ID(int room_ID) {
		Room_ID = room_ID;
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

//	public String getBelonging_Number() {
//		return Belonging_Number;
//	}
//
//	public void setBelonging_Number(String belonging_Number) {
//		Belonging_Number = belonging_Number;
//	}
//
//	public String getBelonging_Name_Character() {
//		return Belonging_Name_Character;
//	}
//
//	public void setBelonging_Name_Character(String belonging_Name_Character) {
//		Belonging_Name_Character = belonging_Name_Character;
//	}
//
//	public int getBelonging_Count() {
//		return Belonging_Count;
//	}
//
//	public void setBelonging_Count(int belonging_Count) {
//		Belonging_Count = belonging_Count;
//	}
//
//	public String getBelonging_Unit() {
//		return Belonging_Unit;
//	}
//
//	public void setBelonging_Unit(String belonging_Unit) {
//		Belonging_Unit = belonging_Unit;
//	}
//
//	public int getKeeping_ID() {
//		return Keeping_ID;
//	}
//
//	public void setKeeping_ID(int keeping_ID) {
//		Keeping_ID = keeping_ID;
//	}
//
//	public String getCabinet_Number() {
//		return Cabinet_Number;
//	}
//
//	public void setCabinet_Number(String cabinet_Number) {
//		Cabinet_Number = cabinet_Number;
//	}

	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}

	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public Date getCheck_Date() {
		return Check_Date;
	}

	public void setCheck_Date(Date check_Date) {
		Check_Date = check_Date;
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
		return this.Suspect_ID+" "+this.Check_Situation;
	}
}
