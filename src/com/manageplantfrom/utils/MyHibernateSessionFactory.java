package com.manageplantfrom.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * 获取会话工厂的实现类
 * @author wuhaifei
 *
 * @date 2016年8月10日
 */
public class MyHibernateSessionFactory {
	
	private static SessionFactory sessionFactory;//会话工厂属性
	
	private MyHibernateSessionFactory(){
		
	}

	public static SessionFactory getSessionFactory(){
		if(sessionFactory == null){
			Configuration config = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
												.applySettings(config.getProperties())
												.buildServiceRegistry();
			return sessionFactory = config.buildSessionFactory(serviceRegistry);
		}else{
			return sessionFactory;
		}
	}
	/***
	 * 获取会话对象
	 * @return session
	 */
	public static Session getCurrentSession(){
		return getSessionFactory().getCurrentSession();
	}
}
