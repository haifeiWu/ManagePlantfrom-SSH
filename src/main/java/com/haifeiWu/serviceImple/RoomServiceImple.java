package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.CardReaderDao;
import com.haifeiWu.dao.RoomInforDao;
import com.haifeiWu.entity.PHCSMP_CardReader;
import com.haifeiWu.entity.PHCSMP_Room;
import com.haifeiWu.service.RoomService;

@Service("roomService")
public class RoomServiceImple extends DaoSupportImpl<PHCSMP_Room> implements
		RoomService {
	
	
	
	@Autowired
	private RoomInforDao roomInforDao;
	@Autowired
	private CardReaderDao cardReaderDao;

	@Override
	public PHCSMP_Room findbyIp(String ip) {
		return roomInforDao.findByPropertyName("room_IPAddress", ip);
	}

	@Override
	public PHCSMP_Room findByRoomID(int roomId) {
		if (roomInforDao == null) {
			System.out
					.println("------------->roomInforDao------null<----------");
			System.out.println("null");
			return null;
		}
		return roomInforDao.findByRoomID(roomId);
	}

	@Override
	public PHCSMP_Room findByCardReaderID(int cardReaderID) {
		return roomInforDao.findByPropertyName("cardReader_ID", cardReaderID);
	}
	

	@Override
	public List<PHCSMP_Room> findAllRoom() {
		return roomInforDao.findAllInfor();
	}

	@Override
	public PHCSMP_CardReader findByCardReaderName(String cardReader_Name) {
		return cardReaderDao.findByPropertyName("cardReader_Name",
				cardReader_Name);
	}

	@Override
	public List<PHCSMP_Room> findListByCardReaderID(int cardReaderID) {
		return roomInforDao.findListByPropertyName("cardReader_ID", cardReaderID);
	}

	@Override
	public List<PHCSMP_Room> findListbyIp(String ip) {
		return roomInforDao.findListByPropertyName("room_IPAddress", ip);
	}

	@Override
	public List<PHCSMP_Room> findListByRoomID(int roomId) {
		if (roomInforDao == null) {
			System.out
					.println("------------->roomInforDao------null<----------");
			System.out.println("null");
			return null;
		}
		return roomInforDao.findListByRoomID(roomId);
	}

	/**
	 * 修改房间的信息
	 */
	
	@Override
	public void updateRoombyId(PHCSMP_Room room) {
		 roomInforDao.updateroom(room);
		
	}
/**
 * 初始化
 */
	@Override
	public void updateprocess_IDById(int cardReader_ID,int room_ID,String room_IPAddress){
		roomInforDao.updateprocess_IDById(cardReader_ID, room_ID, room_IPAddress);
	}
	
	@Override
	public void batchupdate(String roomIdArray,int process_ID) {
			roomInforDao.batchupdate(roomIdArray,process_ID);
	}

}
