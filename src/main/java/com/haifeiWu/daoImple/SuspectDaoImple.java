package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.SuspectDao;
import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.entity.PHCSMP_Dic_IdentifyCard_Type;
import com.haifeiWu.entity.PHCSMP_Suspect;

/**
 * SuspectDaoImple dao实现层
 * 
 * @author wuhaifei
 * @d2016年9月21日
 */
@Repository("suspectDao")
public class SuspectDaoImple extends DaoSupportImpl<PHCSMP_Suspect> implements
		SuspectDao {

	private Transaction tx = null;
	private Session session = null;
	private String hql = "";
	/**
	 * 调用DaoSupport中的save即可
	 */
	@Override
	public void suspectDao(PHCSMP_Suspect model) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务
		session.save(model);
		tx.commit();// 提交事务
	}
	/**
	 * 
	 */
	@Override
	public PHCSMP_Suspect findInfroByActiveCodeAndBandID(int roomId, int bandId) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务
		//ActiveCode和is_Active和roomId是一个属性，就是房间号
		hql = "from PHCSMP_Suspect where is_Active=? and band_ID=? and process_Now!=-1";
		Query query = session.createQuery(hql);
		query.setParameter(0, roomId);
		query.setParameter(1, bandId);
		PHCSMP_Suspect phcsmp_Suspect = (PHCSMP_Suspect) query.uniqueResult();

		tx.commit();// 提交事务
		return phcsmp_Suspect;
	}
	//通过房间号找嫌疑人
	@Override
	public PHCSMP_Suspect findInfroByActiveCode(int roomId) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_Suspect where room_Now=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, roomId);
		PHCSMP_Suspect phcsmp_Suspect = (PHCSMP_Suspect) query.uniqueResult();

		tx.commit();// 提交事务
		return phcsmp_Suspect;
	}
	/**
	 * 通过手环ID和房间号更新嫌疑人信息
	 */
	@Override
	public int updateSuspectInforByBandId(int bandId, int roomId) {

		session = this.getSession();
		tx = session.beginTransaction();// 开启事务
		//此处is_Active和roomID一致
		hql = "update PHCSMP_Suspect p set p.is_Active= ? where p.band_ID= ? and process_Now!=-1";
		Query query = session.createQuery(hql);
		query.setInteger(0, roomId);
		query.setInteger(1, bandId);
		int count = query.executeUpdate();
		tx.commit();// 提交事务
		return count;
	}
	/**
	 * 查询所有证件类型,不需要经过反射，
	 */
	@Override
	public List<PHCSMP_Dic_IdentifyCard_Type> findAllIdentifyCardType() {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_Dic_IdentifyCard_Type";
		@SuppressWarnings("unchecked")
		List<PHCSMP_Dic_IdentifyCard_Type> list = session.createQuery(hql)
				.list();
		tx.commit();// 提交事务

		return list;
	}
	/**
	 * 查询所有的案由
	 */
	@Override
	public List<PHCSMP_Dic_Action_Cause> findAllSuspectCause() {
		session = this.getSession();// MySessionFactory.getCurrentSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_Dic_Action_Cause";
		@SuppressWarnings("unchecked")
		List<PHCSMP_Dic_Action_Cause> list = session.createQuery(hql).list();
		tx.commit();// 提交事务

		return list;
	}
	/**
	 * 根据手环ID查询嫌疑人，新的需求中是否还需要对其他字段进行判断？（因为使用过同一ID的有多个嫌疑人）
	 * 该方法需要更改
	 * 
	 */
	@Override
	public PHCSMP_Suspect selectPersonInforByBandID(int bandId) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_Suspect where band_ID=? and process_Now!=-1";
		Query query = session.createQuery(hql);
		query.setParameter(0, bandId);
		PHCSMP_Suspect phcsmp_Suspect = (PHCSMP_Suspect) query.uniqueResult();

		tx.commit();// 提交事务
		return phcsmp_Suspect;
	}
	/**
	 * 获取数据库中的部分待查嫌疑人数据或者出区嫌疑人数据
	 * 此方法在统计的页面使用
	 */
	@Override
	public List<PHCSMP_Suspect> getCheckingSuspect(int Process_Now) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "FROM PHCSMP_Suspect WHERE Process_Now = ?";
		Query query = session.createQuery(hql).setFirstResult(0)
				.setMaxResults(5);
		query.setParameter(0, Process_Now);
		@SuppressWarnings("unchecked")
		List<PHCSMP_Suspect> phcsmp_Suspect = query.list();

		tx.commit();// 提交事务
		return phcsmp_Suspect;
	}
}
