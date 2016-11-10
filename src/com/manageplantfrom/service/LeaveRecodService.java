package com.manageplantfrom.service;

import com.manageplantfrom.base.DaoSupport;
import com.manageplantfrom.entity.PHCSMP_Leave_Record;

public interface LeaveRecodService extends DaoSupport<PHCSMP_Leave_Record> {
	/**
	 * 保存离开办案区系信息
	 * @param model
	 */
	void saveLeaveRecordInfor(PHCSMP_Leave_Record model);

}
