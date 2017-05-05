package com.haifeiWu.entity;

import java.io.Serializable;

/**
 * 用户信息表
 * 
 * @author wuhaifei
 * 
 * @String 2016年8月9日
 */
public class PHCSMP_Staff implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 470766277566626728L;

	private int Staff_ID;// 用户ID（主键）
	private String Staff_Name;// 用户名（默认工号）
	private String PassWord;// 密码
	private String real_Name;// 真实姓名
	private String Sex;// 性别
	private String Duties_Name;// 职务
	private String role_Name;// 权限，普通干警高级干警和管理员
	private String Email;// 邮箱
	private String RegistedDate;// 注册日期
	private String Is_Dimission;// 是否离职
	private String Phone;// 移动电话
	private String Mobile;// 固话
	private String Is_FirstLogin;// 是否第一次登录

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

	public String getReal_Name() {
		return real_Name;
	}

	public void setReal_Name(String real_Name) {
		this.real_Name = real_Name;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getDuties_Name() {
		return Duties_Name;
	}

	public void setDuties_Name(String duties_Name) {
		Duties_Name = duties_Name;
	}

	public String getRole_Name() {
		return role_Name;
	}

	public void setRole_Name(String role_Name) {
		this.role_Name = role_Name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getRegistedDate() {
		return RegistedDate;
	}

	public void setRegistedDate(String registedDate) {
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

	@Override
	public String toString() {
		return "PHCSMP_Staff [Staff_ID=" + Staff_ID + ", Staff_Name="
				+ Staff_Name + ", PassWord=" + PassWord + ", real_Name="
				+ real_Name + ", Sex=" + Sex + ", Duties_Name=" + Duties_Name
				+ ", Email=" + Email + ", RegistedDate=" + RegistedDate
				+ ", Is_Dimission=" + Is_Dimission + ", Phone=" + Phone
				+ ", Mobile=" + Mobile + ", Is_FirstLogin=" + Is_FirstLogin
				+ "]";
	}

	public PHCSMP_Staff() {
	}

}
