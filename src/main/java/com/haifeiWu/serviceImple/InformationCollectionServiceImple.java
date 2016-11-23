package com.haifeiWu.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("informationCollectionService")
public class InformationCollectionServiceImple implements InformationCollectionService {
	
	@Autowired
	private InformationCollectionDao informationCollectionDao;

	@Override
	public void saveCollectionInfor(PHCSMP_Information_Collection model) {
		informationCollectionDao.save(model);
	}
}
