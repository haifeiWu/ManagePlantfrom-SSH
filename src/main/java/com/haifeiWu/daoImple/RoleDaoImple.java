package com.haifeiWu.daoImple;

import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.RoleDao;
import com.haifeiWu.entity.PHCSMP_Role;

@Repository("roleDao")
public class RoleDaoImple extends DaoSupportImpl<PHCSMP_Role> implements RoleDao {

}
