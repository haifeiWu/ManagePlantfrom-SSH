package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.CardReaderDao;
import com.haifeiWu.entity.PHCSMP_CardReader;
import com.haifeiWu.entity.PHCSMP_Room;
import com.haifeiWu.service.CardReaderService;

@Service("CardReaderService")
public class CardReaderServiceImple extends DaoSupportImpl<PHCSMP_CardReader> implements CardReaderService {

	@Autowired
	private CardReaderDao cardReaderDao;
	

	@Override
	public PHCSMP_CardReader findById(int id) {
		// TODO Auto-generated method stub
		return findByPropertyName("cardReader_ID", id);
	}

	@Override
	public List<PHCSMP_CardReader> findAllCardReader() {
		return findAllInfor();
	}

	

	@Override
	public void update(String cardReader_Name, int cardReader_Type,int cardReader_ID) {
		cardReaderDao.update(cardReader_Name, cardReader_Type,cardReader_ID);
		
	}

}
