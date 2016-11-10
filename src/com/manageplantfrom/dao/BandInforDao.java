package com.manageplantfrom.dao;

import java.util.List;

import com.manageplantfrom.base.DaoSupport;
import com.manageplantfrom.entity.PHCSMP_Band;

public interface BandInforDao extends DaoSupport<PHCSMP_Band> {
	
	/**
	 * 查找数据库中的所有信息
	 * @return
	 */
	List<PHCSMP_Band> findAllBundInfor();
	
	/**
	 * 根据手环编号来查找手环id
	 * @param wristId 手环编号
	 * @return 手环id
	 */
	int findBandIdByWristId(String wristId);
	
}
