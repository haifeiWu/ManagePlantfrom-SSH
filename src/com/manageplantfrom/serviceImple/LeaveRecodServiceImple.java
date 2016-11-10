package com.manageplantfrom.serviceImple;

import java.util.List;

import com.manageplantfrom.base.DaoSupportImpl;
import com.manageplantfrom.dao.LeaveRecodDao;
import com.manageplantfrom.daoImple.LeaveRecodDaoImple;
import com.manageplantfrom.entity.PHCSMP_Leave_Record;
import com.manageplantfrom.entity.Temporary_Leave;
import com.manageplantfrom.service.LeaveRecodService;

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
