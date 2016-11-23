package com.haifeiWu.daoImple;

import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.PersonalCheckDao;
import com.haifeiWu.entity.PHCSMP_Personal_Check;

@Repository("personalCheckDao")
public class PersonalCheckDaoImple extends DaoSupportImpl<PHCSMP_Personal_Check>
												implements PersonalCheckDao {

}
