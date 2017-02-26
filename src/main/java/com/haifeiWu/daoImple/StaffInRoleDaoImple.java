package com.haifeiWu.daoImple;

import org.springframework.stereotype.Repository;
import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.StaffInRoleDao;
import com.haifeiWu.entity.PHCSMP_Staff_In_Role;

@Repository("staffInRoleDao")
public class StaffInRoleDaoImple extends DaoSupportImpl<PHCSMP_Staff_In_Role> implements StaffInRoleDao {

}
