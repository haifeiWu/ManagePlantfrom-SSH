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

	private int belongingS_ID;
	private String suspect_ID;// 档案编号
	private String belonging_Number;// 物品编号
	private String belonging_Name;// 物品名称
	private String belonging_Character;// 物品特征
	private int belonging_Count;// 物品数量
	private String belonging_Unit;// 物品单位
	private String keeping_ID;// 保管措施（随身物品保管措施字典表中ID逗号隔开）
	private String cabinet_Number;// 保管柜编号
	private String staff_ID;// 办案民警
	private String staff_ID_Belonging;// 随身财物管理民警
	private int room_ID;// 信息登记房间

	private int total_record = 60;// 需要填写的总记录数

	public int getBelongingS_ID() {
		return belongingS_ID;
	}

	public void setBelongingS_ID(int belongingS_ID) {
		this.belongingS_ID = belongingS_ID;
	}

	public String getSuspect_ID() {
		return suspect_ID;
	}

	public void setSuspect_ID(String suspect_ID) {
		this.suspect_ID = suspect_ID;
	}

	public String getBelonging_Number() {
		return belonging_Number;
	}

	public void setBelonging_Number(String belonging_Number) {
		this.belonging_Number = belonging_Number;
	}

	public String getBelonging_Name() {
		return belonging_Name;
	}

	public void setBelonging_Name(String belonging_Name) {
		this.belonging_Name = belonging_Name;
	}

	public String getBelonging_Character() {
		return belonging_Character;
	}

	public void setBelonging_Character(String belonging_Character) {
		this.belonging_Character = belonging_Character;
	}

	public int getBelonging_Count() {
		return belonging_Count;
	}

	public void setBelonging_Count(int belonging_Count) {
		this.belonging_Count = belonging_Count;
	}

	public String getBelonging_Unit() {
		return belonging_Unit;
	}

	public void setBelonging_Unit(String belonging_Unit) {
		this.belonging_Unit = belonging_Unit;
	}

	public String getKeeping_ID() {
		return keeping_ID;
	}

	public void setKeeping_ID(String keeping_ID) {
		this.keeping_ID = keeping_ID;
	}

	public String getCabinet_Number() {
		return cabinet_Number;
	}

	public void setCabinet_Number(String cabinet_Number) {
		this.cabinet_Number = cabinet_Number;
	}

	public String getStaff_ID() {
		return staff_ID;
	}

	public void setStaff_ID(String staff_ID) {
		this.staff_ID = staff_ID;
	}

	public String getStaff_ID_Belonging() {
		return staff_ID_Belonging;
	}

	public void setStaff_ID_Belonging(String staff_ID_Belonging) {
		this.staff_ID_Belonging = staff_ID_Belonging;
	}

	public int getRoom_ID() {
		return room_ID;
	}

	public void setRoom_ID(int room_ID) {
		this.room_ID = room_ID;
	}

	private int fill_record;// 当前填写的记录数

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
		return "物品名称：" + this.belonging_Name + "物品数量：" + this.belonging_Count;
	}

}
