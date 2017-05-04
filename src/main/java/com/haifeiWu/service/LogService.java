package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_LogInfo;
import com.haifeiWu.entity.PHCSMP_Process_Log;
import com.haifeiWu.utils.PageBean;

/**
 * 实现日志功能的service
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
public interface LogService {

	public PageBean getInfoPageBean(int pageSize, int page);
	public PageBean getProcessPageBean(int pageSize, int page);
	public List<PHCSMP_LogInfo> findAllStaffLog();
	public List<PHCSMP_Process_Log> findAllProcessLog();
	public void save(PHCSMP_LogInfo log);
	public void save(PHCSMP_Process_Log process);
	public PHCSMP_Process_Log searchEmpEndTime();
	public void update(PHCSMP_Process_Log process);
	public PageBean findLogprocess(String param,int pageSize, int page,String hql);
	public PageBean findLoginfor(String staffName, int i, int page,String hql); 
}
