package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.BelongingInforDao;
import com.haifeiWu.entity.PHCSMP_BelongingS;
import com.haifeiWu.service.BelongingInforService;

/**
 * 随身物品检查记录的service层代码实现
 * @author wuhaifei
 * @d2016年8月16日
 */
@Service("belongingInforService")
public class BelongingInforServiceImple extends DaoSupportImpl<PHCSMP_BelongingS> implements BelongingInforService {
	
	@Autowired
	private BelongingInforDao belongingInforDao;

	@Override
	public void saveBelongingInfor(PHCSMP_BelongingS model) {
		belongingInforDao.save(model);
	}

	@Override
	public void saveBelongInforList(List<PHCSMP_BelongingS> belongs) {
		belongingInforDao.savesaveBelongInforList(belongs);
	}
}
