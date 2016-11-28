package com.haifeiWu.entity;

import java.io.Serializable;

// 日志信息表 -->
public class PHCSMP_LogInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8729816995392362934L;

	private int Log_ID;
	private String Operation_Time;
	private String Staff_Name;
	private String Operation_Info;
	private String Operation_Model;

	public PHCSMP_LogInfo(int log_ID, String operation_Time, String staff_Name,
			String operation_Info, String operation_Model) {
		super();
		Log_ID = log_ID;
		Operation_Time = operation_Time;
		Staff_Name = staff_Name;
		Operation_Info = operation_Info;
		Operation_Model = operation_Model;
	}

	public int getLog_ID() {
		return Log_ID;
	}

	public void setLog_ID(int log_ID) {
		Log_ID = log_ID;
	}

	public String getOperation_Time() {
		return Operation_Time;
	}

	public void setOperation_Time(String operation_Time) {
		Operation_Time = operation_Time;
	}

	public String getStaff_Name() {
		return Staff_Name;
	}

	public void setStaff_Name(String staff_Name) {
		Staff_Name = staff_Name;
	}

	public String getOperation_Info() {
		return Operation_Info;
	}

	public void setOperation_Info(String operation_Info) {
		Operation_Info = operation_Info;
	}

	public String getOperation_Model() {
		return Operation_Model;
	}

	public void setOperation_Model(String operation_Model) {
		Operation_Model = operation_Model;
	}

	public PHCSMP_LogInfo() {
	}

}
