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

	@Override
	public void update(PHCSMP_Band entity) {
		bandInforDao.update(entity);
	}

	@Override
	public PHCSMP_Band findBandById(int bandId) {
		return bandInforDao.findByPropertyName("band_ID", bandId);
	}
}
