package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_Band;
import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.entity.PHCSMP_Dic_IdentifyCard_Type;
import com.haifeiWu.entity.PHCSMP_Suspect;

/**
 * 登录犯罪嫌疑人信息的service
 * 
 * @author wuhaifei
 * @d2016年8月14日
 */
public interface SuspectService {
	/**
	 * 保存嫌疑人信息到数据库
	 * 
	 * @param model
	 */
	public void saveSuspect(PHCSMP_Suspect model);
	public PHCSMP_Suspect findByBandID(int bandId);
	public PHCSMP_Suspect findByRoomID(int roomId);
	/**
	 * 获取数据库中的部分待查嫌疑人数据或者出区嫌疑人数据
	 * 
	 * @return
	 */
	public List<PHCSMP_Suspect> getLeavePoliceSuspect();

	/**
	 * 获取数据库中的部分待查嫌疑人数据或者出区嫌疑人数据
	 * 
	 * @return
	 */
	public List<PHCSMP_Suspect> getOnPoliceSuspect();
	/**
	 * 查出所有的手环信息，用于用户注册
	 * 
	 * @return
	 */
	List<PHCSMP_Band> findAllBundInfor();

	/**
	 * 查找数据库中的所有身份证类型的数据
	 * 
	 * @return 所有身份证类型的数据
	 */
	List<PHCSMP_Dic_IdentifyCard_Type> findAllIdentifyCardType();

	/**
	 * 查詢數據庫的所有案由數據
	 * 
	 * @return
	 */
	List<PHCSMP_Dic_Action_Cause> findAllSuspectCause();

	public PHCSMP_Suspect findBySuspetcId(String suspectId);
}
