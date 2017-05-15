package com.haifeiWu.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.DicProcessDao;
import com.haifeiWu.service.Dic_ProcessService;

@Service("dic_ProcessService")
public class Dic_ProcessServiceImple implements Dic_ProcessService {

	@Autowired
	private DicProcessDao dicprocessDao;
	
	@Override
	public String getProcessName(int process) {
		
		return dicprocessDao.getprocessName(process);
	}

}
