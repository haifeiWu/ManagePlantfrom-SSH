package com.haifeiWu.daoImple;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.RoomInforDao;
import com.haifeiWu.entity.PHCSMP_Room;

/**
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
@Repository("roomInforDao")
public class RoomInforDaoImple extends DaoSupportImpl<PHCSMP_Room> implements
		RoomInforDao {

	private Transaction tx = null;
	private Session session = null;
	private String hql = "";
	/**
	 * 根据读卡器的ID查询房间的ID
	 */
	@Override
	public int findRoomIDByCardReaderID(String cardReaderID) {
//		session = this.getSession();
//		tx = session.beginTransaction();// 开启事务
//
//		hql = "from PHCSMP_Room where CardReader_ID=?";
//
//		Query query = session.createQuery(hql);
//		query.setParameter(0, cardReaderID);//deviceId就是读卡器的ID
//		PHCSMP_Room room = (PHCSMP_Room) query.uniqueResult();
//		tx.commit();// 提交事务
//		return room.getRoom_ID();
		return findByPropertyName("CardReader_ID", cardReaderID).getRoom_ID();
	}

}
