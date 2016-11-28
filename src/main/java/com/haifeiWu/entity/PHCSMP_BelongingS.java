package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 进入人员随身财物检查记录表
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
public class PHCSMP_BelongingS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5406954408120810405L;

	private int BelongingS_ID;
	private String Suspect_ID;// 档案编号
	private String Belonging_Number;// 物品编号
	private String Belonging_Name;// 物品名称
	private String Belonging_Character;// 物品特征
	private int Belonging_Count;// 物品数量
	private String Belonging_Unit;// 物品单位
	private int Keeping_ID;// 保管措施（随身物品保管措施字典表中ID逗号隔开）
	private String Cabinet_Number;// 保管柜编号
	private int Staff_ID;// 办案民警
	private int Staff_ID_Belonging;// 随身财物管理民警
	private int Room_ID;// 信息登记房间

	private int total_record = 60;// 需要填写的总记录数
	private int fill_record;// 当前填写的记录数

	public String getSuspect_ID() {
		return Suspect_ID;
	}

	public void setSuspect_ID(String suspect_ID) {
		Suspect_ID = suspect_ID;
	}

	public String getBelonging_Number() {
		return Belonging_Number;
	}

	public void setBelonging_Number(String belonging_Number) {
		Belonging_Number = belonging_Number;
	}

	public String getBelonging_Name() {
		return Belonging_Name;
	}

	public void setBelonging_Name(String belonging_Name) {
		Belonging_Name = belonging_Name;
	}

	public String getBelonging_Character() {
		return Belonging_Character;
	}

	public void setBelonging_Character(String belonging_Character) {
		Belonging_Character = belonging_Character;
	}

	public int getBelonging_Count() {
		return Belonging_Count;
	}

	public void setBelonging_Count(int belonging_Count) {
		Belonging_Count = belonging_Count;
	}

	public String getBelonging_Unit() {
		return Belonging_Unit;
	}

	public void setBelonging_Unit(String belonging_Unit) {
		Belonging_Unit = belonging_Unit;
	}

	public int getKeeping_ID() {
		return Keeping_ID;
	}

	public void setKeeping_ID(int keeping_ID) {
		Keeping_ID = keeping_ID;
	}

	public String getCabinet_Number() {
		return Cabinet_Number;
	}

	public void setCabinet_Number(String cabinet_Number) {
		Cabinet_Number = cabinet_Number;
	}

	public int getStaff_ID() {
		return Staff_ID;
	}

	public void setStaff_ID(int staff_ID) {
		Staff_ID = staff_ID;
	}

	public int getStaff_ID_Belonging() {
		return Staff_ID_Belonging;
	}

	public void setStaff_ID_Belonging(int staff_ID_Belonging) {
		Staff_ID_Belonging = staff_ID_Belonging;
	}

	public int getRoom_ID() {
		return Room_ID;
	}

	public void setRoom_ID(int room_ID) {
		Room_ID = room_ID;
	}

	public int getBelongingS_ID() {
		return BelongingS_ID;
	}

	public void setBelongingS_ID(int belongingS_ID) {
		BelongingS_ID = belongingS_ID;
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
		return "物品名称：" + this.Belonging_Name + "物品数量：" + this.Belonging_Count;
	}

}
