package com.haifeiWu.daoImple;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.LineDao;
import com.haifeiWu.entity.PHCSMP_Line;

@Repository("lineDao")
public class LineDaoImple extends DaoSupportImpl<PHCSMP_Line> implements
		LineDao {
	private Transaction tx = null;
	private Session session = null;
	private String hql = null;

	public PHCSMP_Line findObj() {
		session = getSession();

		hql = "from PHCSMP_Line";
		Query query = getSession().createQuery(hql);
		PHCSMP_Line entity = (PHCSMP_Line) query.uniqueResult();
		
		return entity;
	}

	public void updateLineUsed(int lineUsed) {
		hql = "update PHCSMP_Line s set s.line_Used=? where s.line_Count=16";
		update(hql, lineUsed);
	}
}
