package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_CardReader;
import com.haifeiWu.entity.PHCSMP_Room;

public interface RoomService {
	public PHCSMP_Room findbyIp(String ip);
	
	public List<PHCSMP_Room> findListbyIp(String ip);
	
	public PHCSMP_Room findByCardReaderID(int cardReaderID);
	
	public List<PHCSMP_Room> findListByCardReaderID(int cardReaderID);

	public PHCSMP_Room findByRoomID(int roomId);
	
	public List<PHCSMP_Room> findListByRoomID(int roomId);

	public List<PHCSMP_Room> findAllRoom();

	public PHCSMP_CardReader findByCardReaderName(String cardReader_Name);
	
	public void updateRoombyId(PHCSMP_Room room);
	
	public void batchupdate(String roomIdArray,int process_ID);
	
	public void updateprocess_IDById(int cardReader_ID,int room_ID,String room_IPAddress);
	
	public void updateRoomByRoomId(int room_Id,String room_Name, int cardReader_ID,String room_IPAddress);
}
