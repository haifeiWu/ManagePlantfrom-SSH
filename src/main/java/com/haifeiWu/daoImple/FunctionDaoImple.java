package com.haifeiWu.daoImple;

import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.FunctionDao;
import com.haifeiWu.entity.PHCSMP_Function;

@Repository("functionDao")
public class FunctionDaoImple extends DaoSupportImpl<PHCSMP_Function> implements
		FunctionDao {

}
