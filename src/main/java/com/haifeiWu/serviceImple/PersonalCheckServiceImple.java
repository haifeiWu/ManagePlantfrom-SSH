package com.haifeiWu.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.PersonalCheckDao;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.service.PersonalCheckService;

/**
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
@Service("personalCheckService")
public class PersonalCheckServiceImple implements PersonalCheckService {

	@Autowired
	private PersonalCheckDao personalCheckDao;

	@Override
	public void saveCheckPersonInfor(PHCSMP_Personal_Check model) {
		personalCheckDao.save(model);
	}

	@Override
	public PHCSMP_Personal_Check findInforBySuspetcId(String suspectId) {
		return personalCheckDao.findInforBySuspetcId(suspectId);
	}
}
