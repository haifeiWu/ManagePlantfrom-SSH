package com.haifeiWu.dao;

/**
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
public interface RoomInforDao {
	/**
	 * 根据设备id查找房间号
	 * 
	 * @param deviceId
	 * @return
	 */
	int findRoomIDByCardReaderID(String cardReaderID);

}
