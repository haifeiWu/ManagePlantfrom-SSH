package com.haifeiWu.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.KeepDao;
import com.haifeiWu.service.KeepService;

@Service("keepService")
public class KeepServiceImple implements KeepService {
	@Autowired
	private KeepDao keepDao;
	
	@Override
	public String getKeepname(int belongstaffid) {
		try {
			return keepDao.getkeepname(belongstaffid);
		} catch (Exception e) {
			return null;
		}
	}

}
