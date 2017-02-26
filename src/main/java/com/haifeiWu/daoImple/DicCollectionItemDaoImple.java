package com.haifeiWu.daoImple;

import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.DicCollectionItemDao;
import com.haifeiWu.entity.PHCSMP_Dic_Collection_Item;

@Repository("dicCollectionItemDao")
public class DicCollectionItemDaoImple extends DaoSupportImpl<PHCSMP_Dic_Collection_Item> implements DicCollectionItemDao {

}