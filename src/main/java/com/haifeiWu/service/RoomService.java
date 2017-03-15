package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_CardReader;
import com.haifeiWu.entity.PHCSMP_Room;

public interface RoomService {
	public PHCSMP_Room findbyIp(String ip);

	public PHCSMP_Room findByCardReaderID(int cardReaderID);

	public PHCSMP_Room findByRoomID(int roomId);

	public List<PHCSMP_Room> findAllRoom();

	public PHCSMP_CardReader findByCardReaderName(String cardReader_Name);
}
