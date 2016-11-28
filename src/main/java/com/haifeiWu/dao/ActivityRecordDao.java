package com.haifeiWu.dao;

import java.util.List;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_Activity_Record;

/**
 * 
 * @author wuhaifei
 * @d2016年10月10日
 */
public interface ActivityRecordDao extends DaoSupport<PHCSMP_Activity_Record> {

	/**
	 * 批量保存活动记录信息
	 * 
	 * @param activitys
	 */
	void saveActivitysInfor(List<PHCSMP_Activity_Record> activitys);

}
