package com.manageplantfrom.dao;

import com.manageplantfrom.entity.PHCSMP_Staff;

public interface UserDao {
	
	/**
	 * 根据用户名查找用户
	 * @param staff_Name 用户名
	 * @param passWord 密码
	 * @return
	 */
	PHCSMP_Staff findUserByStaffNameAndPwd(String staff_Name, String passWord);

}
