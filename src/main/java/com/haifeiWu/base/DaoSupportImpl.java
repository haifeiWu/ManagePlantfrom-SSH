package com.haifeiWu.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;







import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.entity.PHCSMP_Dic_IdentifyCard_Type;
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
		Class s=this.getClass();
		ParameterizedType pt=null;
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
		tx = getSession().beginTransaction();// 开启事务
		getSession().save(entity);
		tx.commit();// 提交事务
	}
	/**
	 * 该更新方法也是通用的
	 */
	public void update(T entity) {
		tx = getSession().beginTransaction();// 开启事务
		getSession().update(entity);
		tx.commit();// 提交事务
	}
	
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

	@Override
	public T findByPropertyName(String propertyName, Object value) {
		//String hql = "from " +  + " t where t."+propertyName+"=?";
		String hql = "from " + clazz.getName() + " t where t."+propertyName+" = ? ";
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
		List<T> list = getSession().createQuery(hql)
				.list();
		tx.commit();// 提交事务
		return list;
	}

	@Override
	public List<T> findListByPropertyName(String propertyName, Object value) {
		String hql = "from " + clazz.getName() + " t where t."+propertyName+"="+value;
		System.out.println(hql);
		tx = getSession().beginTransaction();// 开启事务
		@SuppressWarnings("unchecked")
		List<T> list = getSession().createQuery(hql)
				.list();
		tx.commit();// 提交事务
		return list;
	}
    //查找当前嫌疑人出区返回时间为空的信息
	@Override
	public T findTemporaryLeaveInfoById(String suspectId) {
		String hql = "from " + clazz.getName() + " t where t.suspect_ID=? and t.return_Time is null";
		System.out.println(hql+"=---------------");
		tx = getSession().beginTransaction();// 开启事务
		Query query = getSession().createQuery(hql);
		query.setParameter(0, suspectId);
		@SuppressWarnings("unchecked")
		T entity = (T) query.uniqueResult();
		tx.commit();// 提交事务
		return entity;
	}

	
}
