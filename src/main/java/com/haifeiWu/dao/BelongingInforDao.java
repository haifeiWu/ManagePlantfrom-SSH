package com.haifeiWu.dao;

import java.util.List;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_BelongingS;

/**
 * 随身物品检查记录的dao层代码
 * 
 * @author wuhaifei
 * @d2016年8月16日
 */
public interface BelongingInforDao extends DaoSupport<PHCSMP_BelongingS> {

	/**
	 * 批量保存随身物品信息
	 * 
	 * @param belongs
	 */
	void savesaveBelongInforList(List<PHCSMP_BelongingS> belongs);

	/**
	 * 根据档案号查出所有的嫌疑人随身物品信息
	 * 
	 * @param suspectId
	 * @return
	 */
	List<PHCSMP_BelongingS> selectBelongInfor(String suspectId);

}
