package com.manageplantfrom.service;

import com.manageplantfrom.entity.PHCSMP_Staff;

public interface UserService {

	/**
	 * 根据用户名密码查找用户，用于用户登录
	 * @param staff_Name 用户名
	 * @param passWord 密码
	 * @return
	 */
	PHCSMP_Staff findUserByStaffNameAndPwd(String staff_Name, String passWord);

}
