package com.haifeiWu.dao;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.Temporary_Leave;

public interface TemporaryLeaveDao extends DaoSupport<Temporary_Leave> {

	public Temporary_Leave findTemporaryLeaveInfoById(String suspectId);
}
