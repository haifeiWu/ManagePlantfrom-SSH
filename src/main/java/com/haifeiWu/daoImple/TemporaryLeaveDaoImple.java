package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.TemporaryLeaveDao;
import com.haifeiWu.entity.Temporary_Leave;

@Repository("temporaryLeaveDao")
public class TemporaryLeaveDaoImple extends DaoSupportImpl<Temporary_Leave> implements TemporaryLeaveDao {

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

	// 查找当前嫌疑人出区返回时间为空的信息
	@Override
	public Temporary_Leave findTemporaryLeaveInfoById(String suspectId) {
		String hql = "from Temporary_Leave t where t.suspect_ID=? and t.return_Time=null";
		System.out.println(hql + "=---------------");
		tx = getSession().beginTransaction();// 开启事务

		Query query = getSession().createQuery(hql);
		query.setParameter(0, suspectId);
//		query.setParameter(1, null);
		Temporary_Leave entity = (Temporary_Leave) query.uniqueResult();

		tx.commit();// 提交事务
		return entity;
	}

	@Override
	public Temporary_Leave findTemporaryreturnById(String suspectId) {
		// TODO Auto-generated method stub
		return null;
	}
}
