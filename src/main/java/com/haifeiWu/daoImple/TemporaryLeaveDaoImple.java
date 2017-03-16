package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.TemporaryLeaveDao;
import com.haifeiWu.entity.PHCSMP_BelongingS;
import com.haifeiWu.entity.Temporary_Leave;

@Repository("temporaryLeaveDao")
public class TemporaryLeaveDaoImple extends DaoSupportImpl<Temporary_Leave>
		implements TemporaryLeaveDao {
			
	private Transaction tx = null;
	private Session session = null;
	private String hql = null;

	@SuppressWarnings("unchecked")
	@Override
	public List<Temporary_Leave> selectTemporaryLeavesInfor(String suspectId) {
		session = getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from Temporary_Leave where suspect_ID=?";

		Query query = session.createQuery(hql);
		query.setParameter(0, suspectId);
		List<Temporary_Leave> temporary_Leaves = query.list();
		tx.commit();// 提交事务
		return temporary_Leaves;
	}
}
