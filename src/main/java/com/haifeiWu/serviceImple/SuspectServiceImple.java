package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.BandInforDao;
import com.haifeiWu.dao.SuspectDao;
import com.haifeiWu.entity.PHCSMP_Band;
import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.entity.PHCSMP_Dic_IdentifyCard_Type;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.SuspectService;

/**
 * 登录犯罪嫌疑人信息的service实现类 
 * @author wuhaifei
 * @d2016年8月14日
 */
@Service("suspectService")
public class SuspectServiceImple implements SuspectService {
	
	@Autowired
	private SuspectDao suspectDao;
	
	@Autowired
	private BandInforDao bundInforDao;

	@Override
	public void saveSuspectInfor(PHCSMP_Suspect model) {
		suspectDao.save(model);
	}

	@Override
	public PHCSMP_Suspect findInfroByActiveCodeAndBandID(int roomId, int bandId) {
		return suspectDao.findInfroByActiveCodeAndBandID(roomId,bandId);
	}

	@Override
	public List<PHCSMP_Band> findAllBundInfor() {
		return bundInforDao.findAllBundInfor();
	}

	@Override
	public PHCSMP_Suspect findInfroByActiveCode(int roomId) {
		return suspectDao.findInfroByActiveCode(roomId);
	}

	@Override
	public int updateSuspectInforByBandId(int bandId, int roomId) {
		return suspectDao.updateSuspectInforByBandId(bandId,roomId);
	}

	@Override
	public List<PHCSMP_Dic_IdentifyCard_Type> findAllIdentifyCardType() {
		return suspectDao.findAllIdentifyCardType();
	}

	@Override
	public List<PHCSMP_Dic_Action_Cause> findAllSuspectCause() {
		return suspectDao.findAllSuspectCause();
	}

	@Override
	public PHCSMP_Suspect selectPersonInforByBandID(int bandId) {
		return suspectDao.selectPersonInforByBandID(bandId);
	}

	@Override
	public List<PHCSMP_Suspect> getSectionSuspectData() {
		return suspectDao.getSectionSuspectData();
	}

}
