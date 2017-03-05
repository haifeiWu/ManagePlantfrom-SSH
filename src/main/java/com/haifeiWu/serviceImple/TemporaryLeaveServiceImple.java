package com.haifeiWu.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.TemporaryLeaveDao;
import com.haifeiWu.entity.Temporary_Leave;
import com.haifeiWu.service.TemporaryLeaveService;

@Service("TemporaryLeaveService")
public class TemporaryLeaveServiceImple extends DaoSupportImpl<Temporary_Leave> implements TemporaryLeaveService{
	@Autowired
	private TemporaryLeaveDao temporaryLeaveDao;
	@Override
	public Temporary_Leave IsTemporaryLeaveReturn(String suspectId) {
		Temporary_Leave temporaryLeave=temporaryLeaveDao.findTemporaryLeaveInfoById(suspectId);
		if(temporaryLeave!=null){
			//不为空则是出区返回
			return temporaryLeave;
		}else{
			//出区
			return null;
		}
	}

	@Override
	public void saveTemporaryLeaveInfo(Temporary_Leave obj) {
		temporaryLeaveDao.save(obj);
	}
	@Override
	public void updateTemporaryLeaveInfo(Temporary_Leave obj) {
		temporaryLeaveDao.update(obj);
	}
	
}
