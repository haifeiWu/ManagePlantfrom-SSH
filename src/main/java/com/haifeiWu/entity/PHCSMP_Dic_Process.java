package com.haifeiWu.entity;

import java.io.Serializable;
/**
 * 
 * @author WXY
 *
 */
public class PHCSMP_Dic_Process implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7409101350204091326L;
	
	private int process_ID;//流程ID
	private String process_Name;//流程名称
	
	public int getProcess_ID() {
		return process_ID;
	}
	public void setProcess_ID(int process_ID) {
		this.process_ID = process_ID;
	}
	public String getProcess_Name() {
		return process_Name;
	}
	public void setProcess_Name(String process_Name) {
		this.process_Name = process_Name;
	}
	
}
