package com.haifeiWu.service;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_Information_Collection;

public interface InformationCollectionService extends DaoSupport<PHCSMP_Information_Collection> {
	/**
	 * 保存信息采集的信息
	 * @param model
	 */
	void saveCollectionInfor(PHCSMP_Information_Collection model);

}
