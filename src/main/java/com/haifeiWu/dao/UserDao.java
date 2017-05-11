package com.haifeiWu.dao;

import java.util.List;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_Role;
import com.haifeiWu.entity.PHCSMP_Staff;

public interface UserDao extends DaoSupport<PHCSMP_Staff> {

	/**
	 * 根据用户名查找用户
	 * 
	 * @param staff_Name
	 *            用户名
	 * @param passWord
	 *            密码
	 * @return
	 */
	PHCSMP_Staff findUserByStaffNameAndPwd(String staff_Name, String passWord);

	/**
	 * 查找数据库中的所有的用户数据
	 * 
	 * @return
	 */
	public List<PHCSMP_Staff> findAllStaffs();

	/**
	 * 保存用户信息
	 * 
	 * */
	void saveStaff(PHCSMP_Staff staff);

	/**
	 * 根据某个参数查找用户
	 * 
	 * @return
	 */
	List<PHCSMP_Staff> search(String key, String val);

	/**
	 * 根据id删除用户
	 * 
	 * @return
	 */
	void deleteByStaffId(int id);

	public List<PHCSMP_Role> findAllRole();

	void updateStaff(PHCSMP_Staff model);

	PHCSMP_Staff findstaffById(int staffid);
}
