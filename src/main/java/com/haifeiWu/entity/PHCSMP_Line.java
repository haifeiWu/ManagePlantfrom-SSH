package com.haifeiWu.entity;

import java.io.Serializable;
/**
 * 回路信息表
 * @author WXY
 *
 */
public class PHCSMP_Line implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6801480855932409636L;
	private int line_Count;//回路总数
	private int line_Used;//已使用回路数，默认0
	
	
	
	public int getLine_Count() {
		return line_Count;
	}
	public void setLine_Count(int line_Count) {
		this.line_Count = line_Count;
	}
	public int getLine_Used() {
		return line_Used;
	}
	public void setLine_Used(int line_Used) {
		this.line_Used = line_Used;
	}
	
	
	
	
	
	
	
}
