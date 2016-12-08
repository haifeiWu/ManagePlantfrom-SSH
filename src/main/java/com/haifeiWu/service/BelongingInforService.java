package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_BelongingS;

/**
 * 随身物品检查记录的service层代码
 * 
 * @author wuhaifei
 * @d2016年8月16日
 */
public interface BelongingInforService {
	/**
	 * 保存随身物品检查记录
	 * 
	 * @param model
	 */
	void saveBelongingInfor(PHCSMP_BelongingS model);

	/**
	 * 批量保存随身物品信息
	 * 
	 * @param belongs
	 */
	void saveBelongInforList(List<PHCSMP_BelongingS> belongs);

	/**
	 * 根据档案号查询嫌疑人随身物品信息
	 * 
	 * @param suspectId
	 * @return
	 */
	PHCSMP_BelongingS findInforBySuspetcId(String suspectId);

	/**
	 * 根据档案号查出所有的嫌疑人随身物品信息
	 * 
	 * @param suspectId
	 * @return
	 */
	List<PHCSMP_BelongingS> selectBelongInfor(String suspectId);

}
