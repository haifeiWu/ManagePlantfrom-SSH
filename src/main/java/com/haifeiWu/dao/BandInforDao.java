package com.haifeiWu.dao;

import java.util.List;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_Band;

public interface BandInforDao extends DaoSupport<PHCSMP_Band> {

	/**
	 * 查找数据库中的所有信息
	 * 
	 * @return
	 */
	public List<PHCSMP_Band> findAllBundInfor();

	/**
	 * 根据手环编号来查找手环id
	 * 
	 * @param wristId
	 *            手环编号
	 * @return 手环id
	 */
	public int findBandIdByWristId(String wristId);

	public void updateIsUsedByBandId(int isUsed, int bandID);
	
	public void updateRemarkAndTypeById(String remark,int band_Type,int band_ID);
}
