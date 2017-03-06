package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.CabinetDao;
import com.haifeiWu.dao.DicInspectionSituationDao;
import com.haifeiWu.dao.DicKeepingWayDao;
import com.haifeiWu.dao.PersonalCheckDao;
import com.haifeiWu.entity.PHCSMP_Cabinet;
import com.haifeiWu.entity.PHCSMP_Dic_Inspection_Situation;
import com.haifeiWu.entity.PHCSMP_Dic_Keeping_Way;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.service.PersonalCheckService;

/**
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
@Service("personalCheckService")
public class PersonalCheckServiceImple implements PersonalCheckService {

	@Autowired
	private PersonalCheckDao personalCheckDao;
	@Autowired
	private DicInspectionSituationDao dicInspectionSituationDao;
	@Autowired
	private DicKeepingWayDao dicKeepingWayDao;
	@Autowired
	private CabinetDao cabinetDao;

	@Override
	public void saveCheckPersonInfor(PHCSMP_Personal_Check model) {
		personalCheckDao.save(model);
	}

	@Override
	public PHCSMP_Personal_Check findInforBySuspetcId(String suspectId) {
		return personalCheckDao.findSuspectPublicById(suspectId);
	}

	@Override
	public List<PHCSMP_Dic_Keeping_Way> findAllKeepingWay() {
		return dicKeepingWayDao.findAllInfor();
	}

	@Override
	public List<PHCSMP_Dic_Inspection_Situation> findAllInspectionSituation() {

		return dicInspectionSituationDao.findAllInfor();
	}

	@Override
	public List<PHCSMP_Cabinet> findAllPHCSMPCabinet() {
		return cabinetDao.findAllInfor();
	}
}
