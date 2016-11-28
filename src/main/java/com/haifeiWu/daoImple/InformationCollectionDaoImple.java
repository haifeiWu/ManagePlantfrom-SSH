package com.haifeiWu.daoImple;

import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.InformationCollectionDao;
import com.haifeiWu.entity.PHCSMP_Information_Collection;

/**
 * 信息采集dao层接口的实现
 * 
 * @author wuhaifei
 * @d2016年8月15日
 */
@Repository("informationCollectionDao")
public class InformationCollectionDaoImple extends
		DaoSupportImpl<PHCSMP_Information_Collection> implements
		InformationCollectionDao {
}
