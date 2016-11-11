package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.SuspectDao;
import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.entity.PHCSMP_Dic_IdentifyCard_Type;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.utils.MyHibernateSessionFactory;

/**
 * SuspectDaoImple  dao实现层
 * @author wuhaifei
 * @d2016年9月21日
 */
public class SuspectDaoImple extends DaoSupportImpl<PHCSMP_Suspect> implements SuspectDao {

	private Transaction tx = null;
	private Session session = null;
	private String hql="";
	
	@Override
	public void suspectDao(PHCSMP_Suspect model) {
		session = MyHibernateSessionFactory.getCurrentSession();
		tx = session.beginTransaction();//开启事务
		session.save(model);
		tx.commit();//提交事务
	}

	@Override
	public PHCSMP_Suspect findInfroByActiveCodeAndBandID(int roomId, int bandId) {
		session = MyHibernateSessionFactory.getCurrentSession();
		tx = session.beginTransaction();//开启事务
		
		hql = "from PHCSMP_Suspect where is_Active=? and band_ID=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, roomId);
		query.setParameter(1,bandId);
		PHCSMP_Suspect phcsmp_Suspect = (PHCSMP_Suspect) query.uniqueResult();
		
		tx.commit();//提交事务
		return phcsmp_Suspect;
	}

	@Override
	public PHCSMP_Suspect findInfroByActiveCode(int roomId) {
		session = MyHibernateSessionFactory.getCurrentSession();
		tx = session.beginTransaction();//开启事务
		
		hql = "from PHCSMP_Suspect where is_Active=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, roomId);
		PHCSMP_Suspect phcsmp_Suspect = (PHCSMP_Suspect) query.uniqueResult();
		
		tx.commit();//提交事务
		return phcsmp_Suspect;
	}

	@Override
	public int updateSuspectInforByBandId(int bandId, int roomId) {
		
		session = MyHibernateSessionFactory.getCurrentSession();
		tx = session.beginTransaction();//开启事务
		hql="update PHCSMP_Suspect p set p.is_Active= ? where p.band_ID= ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, roomId);
		query.setInteger(1,bandId);
		int count = query.executeUpdate();
		tx.commit();//提交事务
		return count;
	}

	@Override
	public List<PHCSMP_Dic_IdentifyCard_Type> findAllIdentifyCardType() {
		session = MyHibernateSessionFactory.getCurrentSession();
		tx = session.beginTransaction();//开启事务
		
		hql = "from PHCSMP_Dic_IdentifyCard_Type";
		List list = session.createQuery(hql).list();
		tx.commit();//提交事务
		
		return list;
	}

	@Override
	public List<PHCSMP_Dic_Action_Cause> findAllSuspectCause() {
		session = MyHibernateSessionFactory.getCurrentSession();
		tx = session.beginTransaction();//开启事务
		
		hql = "from PHCSMP_Dic_Action_Cause";
		List list = session.createQuery(hql).list();
		tx.commit();//提交事务
		
		return list;
	}

	@Override
	public PHCSMP_Suspect selectPersonInforByBandID(int bandId) {
		session = MyHibernateSessionFactory.getCurrentSession();
		tx = session.beginTransaction();//开启事务
		
		hql = "from PHCSMP_Suspect where band_ID=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, bandId);
		PHCSMP_Suspect phcsmp_Suspect = (PHCSMP_Suspect) query.uniqueResult();
		
		tx.commit();//提交事务
		return phcsmp_Suspect;
	}

}
