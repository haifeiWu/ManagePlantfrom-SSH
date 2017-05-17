package com.haifeiWu.daoImple;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.KeepDao;
import com.haifeiWu.entity.PHCSMP_Dic_Keeping_Way;
import com.haifeiWu.entity.PHCSMP_Staff;
@Repository("keepDao")
public class KeepDaoImple extends DaoSupportImpl<PHCSMP_Dic_Keeping_Way> implements KeepDao {
	private Transaction tx = null;
	private Session session = null;
	private String hql = null;
	
	@Override
	public String getkeepname(int belongstaffid) {
		session = getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_Dic_Keeping_Way where keeping_ID=?";

		Query query = session.createQuery(hql);
		query.setParameter(0, belongstaffid);
		PHCSMP_Dic_Keeping_Way keep = (PHCSMP_Dic_Keeping_Way) query.uniqueResult();
		tx.commit();// 提交事务
		return keep.getKeeping_Name();
	}
	
	
}
