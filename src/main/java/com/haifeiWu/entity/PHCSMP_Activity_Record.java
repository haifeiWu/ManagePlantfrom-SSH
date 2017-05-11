package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 询问、讯问等活动记录表
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_Activity_Record implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5853896025376141947L;

	private int activity_Record_ID;

	private String suspect_ID;// 档案编号
	private String start_Time;// 开始时间
	private String end_Time;// 结束时间
	private int room_ID;// 房间ID
	private String activity_Record;// 活动内容
	// private String vedio_Number;// 音频视频编码
	private String remark;// 备注
	private Integer staff_ID;// 办案民警ID（多个民警ID间逗号间隔）

	private int total_record = 60;// 需要填写的总记录数
	private int fill_record;// 当前填写的记录数

	public int getActivity_Record_ID() {
		return activity_Record_ID;
	}

	public void setActivity_Record_ID(int activity_Record_ID) {
		this.activity_Record_ID = activity_Record_ID;
	}

	public String getSuspect_ID() {
		return suspect_ID;
	}

	public void setSuspect_ID(String suspect_ID) {
		this.suspect_ID = suspect_ID;
	}

	public String getStart_Time() {
		return start_Time;
	}

	public void setStart_Time(String start_Time) {
		this.start_Time = start_Time;
	}

	public String getEnd_Time() {
		return end_Time;
	}

	public void setEnd_Time(String end_Time) {
		this.end_Time = end_Time;
	}

	public int getRoom_ID() {
		return room_ID;
	}

	public void setRoom_ID(int room_ID) {
		this.room_ID = room_ID;
	}

	public String getActivity_Record() {
		return activity_Record;
	}

	public void setActivity_Record(String activity_Record) {
		this.activity_Record = activity_Record;
	}

	/*
	 * public String getVedio_Number() { return vedio_Number; }
	 * 
	 * public void setVedio_Number(String vedio_Number) { this.vedio_Number =
	 * vedio_Number; }
	 */

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStaff_ID() {
		return staff_ID;
	}

	public void setStaff_ID(Integer staff_ID) {
		this.staff_ID = staff_ID;
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
		return "PHCSMP_Activity_Record [activity_Record_ID="
				+ activity_Record_ID + ", suspect_ID=" + suspect_ID
				+ ", start_Time=" + start_Time + ", end_Time=" + end_Time
				+ ", room_ID=" + room_ID + ", activity_Record="
				+ activity_Record + ", remark=" + remark + ", staff_ID="
				+ staff_ID + ", total_record=" + total_record
				+ ", fill_record=" + fill_record + "]";
	}

	/*
	 * @Override public String toString() { return
	 * "PHCSMP_Activity_Record [activity_Record_ID=" + activity_Record_ID +
	 * ", suspect_ID=" + suspect_ID + ", start_Time=" + start_Time +
	 * ", end_Time=" + end_Time + ", room_ID=" + room_ID + ", activity_Record="
	 * + activity_Record + ", vedio_Number=" + vedio_Number + ", remark=" +
	 * remark + ", staffS=" + staffS + ", total_record=" + total_record +
	 * ", fill_record=" + fill_record + "]"; }
	 */

	// @Override
	// public String toString() {
	// return this.suspect_ID + " " + this.start_Time + " " + this.end_Time;
	// }

}
