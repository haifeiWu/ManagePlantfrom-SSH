package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.LogInfoDao;
import com.haifeiWu.entity.PHCSMP_LogInfo;
import com.haifeiWu.service.LogInfoService;
import com.haifeiWu.utils.PageBean;

/**
 * 日志功能的
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
@Service("logInfoService")
public class LogInfoServiceImpl implements LogInfoService {

	@Autowired
	private LogInfoDao logInfoDao; // Spring框架注入

	/**
	 * pageSize为每页显示的记录数 page为当前显示的网页
	 */
	@Override
	public PageBean getPageBean(int pageSize, int page) {
		PageBean pageBean = new PageBean();

		String hql = "from PHCSMP_LogInfo";

		int allRows = logInfoDao.getAllRowCount(hql);

		int totalPage = pageBean.getTotalPages(pageSize, allRows);

		int currentPage = pageBean.getCurPage(page);

		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);

		List<PHCSMP_LogInfo> list = logInfoDao.queryByPage(hql, offset,
				pageSize);

		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);

		return pageBean;
	}
}