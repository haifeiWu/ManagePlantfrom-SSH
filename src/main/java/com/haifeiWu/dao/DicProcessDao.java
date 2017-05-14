package com.haifeiWu.dao;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_Dic_Process;

public interface DicProcessDao extends DaoSupport<PHCSMP_Dic_Process> {
	public String getprocessName(int process);
}
