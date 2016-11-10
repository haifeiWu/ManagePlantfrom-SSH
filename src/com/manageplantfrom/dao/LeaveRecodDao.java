package com.manageplantfrom.dao;

import java.util.List;

import com.manageplantfrom.base.DaoSupport;
import com.manageplantfrom.entity.PHCSMP_Leave_Record;
import com.manageplantfrom.entity.Temporary_Leave;

public interface LeaveRecodDao extends DaoSupport<PHCSMP_Leave_Record> {
	/**
	 * 保存离开办案区信息list
	 * @param temporaryLeaves
	 */
	void saveLeaveRecordInfor(List<Temporary_Leave> temporaryLeaves);

}
