package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.BandInforDao;
import com.haifeiWu.entity.PHCSMP_Band;
import com.haifeiWu.service.BandService;

@Service("bandService")
public class BandServiceImple extends DaoSupportImpl<PHCSMP_Band> implements
		BandService {

	@Autowired
	private BandInforDao bandInforDao;

	@Override
	public List<PHCSMP_Band> findAvailableBand() {
		return bandInforDao.findListByPropertyName("is_Used", 0);
	}

	@Override
	public List<PHCSMP_Band> findAllBundInfor() {
		return bandInforDao.findAllBundInfor();
	}

	// @Override
	// public void update(PHCSMP_Band model) {
	// bandInforDao.update(model);
	// }

	@Override
	public void update(int isUsed, int bandID) {
		// bandInforDao.update(entity);
		bandInforDao.updateIsUsedByBandId(isUsed, bandID);

	}
	/**
	 * 修改手环的类型和备注
	 */
	@Override
	public void updateRemarkAndTypeById(String remark,int band_Type,int band_ID){
		 bandInforDao.updateRemarkAndTypeById(remark, band_Type, band_ID);
	}
	
	@Override
	public PHCSMP_Band findBandById(int bandId) {
		return bandInforDao.findByPropertyName("band_ID", bandId);
	}

	@Override
	public PHCSMP_Band findByRemark(String remark) {
		return bandInforDao.findByPropertyName("remark", remark);
	}
	
	
	@Override
	public List<PHCSMP_Band> findByBanbType(int bandType) {
		return bandInforDao.findListByPropertyName("band_Type",bandType);
	}
}
