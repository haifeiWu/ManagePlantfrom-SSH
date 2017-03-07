package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.SuspectDao;
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

	@Override
	public void saveSuspect(PHCSMP_Suspect model) {
		suspectDao.save(model);
	}

	@Override
	public PHCSMP_Suspect findByBandID(int bandId) {
		return suspectDao.findByBandId(bandId);
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
	public void updateSwitch(int cardReader_Switch, String suspect_ID) {
		suspectDao.updateSwitch(cardReader_Switch, suspect_ID);
	}

	@Override
	public void updateLeaveState(int recordVideo_State, int process_Now,
			int cardReader_Switch, String suspetcId) {
		String hql = "update PHCSMP_Suspect s set s.recordVideo_State=? s.process_Now=? s.cardReader_Switch=? where s.band_ID=?";
		suspectDao.update(hql, recordVideo_State, process_Now,
				cardReader_Switch, suspetcId);

	}

	@Override
	public void updateSuspect(int room_Now, int process_Now,
			int recordVideo_State, String suspect_ID) {
		String hql = "update PHCSMP_Suspect s set s.room_Now=? s.process_Now=? s.recordVideo_State=? where s.suspect_ID=?";
		suspectDao.update(hql, recordVideo_State, room_Now, process_Now,
				recordVideo_State, suspect_ID);

	}

	@Override
	public void updateSuspect(int room_Now, int process_Now, String suspect_ID) {
		String hql = "update PHCSMP_Suspect s set s.room_Now=? s.process_Now=? where s.suspect_ID=?";
		suspectDao.update(hql, room_Now, process_Now, suspect_ID);
	}
}
