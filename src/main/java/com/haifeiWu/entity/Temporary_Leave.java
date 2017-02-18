package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 临时离开原因
 * 
 * @author wuhaifei
 * @d2016年11月8日
 */
public class Temporary_Leave implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3697808440728790236L;

	private int temporary_Leave_Id;
	private String suspect_ID;// 档案编号
	private String tempLeave_Time;// 临时离开时间 1
	private String tempLeave_Reason;// 临时离开原因 2
	private String return_Time;// 返回时间 3
	private String staff_ID;// 办案民警 4
	private int room_ID;// 信息登记房间 5

	public int getTemporary_Leave_Id() {
		return temporary_Leave_Id;
	}

	public void setTemporary_Leave_Id(int temporary_Leave_Id) {
		this.temporary_Leave_Id = temporary_Leave_Id;
	}

	public String getSuspect_ID() {
		return suspect_ID;
	}

	public void setSuspect_ID(String suspect_ID) {
		this.suspect_ID = suspect_ID;
	}

	public String getTempLeave_Time() {
		return tempLeave_Time;
	}

	public void setTempLeave_Time(String tempLeave_Time) {
		this.tempLeave_Time = tempLeave_Time;
	}

	public String getTempLeave_Reason() {
		return tempLeave_Reason;
	}

	public void setTempLeave_Reason(String tempLeave_Reason) {
		this.tempLeave_Reason = tempLeave_Reason;
	}

	public String getReturn_Time() {
		return return_Time;
	}

	public void setReturn_Time(String return_Time) {
		this.return_Time = return_Time;
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

	@Override
	public String toString() {// 重写toString方法

		return "临时离开原因：" + this.tempLeave_Reason + "档案号：" + this.suspect_ID;
	}
}
