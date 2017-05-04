package com.haifeiWu.dao;

import java.util.List;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_Room;

/**
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
public interface RoomInforDao extends DaoSupport<PHCSMP_Room> {
	/**
	 * 根据设备id查找房间号
	 * 
	 * @param deviceId
	 * @return
	 */
	int findRoomIDByCardReaderID(int cardReaderID);

	public PHCSMP_Room findByRoomID(int roomID);
	public List<PHCSMP_Room> findListByRoomID(int roomID);

	/**
	 * 查找所有的房间信息
	 */
	public List<PHCSMP_Room> findRoom();
	
	/**
	 * 修改房间信息
	 */
	public void updateroom(PHCSMP_Room room);
	public void batchupdate(String roomIdArray,int process_ID);
	public void updateprocess_IDById(int cardReader_ID,int room_ID,String room_IPAddress);
}
