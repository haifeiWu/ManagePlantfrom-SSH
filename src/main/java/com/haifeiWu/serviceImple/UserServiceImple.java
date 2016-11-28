package com.haifeiWu.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.UserDao;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.service.UserService;

@Service("userService")
public class UserServiceImple implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public PHCSMP_Staff findUserByStaffNameAndPwd(String staff_Name,
			String passWord) {
		return userDao.findUserByStaffNameAndPwd(staff_Name, passWord);
	}

}
