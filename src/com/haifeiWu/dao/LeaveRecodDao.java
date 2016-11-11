package com.haifeiWu.dao;

import java.util.List;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_Leave_Record;
import com.haifeiWu.entity.Temporary_Leave;

public interface LeaveRecodDao extends DaoSupport<PHCSMP_Leave_Record> {
	/**
	 * 保存离开办案区信息list
	 * @param temporaryLeaves
	 */
	void saveLeaveRecordInfor(List<Temporary_Leave> temporaryLeaves);

}
