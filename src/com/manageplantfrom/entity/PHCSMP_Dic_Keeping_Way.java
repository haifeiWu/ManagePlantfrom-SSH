package com.manageplantfrom.entity;

/**
 * 随身物品保管措施字典表
 * （扣押、暂存、代保管）
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class PHCSMP_Dic_Keeping_Way {
	private int Keeping_ID;
	private String Keeping_Name;
	
	public int getKeeping_ID() {
		return Keeping_ID;
	}
	public void setKeeping_ID(int keeping_ID) {
		Keeping_ID = keeping_ID;
	}
	public String getKeeping_Name() {
		return Keeping_Name;
	}
	public void setKeeping_Name(String keeping_Name) {
		Keeping_Name = keeping_Name;
	}
}
