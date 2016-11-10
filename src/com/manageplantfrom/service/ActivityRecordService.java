package com.manageplantfrom.service;

import java.util.List;

import com.manageplantfrom.base.DaoSupport;
import com.manageplantfrom.entity.PHCSMP_Activity_Record;

public interface ActivityRecordService extends DaoSupport<PHCSMP_Activity_Record> {
	/**
	 * 保存活动记录信息的方法
	 * @param model
	 */
	void saveActivityRecordInfor(PHCSMP_Activity_Record model);
	
	/**
	 * 保存活动记录信息list的方法
	 * @param activitys
	 */
	void saveActivitysInfor(List<PHCSMP_Activity_Record> activitys);

}
