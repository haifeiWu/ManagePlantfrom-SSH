package com.manageplantfrom.service;

import com.manageplantfrom.base.DaoSupport;
import com.manageplantfrom.entity.PHCSMP_Personal_Check;

/**
 * 人身检查记录service接口
 * @author wuhaifei
 * @d2016年8月14日
 */
public interface PersonalCheckService extends DaoSupport<PHCSMP_Personal_Check> {

	/**
	 * 保存人身检查记录信息
	 * @param model
	 */
	void saveCheckPersonInfor(PHCSMP_Personal_Check model);

}
