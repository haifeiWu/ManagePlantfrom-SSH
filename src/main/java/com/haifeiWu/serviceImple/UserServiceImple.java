package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.UserDao;
import com.haifeiWu.entity.PHCSMP_Role;
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

	@Override
	public List<PHCSMP_Staff> findAllStaffs() {
		return userDao.findAllStaffs();
	}

	@Override
	public void saveStaff(PHCSMP_Staff staff) {
		userDao.saveStaff(staff);
	}

	@Override
	public List<PHCSMP_Staff> findStaff(String key, String val) {
		List<PHCSMP_Staff> list = userDao.search(key, val);
		return list;
	}

	@Override
	public void deleteByStaffId(int id) {
		userDao.deleteByStaffId(id);

	}

	@Override
	public List<PHCSMP_Role> findAllRole() {
		// TODO Auto-generated method stub
		return userDao.findAllRole();
	}

	@Override
	public void updateStaff(PHCSMP_Staff model) {
		// TODO Auto-generated method stub
		userDao.updateStaff(model);
	}

}
