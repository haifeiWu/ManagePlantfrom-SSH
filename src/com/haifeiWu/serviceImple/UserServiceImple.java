package com.haifeiWu.serviceImple;

import com.haifeiWu.dao.UserDao;
import com.haifeiWu.daoImple.UserDaoImple;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.service.UserService;

public class UserServiceImple implements UserService {

	private UserDao userDao = new UserDaoImple();
	
	@Override
	public PHCSMP_Staff findUserByStaffNameAndPwd(String staff_Name,
			String passWord) {
		return userDao.findUserByStaffNameAndPwd(staff_Name,passWord);
	}

}
