package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.Process_LogDao;
import com.haifeiWu.entity.PHCSMP_LogInfo;
import com.haifeiWu.entity.PHCSMP_Process_Log;

@Repository("Process_LogDao")
public class Process_LogDaoImpl extends DaoSupportImpl<PHCSMP_Process_Log>
		implements Process_LogDao {

	/**
	 * 查找processlog
	 */
	@Override
	public List<PHCSMP_Process_Log> findAll_process(String hql) {
		Session session = this.getSession();
		Transaction tx = null;

		List<PHCSMP_Process_Log> list = null;

		try {
			tx = session.beginTransaction();

			Query query = session.createQuery(hql);

			list = query.list();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			e.printStackTrace();
		}
		return list;

	}

	/**
	 * 按endtime为"0-0"查找processlog记录
	 */
	@Override
	public PHCSMP_Process_Log queryByEndTime(String hql,String suspectId) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction tx = null;
		PHCSMP_Process_Log processLog = new PHCSMP_Process_Log();
		tx = session.beginTransaction();
		Query query = session.createQuery(hql).setParameter(0, "0-0");
		query.setParameter(1, suspectId);
		processLog = (PHCSMP_Process_Log) query.uniqueResult();
		tx.commit();
		return processLog;
		
	}
	
	/**
	 * 按分页查找processlog
	 */
	@Override
	public List<PHCSMP_Process_Log> queryProcessByPage(String hql, int offset,
			int pageSize) {
		Session session = this.getSession();
		Transaction tx = null;
		List<PHCSMP_Process_Log> list = null;

		try {
			tx = session.beginTransaction();

			Query query = session.createQuery(hql).setFirstResult(offset)
					.setMaxResults(pageSize);

			list = query.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 按hql的到记录条数
	 */
	@Override
	public int getAllRowCount(String hql) {
		Session session = this.getSession();
		Transaction tx = null;
		int allRows = 0;
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery(hql);

			allRows = query.list().size();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			e.printStackTrace();
		}

		return allRows;
	}
	
	/**
	 * 按带占位符的hql得到记录条数
	 */
	@Override
	public int getAllRowCount(String hql,String param) {
		Session session = this.getSession();
		Transaction tx = null;
		int allRows = 0;
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery(hql);

			allRows = query.setParameter(0, param).list().size();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			e.printStackTrace();
		}

		return allRows;
	}
	
	/**
	 * 按照参数分页查找processlog记录
	 */
	@Override
	public List<PHCSMP_Process_Log> queryprocessByPageApram(String hql, int offset,
			int pageSize, String param) {
		Session session = this.getSession();
		Transaction tx = null;
		List<PHCSMP_Process_Log> list = null;

		try {
			tx = session.beginTransaction();

			Query query = session.createQuery(hql).setFirstResult(offset)
					.setMaxResults(pageSize);
			list = query.setParameter(0, param).list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			e.printStackTrace();
		}
		return list;
	}


	/**
	 * 按staff为"xxx"查找processlog记录
	 */
	@Override
	public PHCSMP_Process_Log queryBystaff(String hql,String suspectId) {
		Session session = this.getSession();
		Transaction tx = null;
		PHCSMP_Process_Log processLog = new PHCSMP_Process_Log();
		tx = session.beginTransaction();
		Query query = session.createQuery(hql).setParameter(0,0);
		query.setParameter(1, suspectId);
		processLog = (PHCSMP_Process_Log) query.uniqueResult();
		tx.commit();
		return processLog;
	}

	@Override
	public List<PHCSMP_Process_Log> findLogBysuspectId(String hql, String suspectId) {
		Session session = this.getSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		Query query = session.createQuery(hql).setParameter(0, suspectId);
		List<PHCSMP_Process_Log> processLog = query.list();
		tx.commit();
		return processLog;
	}

	@Override
	public PHCSMP_Process_Log queryByComplete(String hql,String suspectId) {
		Session session = this.getSession();
		Transaction tx = null;
		PHCSMP_Process_Log processLog = new PHCSMP_Process_Log();

		Query query = session.createQuery(hql);
		query.setParameter(0, suspectId);
		processLog = (PHCSMP_Process_Log) query.uniqueResult();
		
		return processLog;
	}

}
