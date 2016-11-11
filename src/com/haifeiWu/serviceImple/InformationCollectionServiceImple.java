package com.haifeiWu.serviceImple;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.InformationCollectionDao;
import com.haifeiWu.daoImple.InformationCollectionDaoImple;
import com.haifeiWu.entity.PHCSMP_Information_Collection;
import com.haifeiWu.service.InformationCollectionService;
/**
 * 信息采集service层的实现
 * @author wuhaifei
 * @d2016年8月15日
 */
public class InformationCollectionServiceImple extends DaoSupportImpl<PHCSMP_Information_Collection> implements
		InformationCollectionService {
	private InformationCollectionDao dao = new InformationCollectionDaoImple();

	@Override
	public void saveCollectionInfor(PHCSMP_Information_Collection model) {
		dao.save(model);
	}
}
