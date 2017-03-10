package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_Cabinet;
import com.haifeiWu.entity.PHCSMP_Dic_Inspection_Situation;
import com.haifeiWu.entity.PHCSMP_Dic_Keeping_Way;
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

	public List<PHCSMP_Dic_Inspection_Situation> findAllInspectionSituation();

	public List<PHCSMP_Dic_Keeping_Way> findAllKeepingWay();

	public List<PHCSMP_Cabinet> findAllPHCSMPCabinet();
}
