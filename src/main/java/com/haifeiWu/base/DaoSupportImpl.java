package com.haifeiWu.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 有了DaoSupport之后，使用通用的更新（update，insert，save），通过嫌疑人ID的查询也可使用这里的查询方法
 */
public class DaoSupportImpl<T> implements DaoSupport<T> {
	/**
	 * 自动装配，默认是byType
	 */
	@Autowired
	private SessionFactory sessionFactory;
	private Transaction tx = null;
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public DaoSupportImpl() {
		Class s = this.getClass();
		ParameterizedType pt = null;
		// 获取当前new的对象的 泛型的父类 类型
		pt = (ParameterizedType) s.getGenericSuperclass();
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

	/**
	 * 该save方法是通用的
	 */
	public void save(T entity) {
		Session session = getSession();
		tx = session.beginTransaction();// 开启事务
		getSession().save(entity);
		tx.commit();// 提交事务
	}

	/**
	 * 该更新方法也是通用的
	 */
	// public void update(T entity) {
	// tx = getSession().beginTransaction();// 开启事务
	// getSession().update(entity);
	// tx.commit();// 提交事务
	// }

	@Override
	public T findSuspectPublicById(String suspectId) {

		String hql = "from " + clazz.getName() + " t where t.suspect_ID=?";
		System.out.println(hql);
		tx = getSession().beginTransaction();// 开启事务

		Query query = getSession().createQuery(hql);
		query.setParameter(0, suspectId);
		@SuppressWarnings("unchecked")
		T entity = (T) query.uniqueResult();
		tx.commit();// 提交事务

		return entity;
	}

	@Override
	public void delete(T entity) {
		tx = getSession().beginTransaction();// 开启事务
		getSession().delete(entity);
		tx.commit();// 提交事务
	}

	@Override
	public void saveBatch(List<T> list) {
		int i = 0;
		tx = getSession().beginTransaction();// 开启事务
		for (T element : list) {
			i++;
			getSession().save(element);
			if (i % 5 == 0) {// 以每5个数据作为一个处理单元
				getSession().flush();
				getSession().clear();
			}
		}
		tx.commit();// 提交事务

	}

	/**
	 * 不唯一结果的异常怎么处理，最后都要返回T，在Action层处理
	 */
	@Override
	public T findByPropertyName(String propertyName, Object value) {
		// String hql = "from " + + " t where t."+propertyName+"=?";
		String hql = "from " + clazz.getName() + " t where t." + propertyName
				+ " = ? ";
		System.out.println(hql);
		tx = getSession().beginTransaction();// 开启事务
		Query query = getSession().createQuery(hql);
		query.setParameter(0, value);
		@SuppressWarnings("unchecked")
		T entity = (T) query.uniqueResult();
		tx.commit();// 提交事务
		return entity;
	}

	@Override
	public List<T> findAllInfor() {
		tx = getSession().beginTransaction();// 开启事务
		String hql = "from " + clazz.getName();
		@SuppressWarnings("unchecked")
		List<T> list = getSession().createQuery(hql).list();
		tx.commit();// 提交事务
		return list;
	}

	@Override
	public List<T> findListByPropertyName(String propertyName, Object value) {
		tx = getSession().beginTransaction();// 开启事务
		String hql = "from " + clazz.getName() + " t where t." + propertyName
				+ " =  ?";
		System.out.println(hql);
		Query query = getSession().createQuery(hql);
		query.setParameter(0, value);

		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		tx.commit();// 提交事务
		return list;
	}

	@Override
	public void update(String hql, Object... args) {
		tx = this.getSession().beginTransaction();// 开启事务
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		query.executeUpdate();
		tx.commit();// 提交事务
	}

	@Override
	public void deleteBySuspectID(String suspect_ID) {
		String hql = "delete from " + clazz.getName() + " where suspect_ID=?";
		tx = getSession().beginTransaction();// 开启事务
		Query query = getSession().createQuery(hql);
		query.setParameter(0, suspect_ID);
		query.executeUpdate();
		tx.commit();// 提交事务
	}

}
