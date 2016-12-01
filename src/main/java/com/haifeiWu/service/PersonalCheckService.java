package com.haifeiWu.service;

import com.haifeiWu.entity.PHCSMP_Personal_Check;

/**
 * 人身检查记录service接口
 * 
 * @author wuhaifei
 * @d2016年8月14日
 */
public interface PersonalCheckService {

	/**
	 * 保存人身检查记录信息
	 * 
	 * @param model
	 */
	void saveCheckPersonInfor(PHCSMP_Personal_Check model);

	/**
	 * 根据档案号查询嫌疑人人身检查信息
	 * 
	 * @param suspectId
	 * @return
	 */
	PHCSMP_Personal_Check findInforBySuspetcId(String suspectId);

}
