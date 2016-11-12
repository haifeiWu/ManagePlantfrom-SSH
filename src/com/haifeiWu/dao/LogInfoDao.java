package com.haifeiWu.dao;
import java.util.List;

import com.haifeiWu.entity.PHCSMP_LogInfo;

public interface LogInfoDao {
  
        public List<PHCSMP_LogInfo> queryByPage(String hql, int offset, int pageSize);  
          
        public int getAllRowCount(String hql);  
    }  
