package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_Role;
import com.haifeiWu.entity.PHCSMP_Staff;

/**
 * 用户service
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
public interface UserService {

	/**
	 * 根据用户名密码查找用户，用于用户登录
	 * 
	 * @param staff_Name
	 *            用户名
	 * @param passWord
	 *            密码
	 * @return
	 */
	PHCSMP_Staff findUserByStaffNameAndPwd(String staff_Name, String passWord);

	public List<PHCSMP_Staff> findAllStaffs();

	public void saveStaff(PHCSMP_Staff staff);

	/***
	 * 删除用户
	 * 
	 * @param staff
	 */
	void deleteByStaffId(int id);

	/**
	 * 根据用户名即工号，或者干警的真实姓名查询干警
	 * 
	 * @param key
	 * @param val
	 * @return
	 */
	List<PHCSMP_Staff> findStaff(String key, String val);

	/**
	 * 遍历所有的干警
	 * 
	 * @return
	 */

	List<PHCSMP_Role> findAllRole();

	void updateStaff(PHCSMP_Staff model);
}
