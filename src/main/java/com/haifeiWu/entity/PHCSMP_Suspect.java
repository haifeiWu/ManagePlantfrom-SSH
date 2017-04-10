package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 进入人员基本信息表
 * 
 * @author wuhaifei
 * 
 * @String 2016年8月9日
 */
public class PHCSMP_Suspect implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5439411933114979140L;

	private int id;
	private String suspect_ID;// 档案编号（主键）
	private int band_ID;// 手环ID
	private String suspect_Name;// 姓名
	private String sex;// 性别
	private String birthday;// 出生日期
	private String nation;// 民族
	private String phone;// 手机号码
	private String type_ID;// 证件类型
	private String identifyCard_Number;// 证件号码
	private String suspected_Cause;// 进入办案区原因（案由字典表中ID用逗号分隔）
	private String enter_Time;// 进入办案区时间
	private String detain_Time;// 羁押时间
	
	private String vedio_Number;// 音频视频编码

	// private String quit_time;// 离开信息登记室时间
	private String staff_ID;// 办案民警,此处的办案民警是哪个流程的民警，还是多个隔开
	// 此记录数据字典中没有，用来记录嫌疑人个人信息
	private String address;// 家庭住址
	private String now_address;// 家庭住址
	// private int is_Active;// 是否激活（默认未激活0），这个是房间号，取消该属性
	// /private String identityID_Imag;// 身份证照片的url,冲突
	// private String take_Picture;// 现场拍摄照片的url，冲突
	// private int is_OutOf;// 是否出区，代表该嫌疑人是历史嫌疑人 ,有疑问

	// 用来记录填写完整度
	private int total_record;// 需要填写的总记录数
	private int fill_record;// 当前填写的记录数

	// wxy增加V1.2版本的字段
	private int process_Now;// 当前所处的流程状态，对应PHCSMP_Dic_Process表中的Process_ID，整个流程结束后置为-1
	private int room_Now;// 当前涉案人员所处的房间编号，对应PHCSMP_Room表中的Room_ID
	private String identityCard_Photo;// 对应数据库中的Blob数据类型，用来存储身份证照片
	private String frontal_Photo;// 正面照图像文件地址
	private String sideWays_Photo;// 侧面照图像文件地址
	private int recordVideo_State;// 录像状态，0：不进行录像 1：录像还未启动 2：录像已经启动 3：录像暂停
									// 4：录像结束
	private int is_RecordVideo_DownLoad;// 录像文件是否下载成功，0没有下载成功，1下载成功
	private int cardReader_Switch;// 3-4新增属性默认是0，，判断0 发进指令 ，，，1发出的指令

	// 此属性待定
	// wxy增加刷卡时的指令为开始还是暂停
	// private int startOrPauseVideo;// 0，发开始指令 1发暂停指令

	public int getCardReader_Switch() {
		return cardReader_Switch;
	}

	public void setCardReader_Switch(int cardReader_Switch) {
		this.cardReader_Switch = cardReader_Switch;
	}

	public int getProcess_Now() {
		return process_Now;
	}

	public void setProcess_Now(int process_Now) {
		this.process_Now = process_Now;
	}

	public int getRoom_Now() {
		return room_Now;
	}

	public void setRoom_Now(int room_Now) {
		this.room_Now = room_Now;
	}

	public String getFrontal_Photo() {
		return frontal_Photo;
	}

	public void setFrontal_Photo(String frontal_Photo) {
		this.frontal_Photo = frontal_Photo;
	}

	public String getSideWays_Photo() {
		return sideWays_Photo;
	}

	public void setSideWays_Photo(String sideWays_Photo) {
		this.sideWays_Photo = sideWays_Photo;
	}

	public int getRecordVideo_State() {
		return recordVideo_State;
	}

	public void setRecordVideo_State(int recordVideo_State) {
		this.recordVideo_State = recordVideo_State;
	}

	public int getIs_RecordVideo_DownLoad() {
		return is_RecordVideo_DownLoad;
	}

	public void setIs_RecordVideo_DownLoad(int is_RecordVideo_DownLoad) {
		this.is_RecordVideo_DownLoad = is_RecordVideo_DownLoad;
	}

	public String getNow_address() {
		return now_address;
	}

	public void setNow_address(String now_address) {
		this.now_address = now_address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getSuspect_ID() {
		return suspect_ID;
	}

	public void setSuspect_ID(String suspect_ID) {
		this.suspect_ID = suspect_ID;
	}

	public String getEnter_Time() {
		return enter_Time;
	}

	public void setEnter_Time(String enter_Time) {
		this.enter_Time = enter_Time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBand_ID() {
		return band_ID;
	}

	public void setBand_ID(int band_ID) {
		this.band_ID = band_ID;
	}

	public String getSuspect_Name() {
		return suspect_Name;
	}

	public void setSuspect_Name(String suspect_Name) {
		this.suspect_Name = suspect_Name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType_ID() {
		return type_ID;
	}

	public void setType_ID(String type_ID) {
		this.type_ID = type_ID;
	}

	public String getIdentifyCard_Number() {
		return identifyCard_Number;
	}

	public void setIdentifyCard_Number(String identifyCard_Number) {
		this.identifyCard_Number = identifyCard_Number;
	}

	public String getSuspected_Cause() {
		return suspected_Cause;
	}

	public void setSuspected_Cause(String suspected_Cause) {
		this.suspected_Cause = suspected_Cause;
	}

	public String getStaff_ID() {
		return staff_ID;
	}

	public void setStaff_ID(String staff_ID) {
		this.staff_ID = staff_ID;
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

	/*@Override
	public String toString() {
		return "PHCSMP_Suspect [id=" + id + ", suspect_ID=" + suspect_ID
				+ ", band_ID=" + band_ID + ", suspect_Name=" + suspect_Name
				+ ", sex=" + sex + ", birthday=" + birthday + ", nation="
				+ nation + ", phone=" + phone + ", type_ID=" + type_ID
				+ ", identifyCard_Number=" + identifyCard_Number
				+ ", suspected_Cause=" + suspected_Cause + ", enter_Time="
				+ enter_Time + ", staff_ID=" + staff_ID + ", address="
				+ address + ", now_address=" + now_address + ", total_record="
				+ total_record + ", fill_record=" + fill_record
				+ ", process_Now=" + process_Now + ", room_Now=" + room_Now
				+ ", identityCard_Photo=" + identityCard_Photo
				+ ", frontal_Photo=" + frontal_Photo + ", sideWays_Photo="
				+ sideWays_Photo + ", recordVideo_State=" + recordVideo_State
				+ ", is_RecordVideo_DownLoad=" + is_RecordVideo_DownLoad
				+ ", cardReader_Switch=" + cardReader_Switch + "]";
	}*/

	public String getIdentityCard_Photo() {
		return identityCard_Photo;
	}

	@Override
	public String toString() {
		return "PHCSMP_Suspect [id=" + id + ", suspect_ID=" + suspect_ID
				+ ", band_ID=" + band_ID + ", suspect_Name=" + suspect_Name
				+ ", sex=" + sex + ", birthday=" + birthday + ", nation="
				+ nation + ", phone=" + phone + ", type_ID=" + type_ID
				+ ", identifyCard_Number=" + identifyCard_Number
				+ ", suspected_Cause=" + suspected_Cause + ", enter_Time="
				+ enter_Time + ", detain_Time=" + detain_Time
				+ ", vedio_Number=" + vedio_Number + ", staff_ID=" + staff_ID
				+ ", address=" + address + ", now_address=" + now_address
				+ ", total_record=" + total_record + ", fill_record="
				+ fill_record + ", process_Now=" + process_Now + ", room_Now="
				+ room_Now + ", identityCard_Photo=" + identityCard_Photo
				+ ", frontal_Photo=" + frontal_Photo + ", sideWays_Photo="
				+ sideWays_Photo + ", recordVideo_State=" + recordVideo_State
				+ ", is_RecordVideo_DownLoad=" + is_RecordVideo_DownLoad
				+ ", cardReader_Switch=" + cardReader_Switch + "]";
	}

	public void setIdentityCard_Photo(String identityCard_Photo) {
		this.identityCard_Photo = identityCard_Photo;
	}

	public String getDetain_Time() {
		return detain_Time;
	}

	public void setDetain_Time(String detain_Time) {
		this.detain_Time = detain_Time;
	}

	public String getVedio_Number() {
		return vedio_Number;
	}

	public void setVedio_Number(String vedio_Number) {
		this.vedio_Number = vedio_Number;
	}

}
