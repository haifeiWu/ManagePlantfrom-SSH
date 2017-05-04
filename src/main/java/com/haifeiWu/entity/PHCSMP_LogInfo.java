package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 日志信息表
 * 
 * @author wuhaifei
 * @d2016年11月29日
 */
public class PHCSMP_LogInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8729816995392362934L;

	private Integer log_ID;
	private String date;
	private String operation_Time;//操作时间
	private String staff_Name;//操作用户
	private String operation_Info;//操作内容
	private String operation_Model;//操作模块
	
	
	
	
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getLog_ID() {
		return log_ID;
	}
	public void setLog_ID(Integer log_ID) {
		this.log_ID = log_ID;
	}
	public String getOperation_Time() {
		return operation_Time;
	}
	public void setOperation_Time(String operation_Time) {
		this.operation_Time = operation_Time;
	}
	public String getStaff_Name() {
		return staff_Name;
	}
	public void setStaff_Name(String staff_Name) {
		this.staff_Name = staff_Name;
	}
	public String getOperation_Info() {
		return operation_Info;
	}
	public void setOperation_Info(String operation_Info) {
		this.operation_Info = operation_Info;
	}
	public String getOperation_Model() {
		return operation_Model;
	}
	public void setOperation_Model(String operation_Model) {
		this.operation_Model = operation_Model;
	}

	
	
	
}
