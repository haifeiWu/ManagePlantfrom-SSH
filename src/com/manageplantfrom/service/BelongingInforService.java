package com.manageplantfrom.service;

import java.util.List;

import com.manageplantfrom.base.DaoSupport;
import com.manageplantfrom.entity.PHCSMP_BelongingS;

/**
 * 随身物品检查记录的service层代码
 * @author wuhaifei
 * @d2016年8月16日
 */
public interface BelongingInforService extends DaoSupport<PHCSMP_BelongingS> {
	/**
	 * 保存随身物品检查记录
	 * @param model
	 */
	void saveBelongingInfor(PHCSMP_BelongingS model);
	
	/**
	 * 批量保存随身物品信息
	 * @param belongs
	 */
	void saveBelongInforList(List<PHCSMP_BelongingS> belongs);

}
