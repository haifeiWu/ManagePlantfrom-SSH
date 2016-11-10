package com.manageplantfrom.service;

import java.util.List;

import com.manageplantfrom.base.DaoSupport;
import com.manageplantfrom.entity.PHCSMP_Leave_Record;
import com.manageplantfrom.entity.Temporary_Leave;

public interface LeaveRecodService extends DaoSupport<PHCSMP_Leave_Record> {
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
