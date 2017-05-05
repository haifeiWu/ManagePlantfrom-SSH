package com.haifeiWu.daoImple;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.UserDao;
import com.haifeiWu.entity.PHCSMP_Role;
import com.haifeiWu.entity.PHCSMP_Staff;

/**
 * 办案民警staff
 * 
 * @author WXY
 * 
 */
@Repository("userDao")
public class UserDaoImple extends DaoSupportImpl<PHCSMP_Staff> implements
		UserDao {

	private Transaction tx = null;
	private Session session = null;
	private String hql = "";

	/**
	 * 通过用户名和密码查找民警，登录功能使用
	 */
	@Override
	public PHCSMP_Staff findUserByStaffNameAndPwd(String staff_Name,
			String passWord) {

		session = getSession();// MySessionFactory.getCurrentSession();
		tx = session.beginTransaction();// 开启事务

		hql = "from PHCSMP_Staff where Staff_Name=? and PassWord=?";

		Query query = session.createQuery(hql);
		query.setParameter(0, staff_Name);
		query.setParameter(1, passWord);
		PHCSMP_Staff user = (PHCSMP_Staff) query.uniqueResult();
		tx.commit();// 提交事务
		return user;
	}

	@Override
	public List<PHCSMP_Staff> findAllStaffs() {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务
		System.out.println("Dao----1----");
		hql = "from PHCSMP_Staff";
		@SuppressWarnings("unchecked")
		List<PHCSMP_Staff> list = session.createQuery(hql).list();
		tx.commit();// 提交事务
		System.out.println("Dao--2------");
		return list;
	}

	@Override
	public void saveStaff(PHCSMP_Staff staff) {
		int i = 0;
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务

		session.save(staff);

		tx.commit();// 提交事务
	}

	@Override
	public List<PHCSMP_Staff> search(String key, String val) {
		session = getSession();// MySessionFactory.getCurrentSession();
		tx = session.beginTransaction();// 开启事务
		List<PHCSMP_Staff> list = new ArrayList();
		hql = "from PHCSMP_Staff where 1=1";
		switch (key) {
		case "Staff_Name":
			hql += "and Staff_Name like '%" + val + "%'";
			break;
		case "real_Name":
			hql += "and real_Name like '%" + val + "%'";
			break;

		default:
			break;
		}
		Query query = session.createQuery(hql);
		list = query.list();
		tx.commit();// 提交事务
		return list;

	}

	@Override
	public void deleteByStaffId(int id) {
		String hql = "delete from PHCSMP_Staff where Staff_ID=?";
		tx = getSession().beginTransaction();// 开启事务
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
		tx.commit();// 提交事务

	}

	@Override
	public List<PHCSMP_Role> findAllRole() {
		session = this.getSession();
		tx = session.beginTransaction();// 开启事务
		hql = "from PHCSMP_role";
		@SuppressWarnings("unchecked")
		List<PHCSMP_Role> list = session.createQuery(hql).list();
		tx.commit();// 提交事务
		return list;
	}

	@Override
	public void updateStaff(PHCSMP_Staff model) {
		save(model);
	}

}
