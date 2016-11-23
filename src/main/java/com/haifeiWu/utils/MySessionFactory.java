package com.haifeiWu.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 获取会话工厂的实现类
 * @author wuhaifei
 *
 * @date 2016年8月10日
 */
@Repository
public class MySessionFactory {
	
	@Autowired
	private static SessionFactory sessionFactory;//会话工厂属性
	
	private MySessionFactory(){
		
	}

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	/***
	 * 获取会话对象
	 * @return session
	 */
	public static Session getCurrentSession(){
		return getSessionFactory().getCurrentSession();
	}
}
