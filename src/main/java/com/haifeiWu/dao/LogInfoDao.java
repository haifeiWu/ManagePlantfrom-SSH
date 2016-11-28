package com.haifeiWu.dao;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_LogInfo;

/**
 * 日志功能
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
public interface LogInfoDao {

	public List<PHCSMP_LogInfo> queryByPage(String hql, int offset, int pageSize);

	public int getAllRowCount(String hql);
}
