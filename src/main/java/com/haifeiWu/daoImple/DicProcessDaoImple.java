package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.DicProcessDao;
import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.entity.PHCSMP_Dic_Process;

@Repository("dicProcessDao")
public class DicProcessDaoImple extends DaoSupportImpl<PHCSMP_Dic_Process> implements DicProcessDao {

	
	private Transaction tx = null;
	private Session session = null;
	private String hql = null;

	
	/**
	 * 得到process相应名称
	 */
	@Override
	public String getprocessName(int process) {
		session = getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_Dic_Process where process_ID=?";

		Query query = session.createQuery(hql);
		query.setParameter(0, process);
		PHCSMP_Dic_Process Process = (PHCSMP_Dic_Process) query.uniqueResult();
		tx.commit();// 提交事务
		return Process.getProcess_Name();
	}

}
