package com.haifeiWu.daoImple;

import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.TemporaryLeaveDao;
import com.haifeiWu.entity.Temporary_Leave;

@Repository("temporaryLeaveDao")
public class TemporaryLeaveDaoImple extends DaoSupportImpl<Temporary_Leave> implements TemporaryLeaveDao{

}
