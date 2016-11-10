package com.manageplantfrom.daoImple;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.manageplantfrom.dao.UserDao;
import com.manageplantfrom.entity.PHCSMP_Staff;
import com.manageplantfrom.utils.MyHibernateSessionFactory;

public class UserDaoImple implements UserDao {
	private Transaction tx = null;
	private Session session = null;
	private String hql="";

	@Override
	public PHCSMP_Staff findUserByStaffNameAndPwd(String staff_Name,
			String passWord) {
		
			session = MyHibernateSessionFactory.getCurrentSession();
			tx = session.beginTransaction();//开启事务
			
			hql = "from PHCSMP_Staff where Staff_Name=? and PassWord=?";
			
			Query query = session.createQuery(hql);
			query.setParameter(0, staff_Name);
			query.setParameter(1,passWord);
			PHCSMP_Staff user = (PHCSMP_Staff) query.uniqueResult();
			tx.commit();//提交事务
			return user;
	}

}
