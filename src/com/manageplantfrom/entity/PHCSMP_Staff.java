package com.manageplantfrom.entity;

import java.util.Date;

/**
 * 用户信息表
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class PHCSMP_Staff {
	private int Staff_ID;//用户ID（主键）
	private String Staff_Name;//用户名（默认工号）
	private String PassWord;//密码
	private String real_Name;//真实姓名
	private String Sex;//性别
	private int Duties_ID;//职务ID
	private String Email;//邮箱
	private Date RegistedDate;//注册日期
	private String Is_Dimission;//是否离职
	private String Phone;//移动电话
	private String Mobile;//固话
	private String Is_FirstLogin;//是否第一次登录
	
	public int getStaff_ID() {
		return Staff_ID;
	}
	public void setStaff_ID(int staff_ID) {
		Staff_ID = staff_ID;
	}
	public String getStaff_Name() {
		return Staff_Name;
	}
	public void setStaff_Name(String staff_Name) {
		Staff_Name = staff_Name;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public int getDuties_ID() {
		return Duties_ID;
	}
	public void setDuties_ID(int duties_ID) {
		Duties_ID = duties_ID;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Date getRegistedDate() {
		return RegistedDate;
	}
	public void setRegistedDate(Date registedDate) {
		RegistedDate = registedDate;
	}
	public String getIs_Dimission() {
		return Is_Dimission;
	}
	public void setIs_Dimission(String is_Dimission) {
		Is_Dimission = is_Dimission;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getIs_FirstLogin() {
		return Is_FirstLogin;
	}
	public void setIs_FirstLogin(String is_FirstLogin) {
		Is_FirstLogin = is_FirstLogin;
	}
	public String getReal_Name() {
		return real_Name;
	}
	public void setReal_Name(String real_Name) {
		this.real_Name = real_Name;
	}
}
