package com.haifeiWu.daoImple;

import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.DicLeavingReasonDao;
import com.haifeiWu.entity.PHCSMP_Dic_Leaving_Reason;

@Repository("dicLeavingReasonDao")
public class DicLeavingReasonDaoImple extends DaoSupportImpl<PHCSMP_Dic_Leaving_Reason> implements DicLeavingReasonDao{

}
