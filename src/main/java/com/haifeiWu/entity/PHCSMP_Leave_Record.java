package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 离开办案区登记表 PHCSMP_Leave_Record
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_Leave_Record implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7800818669189466828L;

	private int leave_Record_ID;// id（主键）
	private String suspect_ID;// 档案编号
	private String leave_Time;// 最终离开时间
	private String leave_Hour;
	private String leave_Reason;// 离开原因（离开原因字典表ID逗号隔开）
	private String belongingS_Treatment_Method;// 随身物品处理方式字典表ID逗号隔开
	private String belongingS_Treatment_Record;// 未返还物品处理记录
	private String recipient_Person;// 领取人姓名
	private String recipient_Person_Number;// 领取人身份证号码
	private String treatment_Time;// 随身物品处理时间
	private int staff_ID;// 办案民警 4
	private int room_ID;// 信息登记房间

	private int total_record = 60;// 需要填写的总记录数
	private int fill_record;// 当前填写的记录数

	public int getLeave_Record_ID() {
		return leave_Record_ID;
	}

	public void setLeave_Record_ID(int leave_Record_ID) {
		this.leave_Record_ID = leave_Record_ID;
	}

	public String getSuspect_ID() {
		return suspect_ID;
	}

	public void setSuspect_ID(String suspect_ID) {
		this.suspect_ID = suspect_ID;
	}

	public String getLeave_Time() {
		return leave_Time;
	}

	public void setLeave_Time(String leave_Time) {
		this.leave_Time = leave_Time;
	}

	public String getLeave_Hour() {
		return leave_Hour;
	}

	public void setLeave_Hour(String leave_Hour) {
		this.leave_Hour = leave_Hour;
	}

	public String getLeave_Reason() {
		return leave_Reason;
	}

	public void setLeave_Reason(String leave_Reason) {
		this.leave_Reason = leave_Reason;
	}

	public String getBelongingS_Treatment_Method() {
		return belongingS_Treatment_Method;
	}

	public void setBelongingS_Treatment_Method(
			String belongingS_Treatment_Method) {
		this.belongingS_Treatment_Method = belongingS_Treatment_Method;
	}

	public String getBelongingS_Treatment_Record() {
		return belongingS_Treatment_Record;
	}

	public void setBelongingS_Treatment_Record(
			String belongingS_Treatment_Record) {
		this.belongingS_Treatment_Record = belongingS_Treatment_Record;
	}

	public String getRecipient_Person() {
		return recipient_Person;
	}

	public void setRecipient_Person(String recipient_Person) {
		this.recipient_Person = recipient_Person;
	}

	public String getRecipient_Person_Number() {
		return recipient_Person_Number;
	}

	public void setRecipient_Person_Number(String recipient_Person_Number) {
		this.recipient_Person_Number = recipient_Person_Number;
	}

	public String getTreatment_Time() {
		return treatment_Time;
	}

	public void setTreatment_Time(String treatment_Time) {
		this.treatment_Time = treatment_Time;
	}

	public int getStaff_ID() {
		return staff_ID;
	}

	public void setStaff_ID(int staff_ID) {
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
		return this.suspect_ID + " " + this.leave_Reason + " "
				+ this.recipient_Person;
	}

}
