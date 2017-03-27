package com.haifeiWu.dao;

import java.util.List;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_Activity_Record;

/**
 * 询问、讯问活动
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

	/**
	 * 根据档案号登记办案区活动记录
	 * 
	 * @param suspectId
	 * @return
	 */
	List<PHCSMP_Activity_Record> selectActivityRecordInfor(String suspectId);
	
	public void updatevedio_Number(String vedio_Number,String identifyCard_Number);

}
