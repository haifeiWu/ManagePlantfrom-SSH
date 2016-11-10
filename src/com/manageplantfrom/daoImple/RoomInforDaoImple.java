package com.manageplantfrom.daoImple;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.manageplantfrom.action.RFID_ReadAction;
import com.manageplantfrom.dao.RoomInforDao;
import com.manageplantfrom.entity.PHCSMP_Room;
import com.manageplantfrom.entity.PHCSMP_Staff;
import com.manageplantfrom.utils.MyHibernateSessionFactory;

public class RoomInforDaoImple implements RoomInforDao {

	private Transaction tx = null;
	private Session session = null;
	private String hql="";
	
	@Override
	public int findRoomIDByDeviceId(String deviceId) {
		session = MyHibernateSessionFactory.getCurrentSession();
		tx = session.beginTransaction();//开启事务
		
		hql = "from PHCSMP_Room where CardReader_ID=?";
		
		Query query = session.createQuery(hql);
		query.setParameter(0, deviceId);
		PHCSMP_Room room = (PHCSMP_Room) query.uniqueResult();
		tx.commit();//提交事务
		return room.getRoom_ID();
	}

}
