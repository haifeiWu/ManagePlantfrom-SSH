package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.RoomInforDao;
import com.haifeiWu.entity.PHCSMP_Room;
import com.haifeiWu.service.RoomService;

@Service("roomService")
public class RoomServiceImple extends DaoSupportImpl<PHCSMP_Room> implements
		RoomService {

	@Autowired
	private RoomInforDao roomInforDao;

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

}
