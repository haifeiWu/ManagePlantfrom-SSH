package com.haifeiWu.daoImple;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.StaffDao;
import com.haifeiWu.entity.PHCSMP_Dic_Process;
import com.haifeiWu.entity.PHCSMP_Staff;

@Repository("staffDao")
public class StaffDaoImple extends DaoSupportImpl<PHCSMP_Staff> implements StaffDao {

	private Transaction tx = null;
	private Session session = null;
	private String hql = null;
	
	@Override
	public String getStaffName(int staffid) {
		session = getSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_Staff where Staff_ID=?";

		Query query = session.createQuery(hql);
		query.setParameter(0, staffid);
		PHCSMP_Staff staff = (PHCSMP_Staff) query.uniqueResult();
		tx.commit();// 提交事务
		return staff.getStaff_Name();
	}

}
