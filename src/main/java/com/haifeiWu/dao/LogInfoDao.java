package com.haifeiWu.dao;

import java.util.List;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_LogInfo;
import com.haifeiWu.entity.PHCSMP_Process_Log;

/**
 * 日志功能
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
public interface LogInfoDao extends DaoSupport<PHCSMP_LogInfo>{

	public List<PHCSMP_LogInfo> queryInfoByPage(String hql, int offset, int pageSize);
	

	public int getAllRowCount(String hql);
	public int getAllRowCount(String hql,String param);
	
	
	public List<PHCSMP_LogInfo> findAll_log(String hql);


	public List<PHCSMP_LogInfo> queryInfoByPageAparam(String hql, int offset,
			int pageSize, String username);

	
	
}
