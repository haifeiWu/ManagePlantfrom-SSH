package com.haifeiWu.daoImple;

import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.LineDao;
import com.haifeiWu.entity.PHCSMP_Line;

@Repository("lineDao")
public class LineDaoImple extends DaoSupportImpl<PHCSMP_Line> implements LineDao {

}
