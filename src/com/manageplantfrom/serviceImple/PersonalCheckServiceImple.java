package com.manageplantfrom.serviceImple;

import com.manageplantfrom.base.DaoSupportImpl;
import com.manageplantfrom.dao.PersonalCheckDao;
import com.manageplantfrom.daoImple.PersonalCheckDaoImple;
import com.manageplantfrom.entity.PHCSMP_Personal_Check;
import com.manageplantfrom.service.PersonalCheckService;

public class PersonalCheckServiceImple extends DaoSupportImpl<PHCSMP_Personal_Check> implements PersonalCheckService {
	
	private PersonalCheckDao dao = new PersonalCheckDaoImple();

	@Override
	public void saveCheckPersonInfor(PHCSMP_Personal_Check model) {
		dao.save(model);
	}
}
