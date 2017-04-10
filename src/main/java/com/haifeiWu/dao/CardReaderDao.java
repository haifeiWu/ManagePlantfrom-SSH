package com.haifeiWu.dao;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_CardReader;

public interface CardReaderDao extends DaoSupport<PHCSMP_CardReader> {
	public void update(String cardReader_Name, int cardReader_Type,
			int cardReader_ID);
}
