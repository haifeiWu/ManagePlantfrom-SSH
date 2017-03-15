package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.DicKeepingWayDao;
import com.haifeiWu.dao.DicLeavingReasonDao;
import com.haifeiWu.entity.PHCSMP_Dic_Keeping_Way;
import com.haifeiWu.entity.PHCSMP_Dic_Leaving_Reason;
import com.haifeiWu.service.DicService;

@Service("dicService")
public class DicServiceImple implements DicService {
	@Autowired
	private DicLeavingReasonDao dicLeavingReasonDao;
	@Autowired
	private DicKeepingWayDao dicKeepingWayDao;

	@Override
	public List<PHCSMP_Dic_Leaving_Reason> findLeaveReason() {
		return dicLeavingReasonDao.findAllInfor();
	}

	@Override
	public List<PHCSMP_Dic_Keeping_Way> findKeepingWay() {
		return dicKeepingWayDao.findAllInfor();
	}

}
