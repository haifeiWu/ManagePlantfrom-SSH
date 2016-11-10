package com.manageplantfrom.entity;

import java.io.Serializable;

/**
 * 保管柜信息表
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class PHCSMP_Cabinet implements Serializable {
	
	private int Cabinet_ID;
	private String Cabinet_Number;//保管柜编号（主键）
	private String Cabinet_Desc;//保管柜描述
	
	public String getCabinet_Number() {
		return Cabinet_Number;
	}
	public void setCabinet_Number(String cabinet_Number) {
		Cabinet_Number = cabinet_Number;
	}
	public String getCabinet_Desc() {
		return Cabinet_Desc;
	}
	public void setCabinet_Desc(String cabinet_Desc) {
		Cabinet_Desc = cabinet_Desc;
	}
	public int getCabinet_ID() {
		return Cabinet_ID;
	}
	public void setCabinet_ID(int cabinet_ID) {
		Cabinet_ID = cabinet_ID;
	}
	
}
