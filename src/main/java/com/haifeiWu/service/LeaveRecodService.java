package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_Leave_Record;
import com.haifeiWu.entity.Temporary_Leave;

public interface LeaveRecodService {
	/**
	 * 保存离开办案区系信息
	 * @param model
	 */
	void saveLeaveRecordInfor(PHCSMP_Leave_Record model);
	
	/**
	 * 保存离开办案区系信息list
	 * @param temporaryLeaves
	 */
	void saveLeaveRecordInfor(List<Temporary_Leave> temporaryLeaves);

}
