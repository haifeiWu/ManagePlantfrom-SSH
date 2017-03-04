package com.haifeiWu.dao;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_Line;

public interface LineDao extends DaoSupport<PHCSMP_Line>{
    /**
     * 获取当前表的唯一一条记录
     * @return
     */
	public PHCSMP_Line findObj();
}
