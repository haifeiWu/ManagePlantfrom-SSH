package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.haifeiWu.dao.LineDao;
import com.haifeiWu.entity.PHCSMP_Line;
import com.haifeiWu.service.LineService;
@Service("LineService")
public class LineServiceImple implements LineService {
	@Autowired
	private LineDao lineDao;
	
	private PHCSMP_Line line;
	
	@Override
	public boolean isFull() {
		if(lineDao.findObj().getLine_Used()<lineDao.findObj().getLine_Count()){
			//不饱和，可以录像
			return true;
		}
		else{
			//饱和，暂时无法录像
			return false;
		}
	}

	@Override
	public void startLine() {
		line=lineDao.findObj();
		line.setLine_Used(line.getLine_Used()+1);
		lineDao.save(line);
	}

	@Override
	public void closeLine() {
     line=lineDao.findObj();
		line.setLine_Used(line.getLine_Used()-1);
		lineDao.save(line);
  }
}
