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
 * 
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
	public void saveSuspect(PHCSMP_Suspect model) {
		suspectDao.save(model);
	}

	@Override
	public PHCSMP_Suspect findByBandID(int bandId) {
		return suspectDao.findByBandId(bandId);
	}

	@Override
	public List<PHCSMP_Band> findAllBundInfor() {
		return bundInforDao.findAllBundInfor();
	}

	@Override
	public PHCSMP_Suspect findByRoomID(int roomId) {
		return suspectDao.findByRoomID(roomId);
	}

	@Override
	public List<PHCSMP_Dic_IdentifyCard_Type> findAllIdentifyCardType() {
		return suspectDao.findAllIdentifyCardType();
	}

	@Override
	public List<PHCSMP_Dic_Action_Cause> findAllSuspectCause() {
		return suspectDao.findAllSuspectCause();
	}

	// 根据Process_Now查的两个表
	@Override
	public List<PHCSMP_Suspect> getLeavePoliceSuspect() {
		return suspectDao.getLeavePoliceSuspect();
	}

	@Override
	public List<PHCSMP_Suspect> getOnPoliceSuspect() {
		return suspectDao.getOnPoliceSuspect();
	}

	@Override
	public PHCSMP_Suspect findBySuspetcId(String suspectId) {
		return suspectDao.findBySuspectID(suspectId);
		// return suspectDao.findByPropertyName("suspect_ID", suspectId);
	}

	@Override
	public void updateSuspect(PHCSMP_Suspect suspectInfor) {
		suspectDao.update(suspectInfor);

	}
}
