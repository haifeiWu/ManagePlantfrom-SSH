package com.haifeiWu.dao;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.entity.PHCSMP_Room;

/**
 * 
 * @author wuhaifei
 * @d2016年11月28日
 */
public interface RoomInforDao extends DaoSupport<PHCSMP_Room>{
	/**
	 * 根据设备id查找房间号
	 * 
	 * @param deviceId
	 * @return
	 */
	int findRoomIDByCardReaderID(int cardReaderID);

	public PHCSMP_Room findByRoomID(int roomID);
}
