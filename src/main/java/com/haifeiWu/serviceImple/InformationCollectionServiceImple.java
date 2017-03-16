package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.DicCollectionItemDao;
import com.haifeiWu.dao.InformationCollectionDao;
import com.haifeiWu.entity.PHCSMP_Dic_Collection_Item;
import com.haifeiWu.entity.PHCSMP_Information_Collection;
import com.haifeiWu.service.InformationCollectionService;

/**
 * 信息采集service层的实现
 * 
 * @author wuhaifei
 * @d2016年8月15日
 */
@Service("informationCollectionService")
public class InformationCollectionServiceImple implements
		InformationCollectionService {

	@Autowired
	private InformationCollectionDao informationCollectionDao;
	@Autowired
	private DicCollectionItemDao dicCollectionItemDao;

	@Override
	public void saveCollectionInfor(PHCSMP_Information_Collection model) {
		informationCollectionDao.save(model);
	}

	@Override
	public PHCSMP_Information_Collection findInforBySuspetcId(String suspectId) {
		return informationCollectionDao.findSuspectPublicById(suspectId);
	}

	public List<PHCSMP_Dic_Collection_Item> findAllCollectionItem() {
		return dicCollectionItemDao.findAllInfor();
	}

	@Override
	public void deleteInforCollect(PHCSMP_Information_Collection old) {
		informationCollectionDao.deleteBySuspectID(old.getSuspect_ID());

	}
}
