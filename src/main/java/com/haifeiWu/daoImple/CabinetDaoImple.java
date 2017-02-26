package com.haifeiWu.daoImple;

import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.CabinetDao;
import com.haifeiWu.entity.PHCSMP_Cabinet;

@Repository("cabinetDao")
public class CabinetDaoImple extends DaoSupportImpl<PHCSMP_Cabinet> implements CabinetDao {
	
}
