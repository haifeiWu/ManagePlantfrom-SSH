package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.LogInfoDao;
import com.haifeiWu.entity.PHCSMP_LogInfo;
import com.haifeiWu.entity.PHCSMP_Process_Log;

/**
 * 日志功能的dao实现
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
@Repository("logInfoDao")
public class LogInfoDaoImpl extends DaoSupportImpl<PHCSMP_LogInfo> implements
		LogInfoDao {


	/**
	 * 通过hql语句得到数据库中记录总数
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
	 * 使用hibernate提供的分页功能，得到分页显示的数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PHCSMP_LogInfo> queryInfoByPage(String hql, int offset, int pageSize) {
		Session session = this.getSession();
		Transaction tx = null;
		List<PHCSMP_LogInfo> list = null;

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
	 * 通过条件查找日志记录
	 * @param hql
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<PHCSMP_LogInfo> queryInfoByPageAparam(String hql, int offset, int pageSize,String param) {
		Session session = this.getSession();
		Transaction tx = null;
		List<PHCSMP_LogInfo> list = null;

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
	 * 查找loginfor
	 */
	@Override
	public List<PHCSMP_LogInfo> findAll_log(String hql) {
		Session session=this.getSession();
		Transaction tx=null;
		
			List<PHCSMP_LogInfo> list=null;
		
			//List<PHCSMP_Process_Log> list=null;
		
		
		
		
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery(hql);
			
			list=query.list();
				
				

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
	 * 通过带占位符的hql语句得到数据库中记录总数
	 */
	@Override
	public int getAllRowCount(String hql, String param) {
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

	
	

	
}