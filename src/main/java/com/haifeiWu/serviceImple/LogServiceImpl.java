package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.LogInfoDao;
import com.haifeiWu.dao.Process_LogDao;
import com.haifeiWu.entity.PHCSMP_LogInfo;
import com.haifeiWu.entity.PHCSMP_Process_Log;
import com.haifeiWu.service.LogService;
import com.haifeiWu.utils.PageBean;

/**
 * 日志功能的
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
@Service("logInfoService")
public class LogServiceImpl implements LogService {

	@Autowired
	private LogInfoDao logInfoDao; // Spring框架注入
	@Autowired
	private Process_LogDao process_logDao;
	/**
	 * pageSize为每页显示的记录数 page为当前显示的网页
	 * Loginfo页面分页
	 */
	@Override
	public PageBean getInfoPageBean(int pageSize, int page) {
		PageBean pageBean = new PageBean();

		String hql = "from PHCSMP_LogInfo order by Log_ID desc";

		int allRows = logInfoDao.getAllRowCount(hql);

		int totalPage = pageBean.getTotalPages(pageSize, allRows);

		int currentPage = pageBean.getCurPage(page);

		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);

		List<PHCSMP_LogInfo> list = logInfoDao.queryInfoByPage(hql, offset,
				pageSize);

		pageBean.setLoglist(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);

		return pageBean;
	}

	/**
	 * processLog页面分页
	 */
	@Override
	public PageBean getProcessPageBean(int pageSize, int page) {
		PageBean pageBean = new PageBean();

		String hql = "from PHCSMP_Process_Log order by Log_ID desc";

		int allRows = process_logDao.getAllRowCount(hql);

		int totalPage = pageBean.getTotalPages(pageSize, allRows);

		int currentPage = pageBean.getCurPage(page);

		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);

		List<PHCSMP_Process_Log> list = process_logDao.queryProcessByPage(hql, offset,
				pageSize);

		pageBean.setProcessLogList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);

		return pageBean;
	}

	/**
	 * 加载loginfor
	 */
	@Override
	public List<PHCSMP_LogInfo> findAllStaffLog() {
		String hql="from PHCSMP_LogInfo";
		List<PHCSMP_LogInfo> list = logInfoDao.findAll_log(hql);
		
		return list;
	}
	
	/**
	 * 加载processLog
	 */
	@Override
	public List<PHCSMP_Process_Log> findAllProcessLog() {
		String hql="from PHCSMP_Process_Log";
//		List<PHCSMP_Process_Log> list = process_logDao.findAll_process(hql);
		return null;
	}

	
	/**
	 * 保存loginfor
	 */
	@Override
	public void save(PHCSMP_LogInfo log) {
		logInfoDao.save(log);
		
	}

	/**
	 * 保存processLog
	 */
	@Override
	public void save(PHCSMP_Process_Log process) {
		process_logDao.save(process);
	}

	

	/**
	 * 分页查找loginfor的记录
	 */
	@Override
	public PageBean findLoginfor(String param,int pageSize, int page,String hql) {
		PageBean pageBean = new PageBean();

		int allRows = logInfoDao.getAllRowCount(hql,param);

		int totalPage = pageBean.getTotalPages(pageSize, allRows);

		int currentPage = pageBean.getCurPage(page);

		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);

		List<PHCSMP_LogInfo> list = logInfoDao.queryInfoByPageAparam(hql, offset,
				pageSize,param);

		pageBean.setLoglist(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);

		return pageBean;
	}

	
	/**
	 * 分页查找loginfor的记录
	 */
	@Override
	public PageBean findLogprocess(String param,int pageSize, int page,String hql) {
		PageBean pageBean = new PageBean();

		int allRows = process_logDao.getAllRowCount(hql,param);

		int totalPage = pageBean.getTotalPages(pageSize, allRows);

		int currentPage = pageBean.getCurPage(page);

		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);

		List<PHCSMP_Process_Log> list = process_logDao.queryprocessByPageApram(hql, offset, pageSize, param);

		pageBean.setProcessLogList(list);;
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);

		return pageBean;
	}

	
	
	/**
	 * 查询数据库中endtime为0-0的记录，有且仅可能是最后一条
	 */
	@Override
	public PHCSMP_Process_Log searchEmpEndTime(String suspectId) {
		// TODO Auto-generated method stub
		String hql = "from PHCSMP_Process_Log where end_Time=? and suspect_ID=?";
		return process_logDao.queryByEndTime(hql,suspectId);
		
	}

	/**
	 * 更新endtime
	 */
	@Override
	public void update(PHCSMP_Process_Log process) {
		// TODO Auto-generated method stub
		String hql = "update PHCSMP_Process_Log process set process.end_Time=? where process.log_ID=?";
		process_logDao.update(hql, process.getEnd_Time(),process.getLog_ID());
	}

	/**
	 * 更新staff
	 */
	@Override
	public void updateStaff(PHCSMP_Process_Log process) {
		String hql = "update PHCSMP_Process_Log process set process.staff_ID=? where process.log_ID=?";
		process_logDao.update(hql, process.getStaff_ID(),process.getLog_ID());
		
	}

	/**
	 * 查询数据库中staff为xxx的记录，有且仅可能是最后一条
	 */
	@Override
	public PHCSMP_Process_Log searchEmpstaff(String suspectId) {
		String hql = "from PHCSMP_Process_Log where staff_ID=? and suspect_ID=?";
		return process_logDao.queryBystaff(hql,suspectId);
	}

	@Override
	public List<PHCSMP_Process_Log> findlogBysuspect(String suspectId) {
		String hql = "from PHCSMP_Process_Log where suspect_ID=?";
		return process_logDao.findLogBysuspectId(hql,suspectId);
	}

	@Override
	public void updateNew(PHCSMP_Process_Log process) {
		String hql = "update PHCSMP_Process_Log process set process.complete=?,process.suspected_Cause=?,process.roomId=? where process.log_ID=?";
		process_logDao.update(hql, process.getComplete(),process.getSuspected_Cause(),process.getRoomId(),process.getLog_ID());
	}

	@Override
	public PHCSMP_Process_Log searchEmpcomplete(String suspectId) {
		String hql = "from PHCSMP_Process_Log where complete=?";
		return process_logDao.queryByComplete(hql);
	}

	
	
}