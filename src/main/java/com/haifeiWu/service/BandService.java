package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_Band;

public interface BandService {
	/**
	 * 查询所有可用手环
	 * 
	 * @return
	 */
	public List<PHCSMP_Band> findAvailableBand();

	/**
	 * 查出所有的手环信息，用于用户注册
	 * 
	 * @return
	 */
	public List<PHCSMP_Band> findAllBundInfor();

	public void update(int isUsed, int bandID);

	// public void update(PHCSMP_Band model);

	public PHCSMP_Band findBandById(int bandId);

	public PHCSMP_Band findByRemark(String remark);
	
	public List<PHCSMP_Band> findByBanbType(int bandType);
	
	public void updateRemarkAndTypeById(String remark,int band_Type,int band_ID);
}
