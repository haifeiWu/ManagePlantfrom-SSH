package com.haifeiWu.dao;

import java.util.List;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.entity.PHCSMP_Dic_IdentifyCard_Type;
import com.haifeiWu.entity.PHCSMP_Suspect;

/**
 * 嫌疑人信息的dao层代码
 * @author wuhaifei
 * @d2016年8月14日
 */
public interface SuspectDao extends DaoSupport<PHCSMP_Suspect> {

	/**
	 * 保存嫌疑人信息到数据库
	 * @param model
	 */
	void suspectDao(PHCSMP_Suspect model);

	/**
	 * 根据激活码与手环id查询嫌疑人信息
	 * @param roomId
	 * @param bandId
	 * @return
	 */
	PHCSMP_Suspect findInfroByActiveCodeAndBandID(int roomId, int bandId);
	/**
	 * 根据ActiveCode查找用户信息
	 * @param roomId
	 * @return
	 */
	PHCSMP_Suspect findInfroByActiveCode(int roomId);
	/**
	 * 根据手环id更新信息
	 * @param bandId
	 * @param roomId
	 * @return 
	 */
	int updateSuspectInforByBandId(int bandId, int roomId);

	/**
	 * 查找数据库中的所有的身份证类型的数据
	 * @return
	 */
	List<PHCSMP_Dic_IdentifyCard_Type> findAllIdentifyCardType();

	/**
	 * 
	 * @return
	 */
	List<PHCSMP_Dic_Action_Cause> findAllSuspectCause();

	/**
	 * 根据手环ID查找用户信息
	 * @param bandId
	 * @return
	 */
	PHCSMP_Suspect selectPersonInforByBandID(int bandId);
	/**
	 * 获取数据库中的部分待查嫌疑人数据或者出区嫌疑人数据
	 * @return
	 */
	List<PHCSMP_Suspect> getCheckingSuspect(int is_OutOf);

}
