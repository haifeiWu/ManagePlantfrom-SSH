package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.LeaveRecodDao;
import com.haifeiWu.entity.PHCSMP_Leave_Record;
import com.haifeiWu.entity.Temporary_Leave;
import com.haifeiWu.service.LeaveRecodService;

/**
 * 出区登记
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
@Service("leaveRecodService")
public class LeaveRecodServiceImple implements LeaveRecodService {

	@Autowired
	private LeaveRecodDao leaveRecodDao;

	@Override
	public void saveLeaveRecordInfor(PHCSMP_Leave_Record model) {
		leaveRecodDao.save(model);
	}

	@Override
	public void saveLeaveRecordInfor(List<Temporary_Leave> temporaryLeaves) {
		leaveRecodDao.saveLeaveRecordInfor(temporaryLeaves);
	}

	@Override
	public PHCSMP_Leave_Record findInforBySuspetcId(String suspectId) {
		return leaveRecodDao.findInforBySuspetcId(suspectId);
	}


}
