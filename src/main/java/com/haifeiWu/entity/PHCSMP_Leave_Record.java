package com.haifeiWu.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

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

	private int Leave_Record_ID;// id（主键）
	private String Suspect_ID;// 档案编号
	private Date Leave_Time;// 最终离开时间
	private Time Leave_Hour;
	private String Leave_Reason;// 离开原因（离开原因字典表ID逗号隔开）
	private String BelongingS_Treatment_Method;// 随身物品处理方式字典表ID逗号隔开
	private String BelongingS_Treatment_Record;// 未返还物品处理记录
	private String Recipient_Person;// 领取人姓名
	private String Recipient_Person_Number;// 领取人身份证号码
	private Time Treatment_Time;// 随身物品处理时间
	private int Staff_ID;// 办案民警 4
	private int Room_ID;// 信息登记房间

	private int total_record = 60;// 需要填写的总记录数
	private int fill_record;// 当前填写的记录数

	public String getSuspect_ID() {
		return Suspect_ID;
	}

	public void setSuspect_ID(String suspect_ID) {
		Suspect_ID = suspect_ID;
	}

	public Date getLeave_Time() {
		return Leave_Time;
	}

	public void setLeave_Time(Date leave_Time) {
		Leave_Time = leave_Time;
	}

	public String getLeave_Reason() {
		return Leave_Reason;
	}

	public void setLeave_Reason(String leave_Reason) {
		Leave_Reason = leave_Reason;
	}

	public String getBelongingS_Treatment_Method() {
		return BelongingS_Treatment_Method;
	}

	public void setBelongingS_Treatment_Method(
			String belongingS_Treatment_Method) {
		BelongingS_Treatment_Method = belongingS_Treatment_Method;
	}

	public String getBelongingS_Treatment_Record() {
		return BelongingS_Treatment_Record;
	}

	public void setBelongingS_Treatment_Record(
			String belongingS_Treatment_Record) {
		BelongingS_Treatment_Record = belongingS_Treatment_Record;
	}

	public String getRecipient_Person() {
		return Recipient_Person;
	}

	public void setRecipient_Person(String recipient_Person) {
		Recipient_Person = recipient_Person;
	}

	public String getRecipient_Person_Number() {
		return Recipient_Person_Number;
	}

	public void setRecipient_Person_Number(String recipient_Person_Number) {
		Recipient_Person_Number = recipient_Person_Number;
	}

	public Time getTreatment_Time() {
		return Treatment_Time;
	}

	public void setTreatment_Time(Time treatment_Time) {
		Treatment_Time = treatment_Time;
	}

	public int getLeave_Record_ID() {
		return Leave_Record_ID;
	}

	public void setLeave_Record_ID(int leave_Record_ID) {
		Leave_Record_ID = leave_Record_ID;
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

	@Override
	public String toString() {
		return this.Suspect_ID + " " + this.Leave_Reason + " "
				+ this.Recipient_Person;
	}

	public Time getLeave_Hour() {
		return Leave_Hour;
	}

	public void setLeave_Hour(Time leave_Hour) {
		Leave_Hour = leave_Hour;
	}
}
