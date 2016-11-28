package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_Activity_Record;

/**
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
public interface ActivityRecordService extends
		DaoSupport<PHCSMP_Activity_Record> {
	/**
	 * 保存活动记录信息的方法
	 * 
	 * @param model
	 */
	void saveActivityRecordInfor(PHCSMP_Activity_Record model);

	/**
	 * 保存活动记录信息list的方法
	 * 
	 * @param activitys
	 */
	void saveActivitysInfor(List<PHCSMP_Activity_Record> activitys);

}
