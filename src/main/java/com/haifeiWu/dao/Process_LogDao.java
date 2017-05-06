package com.haifeiWu.dao;

import java.util.List;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_LogInfo;
import com.haifeiWu.entity.PHCSMP_Process_Log;

public interface Process_LogDao extends DaoSupport<PHCSMP_Process_Log> {
	public List<PHCSMP_Process_Log> findAll_process(String hql);
	public List<PHCSMP_Process_Log> queryProcessByPage(String hql, int offset, int pageSize);
	public PHCSMP_Process_Log queryByEndTime(String hql);
	public int getAllRowCount(String hql);
	public int getAllRowCount(String hql,String param);
	public List<PHCSMP_Process_Log> queryprocessByPageApram(String hql, int offset,
			int pageSize, String param);
	public PHCSMP_Process_Log queryBystaff(String hql);
}
