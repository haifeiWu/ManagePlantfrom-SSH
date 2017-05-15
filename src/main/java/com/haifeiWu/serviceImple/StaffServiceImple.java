package com.haifeiWu.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.StaffDao;
import com.haifeiWu.service.StaffService;

@Service("staffService")
public class StaffServiceImple implements StaffService {

	@Autowired
	private StaffDao staffDao;
	
	@Override
	public String getStaffName(int staffid) {
		try {
			return staffDao.getStaffName(staffid);
		} catch (Exception e) {
			return null;
		}
		
	}

}
