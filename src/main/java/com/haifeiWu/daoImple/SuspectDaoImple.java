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

	@Override
	public PHCSMP_Suspect findByRoomID(int roomId) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务
		hql = "from PHCSMP_Suspect where room_Now=? and process_Now!=-1";
		Query query = session.createQuery(hql);
		query.setParameter(0, roomId);
		PHCSMP_Suspect phcsmp_Suspect = (PHCSMP_Suspect) query.uniqueResult();
		tx.commit();// 提交事务
		return phcsmp_Suspect;
	}

	@Override
	public PHCSMP_Suspect findByBandId(int bandId) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务
		hql = "from PHCSMP_Suspect where band_ID=? and process_Now!=-1";
		Query query = session.createQuery(hql);
		query.setParameter(0, bandId);
		PHCSMP_Suspect phcsmp_Suspect = (PHCSMP_Suspect) query.uniqueResult();
		tx.commit();// 提交事务
		return phcsmp_Suspect;
	}

	@Override
	public PHCSMP_Suspect findBySuspectID(String suspectID) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_Suspect where suspect_ID=? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, suspectID);
		PHCSMP_Suspect phcsmp_Suspect = (PHCSMP_Suspect) query.uniqueResult();
		tx.commit();// 提交事务
		return phcsmp_Suspect;
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

	// @Override
	// public PHCSMP_Suspect selectPersonInforByBandID(int bandId) {
	// session = this.getSession();
	// tx = session.beginTransaction();// 开启事务

	// hql = "from PHCSMP_Suspect where band_ID=? and process_Now!=-1";

	// Query query = session.createQuery(hql);
	// query.setParameter(0, bandId);
	// PHCSMP_Suspect phcsmp_Suspect = (PHCSMP_Suspect) query.uniqueResult();

	// }
	public List<PHCSMP_Suspect> getLeavePoliceSuspect() {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "FROM PHCSMP_Suspect WHERE process_Now = -1";
		Query query = session.createQuery(hql);
		// query.setParameter(0, Process_ID);
		@SuppressWarnings("unchecked")
		List<PHCSMP_Suspect> phcsmp_Suspect = query.list();
		tx.commit();// 提交事务
		return phcsmp_Suspect;
	}

	@Override
	public List<PHCSMP_Suspect> getOnPoliceSuspect() {

		session = this.getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "FROM PHCSMP_Suspect WHERE process_Now != -1";
		Query query = session.createQuery(hql);

		// query.setParameter(0, Process_Now);
		@SuppressWarnings("unchecked")
		List<PHCSMP_Suspect> phcsmp_Suspect = query.list();

		tx.commit();// 提交事务
		return phcsmp_Suspect;
	}

	@Override
	public void updateSwitch(int i, String suspect_ID) {
		hql = "update PHCSMP_Suspect s set s.cardReader_Switch=? where s.suspect_ID=?";
		update(hql, i, suspect_ID);
	}

	@Override
	public void updateSuspect(PHCSMP_Suspect suspectInfor) {
		deleteBySuspectID(suspectInfor.getSuspect_ID());
		save(suspectInfor);
	}

	@Override
	public String findByMaxID() {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "select suspect_ID from PHCSMP_Suspect where id = (select max(id) from PHCSMP_Suspect)";
		Query query = session.createQuery(hql);

		String suspect_ID = (String) query.uniqueResult();
		tx.commit();// 提交事务
		return suspect_ID;
	}

	public List<PHCSMP_Suspect> findBySuspectID1(String searchInfor) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_Suspect where suspect_ID=? and process_Now=-1";
		Query query = session.createQuery(hql);
		query.setParameter(0, searchInfor);
		@SuppressWarnings("unchecked")
		List<PHCSMP_Suspect> phcsmp_Suspect = query.list();
		tx.commit();// 提交事务
		return phcsmp_Suspect;
	}

	@Override
	public List<PHCSMP_Suspect> findByCardId(String searchInfor) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_Suspect where identifyCard_Number=? and process_Now=-1";
		Query query = session.createQuery(hql);
		query.setParameter(0, searchInfor);
		@SuppressWarnings("unchecked")
		List<PHCSMP_Suspect> phcsmp_Suspect = query.list();
		tx.commit();// 提交事务
		return phcsmp_Suspect;
	}

	@Override
	public List<PHCSMP_Suspect> findByCardIdNow(String searchInfor) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务
		hql = "from PHCSMP_Suspect where identifyCard_Number=? and process_Now!=-1";
		Query query = session.createQuery(hql);
		query.setParameter(0, searchInfor);
		@SuppressWarnings("unchecked")
		List<PHCSMP_Suspect> phcsmp_Suspect = query.list();
		tx.commit();// 提交事务
		return phcsmp_Suspect;
	}

	@Override
	public List<PHCSMP_Suspect> findBySuspectIdNow(String searchInfor) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_Suspect where suspect_ID=? and process_Now!=-1";
		Query query = session.createQuery(hql);
		query.setParameter(0, searchInfor);
		@SuppressWarnings("unchecked")
		List<PHCSMP_Suspect> phcsmp_Suspect = query.list();
		tx.commit();// 提交事务
		return phcsmp_Suspect;
	}

	@Override
	public List<PHCSMP_Suspect> findBySuspectName(String searchInfor) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务
		hql = "from PHCSMP_Suspect where suspect_Name=? and process_Now=-1";
		Query query = session.createQuery(hql);
		query.setParameter(0, searchInfor);
		@SuppressWarnings("unchecked")
		List<PHCSMP_Suspect> phcsmp_Suspect = query.list();
		tx.commit();// 提交事务
		return phcsmp_Suspect;
	}

	@Override
	public List<PHCSMP_Suspect> findBySuspectNameNow(String suspect_Name) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务
		hql = "from PHCSMP_Suspect where suspect_Name=? and process_Now!=-1";
		Query query = session.createQuery(hql);
		query.setParameter(0, suspect_Name);
		@SuppressWarnings("unchecked")
		List<PHCSMP_Suspect> phcsmp_Suspect = query.list();
		tx.commit();// 提交事务
		return phcsmp_Suspect;
	}

	public void updateIs_RecordVideo_DownLoad(int is_RecordVideo_DownLoad,
			int bandID, String identifyCard_Number) {
		hql = "update PHCSMP_Suspect s set s.is_RecordVideo_DownLoad=? ,s.band_ID=? where s.identifyCard_Number=?";
		update(hql, is_RecordVideo_DownLoad, identifyCard_Number);

	}

}
