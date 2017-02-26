package com.haifeiWu.daoImple;

import org.springframework.stereotype.Repository;
import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.CardReaderDao;
import com.haifeiWu.entity.PHCSMP_CardReader;



@Repository("cardReaderDao")
public class CardReaderDaoImple extends DaoSupportImpl<PHCSMP_CardReader> implements CardReaderDao {
	
}