package com.haifeiWu.serviceImple;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.PersonalCheckDao;
import com.haifeiWu.daoImple.PersonalCheckDaoImple;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.service.PersonalCheckService;

public class PersonalCheckServiceImple extends DaoSupportImpl<PHCSMP_Personal_Check> implements PersonalCheckService {
	
	private PersonalCheckDao dao = new PersonalCheckDaoImple();

	@Override
	public void saveCheckPersonInfor(PHCSMP_Personal_Check model) {
		dao.save(model);
	}
}
