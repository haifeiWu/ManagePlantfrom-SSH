package com.haifeiWu.daoImple;

import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.RoleFunctionDao;
import com.haifeiWu.entity.PHCSMP_RoleFunction;

@Repository("roleFunctionDao")
public class RoleFunctionDaoImple extends DaoSupportImpl<PHCSMP_RoleFunction>
		implements RoleFunctionDao {

}
