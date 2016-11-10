package com.manageplantfrom.dao;

public interface RoomInforDao {
	/**
	 * 根据设备id查找房间号
	 * @param deviceId
	 * @return
	 */
	int findRoomIDByDeviceId(String deviceId);

}
