package com.whfstudio.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import com.haifeiWu.utils.MyHibernateSessionFactory;

public class HibrenateTest {
	public static void main(String[] args) {
		//1.新建Configuration对象
				Configuration cfg = new Configuration().configure();
				//2.通过Configuration创建SessionFactory对象
					//在hibernate3.x中是这种写法
					//SessionFactory sf = cfg.buildSessionFactory();
				//hibernate4.3之前~hibernate4.0
//				ServiceRegistry sr = new ServiceRegistryBuilder()
//									.applySettings(cfg.getProperties())
//									.buildServiceRegistry();
				//hibernate4.3
				ServiceRegistry registry = new StandardServiceRegistryBuilder()
				  					.applySettings(cfg.getProperties())
				  					.build();
				SessionFactory sf = MyHibernateSessionFactory.getSessionFactory();//cfg.buildSessionFactory(registry);
				//3.通过SessionFactory得到Session
				Session session = sf.getCurrentSession();
				//4.通过session对象 得到Transaction对象
				//开启事务
				Transaction tx = session.beginTransaction();
				//5.保存数据
//				PHCSMP_Staff user = new PHCSMP_Staff();
//				user.setReal_Name("张三疯");
//				user.setPassWord("1111");
//				session.save(user);
//				Query query = session.createQuery("from PHCSMP_Staff where Staff_ID=1");
//				List<PHCSMP_Staff> userList = query.list();
//				PHCSMP_Staff user = (PHCSMP_Staff) query.uniqueResult();
				//6.提交事务
				tx.commit();
				//7.关闭session
//				session.close();
				
//				for(int i=0; i<userList.size(); i++){
//					System.out.println(userList.get(i).getReal_Name());
//				}
//				System.out.println("用户名称："+user.getReal_Name()+"密码："+user.getPassWord());
	}
	@Test
	public void ChangeData(){
		//1.新建Configuration对象
		Configuration cfg = new Configuration().configure();
		//2.通过Configuration创建SessionFactory对象
			//在hibernate3.x中是这种写法
			//SessionFactory sf = cfg.buildSessionFactory();
		//hibernate4.3之前~hibernate4.0
//		ServiceRegistry sr = new ServiceRegistryBuilder()
//							.applySettings(cfg.getProperties())
//							.buildServiceRegistry();
		//hibernate4.3
		ServiceRegistry registry = new StandardServiceRegistryBuilder()
		  					.applySettings(cfg.getProperties())
		  					.build();
		SessionFactory sf = MyHibernateSessionFactory.getSessionFactory();//cfg.buildSessionFactory(registry);
		//3.通过SessionFactory得到Session
		Session session = sf.getCurrentSession();
		//4.通过session对象 得到Transaction对象
		//开启事务
		Transaction tx = session.beginTransaction();
		//5.保存数据
//		PHCSMP_Staff user = new PHCSMP_Staff();
//		user.setReal_Name("张三疯");
//		user.setPassWord("1111");
//		session.save(user);
//		Query query = session.createQuery("from PHCSMP_Staff where Staff_ID=1");
//		List<PHCSMP_Staff> userList = query.list();
//		PHCSMP_Staff user = (PHCSMP_Staff) query.uniqueResult();
		//6.提交事务
		tx.commit();
		//7.关闭session
//		session.close();
		
//		for(int i=0; i<userList.size(); i++){
//			System.out.println(userList.get(i).getReal_Name());
//		}
//		System.out.println("用户名称："+user.getReal_Name()+"密码："+user.getPassWord());
	}
}
