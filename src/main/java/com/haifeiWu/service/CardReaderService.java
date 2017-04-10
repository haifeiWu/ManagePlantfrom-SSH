package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_CardReader;

public interface CardReaderService {
	public PHCSMP_CardReader findById(int id);
	
	public List<PHCSMP_CardReader> findAllCardReader();
	public void update(String cardReader_Name, int cardReader_Type,int cardReader_ID);
	
}
