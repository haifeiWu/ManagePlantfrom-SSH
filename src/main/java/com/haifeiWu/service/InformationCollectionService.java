package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_Dic_Collection_Item;
import com.haifeiWu.entity.PHCSMP_Information_Collection;

/**
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
public interface InformationCollectionService {
	/**
	 * 保存信息采集的信息
	 * 
	 * @param model
	 */
	void saveCollectionInfor(PHCSMP_Information_Collection model);

	/**
	 * 根据档案号查询嫌疑人信息采集信息
	 * 
	 * @param suspectId
	 * @return
	 */
	PHCSMP_Information_Collection findInforBySuspetcId(String suspectId);

	public List<PHCSMP_Dic_Collection_Item> findAllCollectionItem();
}
