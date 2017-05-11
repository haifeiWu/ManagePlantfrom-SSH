package com.haifeiWu.entity;

import java.io.Serializable;
/**
 * 警务业务作业日志表
 * @author WXY
 *
 */
public class PHCSMP_Process_Log implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1679947925598059057L;
	
	private int log_ID;//业务日志ID（主键）
	private String suspect_ID;//档案编号
	private int process_ID;//流程ID
	private String suspect_active;
	private String date;
	private String start_Time;//开始时间
	private String end_Time;//结束时间
	private int staff_ID;//操作用户
	private String iP_Address;//操作IP地址
	
	
	
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSuspect_active() {
		return suspect_active;
	}
	public void setSuspect_active(String suspect_active) {
		this.suspect_active = suspect_active;
	}
	public int getLog_ID() {
		return log_ID;
	}
	public void setLog_ID(int log_ID) {
		this.log_ID = log_ID;
	}
	public String getSuspect_ID() {
		return suspect_ID;
	}
	public void setSuspect_ID(String suspect_ID) {
		this.suspect_ID = suspect_ID;
	}
	public int getProcess_ID() {
		return process_ID;
	}
	public void setProcess_ID(int process_ID) {
		this.process_ID = process_ID;
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
	
	
	
	public int getStaff_ID() {
		return staff_ID;
	}
	public void setStaff_ID(int staff_ID) {
		this.staff_ID = staff_ID;
	}
	public String getiP_Address() {
		return iP_Address;
	}
	public void setiP_Address(String iP_Address) {
		this.iP_Address = iP_Address;
	}
	
	
	
}
