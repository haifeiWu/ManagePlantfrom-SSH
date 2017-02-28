package com.haifeiWu.service;

import com.haifeiWu.utils.PageBean;

/**
 * 实现日志功能的service
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
public interface LogInfoService {

	public PageBean getPageBean(int pageSize, int page);
}
