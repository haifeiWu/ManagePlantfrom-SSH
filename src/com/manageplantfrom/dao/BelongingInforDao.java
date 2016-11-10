package com.manageplantfrom.dao;

import java.util.List;

import com.manageplantfrom.base.DaoSupport;
import com.manageplantfrom.entity.PHCSMP_BelongingS;

/**
 * 随身物品检查记录的dao层代码
 * @author wuhaifei
 * @d2016年8月16日
 */
public interface BelongingInforDao extends DaoSupport<PHCSMP_BelongingS> {
	
	/**
	 * 批量保存随身物品信息
	 * @param belongs
	 */
	void savesaveBelongInforList(List<PHCSMP_BelongingS> belongs);

}
