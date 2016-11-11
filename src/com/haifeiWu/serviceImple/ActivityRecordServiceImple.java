package com.haifeiWu.serviceImple;

import java.util.List;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.ActivityRecordDao;
import com.haifeiWu.daoImple.ActivityRecordDaoImple;
import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.service.ActivityRecordService;

/**
 * 询问、讯问、辨认、等候、休息、饮食等活动记录service层
 * @author wuhaifei
 * @d2016年8月16日
 */
public class ActivityRecordServiceImple extends DaoSupportImpl<PHCSMP_Activity_Record> implements ActivityRecordService {
	private ActivityRecordDao dao = new ActivityRecordDaoImple();

	@Override
	public void saveActivityRecordInfor(PHCSMP_Activity_Record model) {
		dao.save(model);
	}

	@Override
	public void saveActivitysInfor(List<PHCSMP_Activity_Record> activitys) {
		dao.saveActivitysInfor(activitys);
	}
	
	
}
