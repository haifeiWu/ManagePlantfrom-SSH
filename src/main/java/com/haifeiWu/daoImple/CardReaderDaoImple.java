package com.haifeiWu.daoImple;

import org.springframework.stereotype.Repository;
import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.CardReaderDao;
import com.haifeiWu.entity.PHCSMP_CardReader;



@Repository("cardReaderDao")
public class CardReaderDaoImple extends DaoSupportImpl<PHCSMP_CardReader> implements CardReaderDao {
	private String hql="";
	@Override
	public void update(String cardReader_Name, int cardReader_Type,int cardReader_ID) {
		hql = "update PHCSMP_CardReader s set s.cardReader_Name=?,s.cardReader_Type=? where s.cardReader_ID=?";
		update(hql, cardReader_Name, cardReader_Type,cardReader_ID);
		
	}
	
	
}