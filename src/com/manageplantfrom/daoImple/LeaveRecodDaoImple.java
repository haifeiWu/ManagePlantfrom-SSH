package com.manageplantfrom.daoImple;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.manageplantfrom.base.DaoSupportImpl;
import com.manageplantfrom.dao.LeaveRecodDao;
import com.manageplantfrom.entity.PHCSMP_Leave_Record;
import com.manageplantfrom.entity.Temporary_Leave;
import com.manageplantfrom.utils.MyHibernateSessionFactory;

public class LeaveRecodDaoImple extends DaoSupportImpl<PHCSMP_Leave_Record> implements LeaveRecodDao {

	private Transaction tx = null;
	private Session session = null;
	
	@Override
	public void saveLeaveRecordInfor(List<Temporary_Leave> temporaryLeaves) {
		int i=0;
		session = MyHibernateSessionFactory.getCurrentSession();
		tx = session.beginTransaction();//开启事务
		for (Temporary_Leave temporaryLeave : temporaryLeaves) {
			i++;
			session.save(temporaryLeave);
		    if(i%5==0){//以每5个数据作为一个处理单元
		        session.flush();
		        session.clear();
		    }
		}
		tx.commit();//提交事务
	}

}
