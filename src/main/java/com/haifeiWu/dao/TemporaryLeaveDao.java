package com.haifeiWu.dao;

import java.util.List;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.Temporary_Leave;

public interface TemporaryLeaveDao extends DaoSupport<Temporary_Leave> {

	List<Temporary_Leave> selectTemporaryLeavesInfor(String suspectId);

	public Temporary_Leave findTemporaryLeaveInfoById(String suspectId);

	Temporary_Leave findTemporaryreturnById(String suspectId);

}
