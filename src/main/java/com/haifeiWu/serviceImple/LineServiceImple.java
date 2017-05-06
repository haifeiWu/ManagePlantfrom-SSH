package com.haifeiWu.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.LineDao;
import com.haifeiWu.entity.PHCSMP_Line;
import com.haifeiWu.service.LineService;

@Service("lineService")
public class LineServiceImple implements LineService {
	@Autowired
	private LineDao lineDao;

	private PHCSMP_Line line;

	@Override
	public boolean isFull() {
		if (lineDao.findAllInfor().get(0).getLine_Used() < lineDao
				.findAllInfor().get(0).getLine_Count()) {
			// 没有饱和，可以录像
			return true;
		} else {
			// 饱和，暂时无法录像
			return false;
		}
	}

	@Override
	public void startLine() {
		line = lineDao.findObj();
		lineDao.updateLineUsed(line.getLine_Used() + 1);
	}

	@Override
	public void closeLine() {
		line = lineDao.findObj();
		lineDao.updateLineUsed(line.getLine_Used() - 1);
	}
}
