package com.haifeiWu.serviceImple;

import java.util.List;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.LeaveRecodDao;
import com.haifeiWu.daoImple.LeaveRecodDaoImple;
import com.haifeiWu.entity.PHCSMP_Leave_Record;
import com.haifeiWu.entity.Temporary_Leave;
import com.haifeiWu.service.LeaveRecodService;

public class LeaveRecodServiceImple extends DaoSupportImpl<PHCSMP_Leave_Record> implements LeaveRecodService {
	private LeaveRecodDao dao = new LeaveRecodDaoImple();

	@Override
	public void saveLeaveRecordInfor(PHCSMP_Leave_Record model) {
		dao.save(model);
	}

	@Override
	public void saveLeaveRecordInfor(List<Temporary_Leave> temporaryLeaves) {
		dao.saveLeaveRecordInfor(temporaryLeaves);
	}
	
}
