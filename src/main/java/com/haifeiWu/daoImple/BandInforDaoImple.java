package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.BandInforDao;
import com.haifeiWu.entity.PHCSMP_Band;

@Repository("bandInforDao")
public class BandInforDaoImple extends DaoSupportImpl<PHCSMP_Band> implements
		BandInforDao {

	private String hql = "";
	private Transaction tx = null;
	private Session session = null;

	/**
	 * 此方法可以抽象到DaoSupport中，提高代码复用度
	 */
	@Override
	public List<PHCSMP_Band> findAllBundInfor() {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_Band";
		@SuppressWarnings("unchecked")
		List<PHCSMP_Band> list = session.createQuery(hql).list();
		tx.commit();// 提交事务

		return list;
	}

	/**
	 * 此处的命名可以优化，同一属性使用多个变量名，影响阅读 bandID是自动生成的主键（1,2,3），remark是手环的编号，类似身份证号
	 */
	@Override
	public int findBandIdByWristId(String wristId) {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务
		hql = "select p.band_ID from PHCSMP_Band p where remark=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, wristId);
		int bundId = (Integer) query.uniqueResult();
		tx.commit();// 提交事务
		return bundId;
	}

	// @Override
	// public void update(PHCSMP_Band entity) {// 重写一下父类的update方法
	// session = this.getSession();
	// tx = session.beginTransaction();// 开启事务
	// session.saveOrUpdate(entity);
	// tx.commit();// 提交事务
	// }

	public void updateIsUsedByBandId(int isUsed, int bandID) {
		hql = "update PHCSMP_Band s set s.is_Used=? where s.band_ID=?";
		update(hql, isUsed, bandID);
	}
	@Override
	public void updateRemarkAndTypeById(String remark,int band_Type,int band_ID)
	{
		hql = "update PHCSMP_Band set remark=?,band_Type=? where band_ID=?";
		update(hql,remark,band_Type,band_ID);
	}
}
