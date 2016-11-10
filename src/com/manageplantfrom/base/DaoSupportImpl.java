package com.manageplantfrom.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.TransactionScoped;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.manageplantfrom.entity.PHCSMP_Suspect;
import com.manageplantfrom.utils.MyHibernateSessionFactory;

public abstract class DaoSupportImpl<T> implements DaoSupport<T> {

	private SessionFactory sessionFactory = MyHibernateSessionFactory.getSessionFactory();
	private Transaction tx = null;
	private Class<T> clazz;

	public DaoSupportImpl() {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
		System.out.println("clazz ---> " + clazz);
	}

	/**
	 * 获取当前可用的Session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(T entity) {
		tx = getSession().beginTransaction();//开启事务
		getSession().save(entity);
		tx.commit();//提交事务
	}

	public void update(T entity) {
		tx = getSession().beginTransaction();//开启事务
		getSession().update(entity);
		tx.commit();//提交事务
	}

	@Override
	public T findInforBySuspetcId(String suspectId) {
		
		String hql = "from "+clazz.getName()+" t where t.suspect_ID=?";
		
		System.out.println(hql);
		tx = getSession().beginTransaction();//开启事务
		
		Query query = getSession().createQuery(hql);
		query.setParameter(0, suspectId);
		T entity = (T) query.uniqueResult();
		
		tx.commit();//提交事务
		
		return entity;
	}
	
}
