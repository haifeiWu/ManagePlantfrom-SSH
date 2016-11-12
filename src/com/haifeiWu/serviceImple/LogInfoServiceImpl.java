package com.haifeiWu.serviceImple;

import java.util.List;

import com.haifeiWu.dao.LogInfoDao;
import com.haifeiWu.daoImple.LogInfoDaoImpl;
import com.haifeiWu.entity.PHCSMP_LogInfo;
import com.haifeiWu.service.LogInfoService;
import com.haifeiWu.utils.PageBean;


public class LogInfoServiceImpl implements LogInfoService{
	
        private LogInfoDao logInfoDao = new LogInfoDaoImpl();  
          
        /** 
         * pageSize为每页显示的记录数 
         * page为当前显示的网页 
         */  
        @Override  
        public PageBean getPageBean(int pageSize, int page)  
        {  
            PageBean pageBean = new PageBean();  
              
            String hql = "from PHCSMP_LogInfo";  
              
            int allRows = logInfoDao.getAllRowCount(hql);  
              
            int totalPage = pageBean.getTotalPages(pageSize, allRows);  
              
            int currentPage = pageBean.getCurPage(page);  
              
            int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);  
              
            List<PHCSMP_LogInfo> list = logInfoDao.queryByPage(hql, offset, pageSize);  
              
            pageBean.setList(list);  
            pageBean.setAllRows(allRows);  
            pageBean.setCurrentPage(currentPage);  
            pageBean.setTotalPage(totalPage);  
              
            return pageBean;  
        }  
    }  