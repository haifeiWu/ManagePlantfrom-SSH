package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.TemporaryLeaveDao;
import com.haifeiWu.entity.Temporary_Leave;
import com.haifeiWu.service.TemporaryLeaveService;

@Service("temporaryLeaveService")
public class TemporaryLeaveServiceImple extends DaoSupportImpl<Temporary_Leave>
		implements TemporaryLeaveService {
	@Autowired
	private TemporaryLeaveDao temporaryLeaveDao;

	@Override
	public Temporary_Leave IsTemporaryLeaveReturn(String suspectId) {
		Temporary_Leave temporaryLeave = temporaryLeaveDao
				.findTemporaryLeaveInfoById(suspectId);
		if (temporaryLeave != null) {
			// 不为空则是出区返回
			return temporaryLeave;
		} else {
			// 出区
			return null;
		}
	}

	@Override
	public void saveTemporaryLeaveInfo(Temporary_Leave obj) {
		temporaryLeaveDao.save(obj);
	}

	// @Override
	// public void updateTemporaryLeaveInfo(Temporary_Leave obj) {
	// temporaryLeaveDao.update(obj);
	// }

	@Override
	public void updateReturnTime(String return_Time, String suspect_ID) {
		String hql = "update Temporary_Leave s set s.return_Time=? where s.suspect_ID=?";
		temporaryLeaveDao.update(hql, return_Time, suspect_ID);
	}

	@Override
	public List<Temporary_Leave> findTempLeaveListBySuspectID(String suspectId) {
		return temporaryLeaveDao
				.findListByPropertyName("suspect_ID", suspectId);
	}

}
