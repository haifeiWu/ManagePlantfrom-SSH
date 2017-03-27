package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_Activity_Record;

/**
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
public interface ActivityRecordService {
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

//	/**
//	 * 根据档案号查询嫌疑人档案信息
//	 * 
//	 * @param suspectId
//	 * @return
//	 */
//	PHCSMP_Activity_Record findInforBySuspetcId(String suspectId);

	/**
	 * 根据档案号登记办案区活动记录
	 * 
	 * @param suspectId
	 * @return
	 */
	List<PHCSMP_Activity_Record> selectActivityRecordInfor(String suspectId);
	
	public void updatevedio_Number(String vedio_Number,String identifyCard_Number);
}
