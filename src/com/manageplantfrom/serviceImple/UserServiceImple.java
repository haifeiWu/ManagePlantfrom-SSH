package com.manageplantfrom.serviceImple;

import com.manageplantfrom.dao.UserDao;
import com.manageplantfrom.daoImple.UserDaoImple;
import com.manageplantfrom.entity.PHCSMP_Staff;
import com.manageplantfrom.service.UserService;

public class UserServiceImple implements UserService {

	private UserDao userDao = new UserDaoImple();
	
	@Override
	public PHCSMP_Staff findUserByStaffNameAndPwd(String staff_Name,
			String passWord) {
		return userDao.findUserByStaffNameAndPwd(staff_Name,passWord);
	}

}
