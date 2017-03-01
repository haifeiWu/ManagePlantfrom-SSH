package com.haifeiWu.serviceImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.RoomInforDao;
import com.haifeiWu.entity.PHCSMP_Room;
import com.haifeiWu.service.RoomService;

@Service("roomService")
public class RoomServiceImple implements RoomService {
	
	@Autowired
	private RoomInforDao roomInforDao;
	
	@Override
	public PHCSMP_Room findbyIp(String ip) {
		return roomInforDao.findByPropertyName("room_IPAddress", ip);
	}

}
