package com.haifeiWu.daoImple;

import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.PersonalCheckDao;
import com.haifeiWu.entity.PHCSMP_Personal_Check;

/**
 * 人身检查的dao实现
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
@Repository("personalCheckDao")
public class PersonalCheckDaoImple extends
		DaoSupportImpl<PHCSMP_Personal_Check> implements PersonalCheckDao {

}
