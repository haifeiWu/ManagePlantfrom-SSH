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
public class MySessionFactory {
	
	@Autowired
	private SessionFactory sessionFactory;//会话工厂属性
	
	private MySessionFactory(){
		
	}
	//私有化的内部类
    private static class SingletonInstance{
    	static MySessionFactory instance = new MySessionFactory();
    }
    
    public static MySessionFactory getInstance(){
    	return SingletonInstance.instance;
    }

	public static SessionFactory getSessionFactory(){
		return getInstance().sessionFactory;
	}
	/***
	 * 获取会话对象
	 * @return session
	 */
	public static Session getCurrentSession(){
		return getSessionFactory().getCurrentSession();
	}
}
