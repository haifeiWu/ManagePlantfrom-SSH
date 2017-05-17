package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 临时离开原因
 * 
 * @author wuhaifei
 * @d2016年11月8日
 */
public class Temporary_Leave implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3697808440728790236L;

	private int temporary_Leave_Id;
	private String suspect_ID;// 档案编号
	private String tempLeave_Time;// 临时离开时间 1
	private String tempLeave_Reason;// 临时离开原因 2
	private String return_Time;// 返回时间 3
	private int tempLeave_staff_ID;// 办案民警 4
	private int return_staff_ID;
	private int room_ID;// 信息登记房间 5
	private int tempLeave_manager;// 管理员
	private int return_manager;

	public Temporary_Leave() {
		super();
	}

	

	public Temporary_Leave( String suspect_ID,
			String tempLeave_Time, String tempLeave_Reason, String return_Time,
			int tempLeave_staff_ID, int return_staff_ID, int room_ID,
			int tempLeave_manager, int return_manager) {
		super();
		this.suspect_ID = suspect_ID;
		this.tempLeave_Time = tempLeave_Time;
		this.tempLeave_Reason = tempLeave_Reason;
		this.return_Time = return_Time;
		this.tempLeave_staff_ID = tempLeave_staff_ID;
		this.return_staff_ID = return_staff_ID;
		this.room_ID = room_ID;
		this.tempLeave_manager = tempLeave_manager;
		this.return_manager = return_manager;
	}



	public int getTemporary_Leave_Id() {
		return temporary_Leave_Id;
	}

	public void setTemporary_Leave_Id(int temporary_Leave_Id) {
		this.temporary_Leave_Id = temporary_Leave_Id;
	}

	public String getSuspect_ID() {
		return suspect_ID;
	}

	public void setSuspect_ID(String suspect_ID) {
		this.suspect_ID = suspect_ID;
	}

	public String getTempLeave_Time() {
		return tempLeave_Time;
	}

	public void setTempLeave_Time(String tempLeave_Time) {
		this.tempLeave_Time = tempLeave_Time;
	}

	public String getTempLeave_Reason() {
		return tempLeave_Reason;
	}

	public void setTempLeave_Reason(String tempLeave_Reason) {
		this.tempLeave_Reason = tempLeave_Reason;
	}

	public String getReturn_Time() {
		return return_Time;
	}

	public void setReturn_Time(String return_Time) {
		this.return_Time = return_Time;
	}

	
	public int getRoom_ID() {
		return room_ID;
	}

	public void setRoom_ID(int room_ID) {
		this.room_ID = room_ID;
	}

	


	public int getTempLeave_staff_ID() {
		return tempLeave_staff_ID;
	}



	public void setTempLeave_staff_ID(int tempLeave_staff_ID) {
		this.tempLeave_staff_ID = tempLeave_staff_ID;
	}



	public int getReturn_staff_ID() {
		return return_staff_ID;
	}



	public void setReturn_staff_ID(int return_staff_ID) {
		this.return_staff_ID = return_staff_ID;
	}



	public int getTempLeave_manager() {
		return tempLeave_manager;
	}



	public void setTempLeave_manager(int tempLeave_manager) {
		this.tempLeave_manager = tempLeave_manager;
	}



	public int getReturn_manager() {
		return return_manager;
	}



	public void setReturn_manager(int return_manager) {
		this.return_manager = return_manager;
	}



	@Override
	public String toString() {
		return "Temporary_Leave [temporary_Leave_Id=" + temporary_Leave_Id
				+ ", suspect_ID=" + suspect_ID + ", tempLeave_Time="
				+ tempLeave_Time + ", tempLeave_Reason=" + tempLeave_Reason
				+ ", return_Time=" + return_Time + ", tempLeave_staff_ID="
				+ tempLeave_staff_ID + ", return_staff_ID=" + return_staff_ID
				+ ", room_ID=" + room_ID + ", tempLeave_manager="
				+ tempLeave_manager + ", return_manager=" + return_manager
				+ "]";
	}

	

	// @Override
	// public String toString() {
	// return "Temporary_Leave [temporary_Leave_Id=" + temporary_Leave_Id
	// + ", suspect_ID=" + suspect_ID + ", tempLeave_Time="
	// + tempLeave_Time + ", tempLeave_Reason=" + tempLeave_Reason
	// + ", return_Time=" + return_Time + ", staff_ID=" + staff_ID
	// + ", room_ID=" + room_ID + "]";
	// }

	
	//

}
