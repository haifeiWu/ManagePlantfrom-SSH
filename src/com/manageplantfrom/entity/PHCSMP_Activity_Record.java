package com.manageplantfrom.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 询问、讯问等活动记录表
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class PHCSMP_Activity_Record implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private int Activity_Record_ID;
	
	private String Suspect_ID;//档案编号
	private Date Start_Time;//开始时间
	private Date End_Time;//结束时间
	private int Room_ID;//房间ID
	private String Activity_Record;//活动内容
	private String Vedio_Number;//音频视频编码
	private String Remark;//备注
	private String StaffS;//办案民警ID（多个民警ID间逗号间隔）
	
	private int total_record = 60;//需要填写的总记录数
	private int fill_record;//当前填写的记录数
	
	
	
	public String getSuspect_ID() {
		return Suspect_ID;
	}
	public void setSuspect_ID(String suspect_ID) {
		Suspect_ID = suspect_ID;
	}
	public Date getStart_Time() {
		return Start_Time;
	}
	public void setStart_Time(Date start_Time) {
		Start_Time = start_Time;
	}
	public Date getEnd_Time() {
		return End_Time;
	}
	public void setEnd_Time(Date end_Time) {
		End_Time = end_Time;
	}
	public int getRoom_ID() {
		return Room_ID;
	}
	public void setRoom_ID(int room_ID) {
		Room_ID = room_ID;
	}
	public String getActivity_Record() {
		return Activity_Record;
	}
	public void setActivity_Record(String activity_Record) {
		Activity_Record = activity_Record;
	}
	public String getVedio_Number() {
		return Vedio_Number;
	}
	public void setVedio_Number(String vedio_Number) {
		Vedio_Number = vedio_Number;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getStaffS() {
		return StaffS;
	}
	public void setStaffS(String staffS) {
		StaffS = staffS;
	}
	public int getActivity_Record_ID() {
		return Activity_Record_ID;
	}
	public void setActivity_Record_ID(int activity_Record_ID) {
		Activity_Record_ID = activity_Record_ID;
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
		return this.Suspect_ID+" "+this.Activity_Record+" "+this.Vedio_Number;
	}
	
}
