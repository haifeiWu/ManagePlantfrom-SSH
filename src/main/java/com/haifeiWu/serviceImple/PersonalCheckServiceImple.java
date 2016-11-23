package com.haifeiWu.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.PersonalCheckDao;
import com.haifeiWu.daoImple.PersonalCheckDaoImple;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.service.PersonalCheckService;

@Service("personalCheckService")
public class PersonalCheckServiceImple implements PersonalCheckService {
	
	@Autowired
	private PersonalCheckDao personalCheckDao;

	@Override
	public void saveCheckPersonInfor(PHCSMP_Personal_Check model) {
		personalCheckDao.save(model);
	}
}
