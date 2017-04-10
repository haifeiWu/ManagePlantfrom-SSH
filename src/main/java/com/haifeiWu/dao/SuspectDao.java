package com.haifeiWu.dao;

import java.util.List;

import com.haifeiWu.base.DaoSupport;
import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.entity.PHCSMP_Dic_IdentifyCard_Type;
import com.haifeiWu.entity.PHCSMP_Suspect;

/**
 * 嫌疑人信息的dao层代码
 * 
 * @author wuhaifei
 * @d2016年8月14日
 */
public interface SuspectDao extends DaoSupport<PHCSMP_Suspect> {

	/**
	 * 根据RoomID查找用户信息
	 * 
	 * @param roomId
	 * @return
	 */
	public PHCSMP_Suspect findByRoomID(int roomId);

	/**
	 * 根据手环id更新信息
	 * 
	 * @param bandId
	 * @param roomId
	 * @return
	 */
	public PHCSMP_Suspect findByBandId(int bandId);

	/**
	 * 根据档案编号查找嫌疑人
	 * 
	 * @return
	 */
	public PHCSMP_Suspect findBySuspectID(String suspectID);

	/**
	 * 查找数据库中的所有的身份证类型的数据
	 * 
	 * @return
	 */
	public List<PHCSMP_Dic_IdentifyCard_Type> findAllIdentifyCardType();

	/**
	 * 
	 * @return
	 */
	public List<PHCSMP_Dic_Action_Cause> findAllSuspectCause();

	// *
	// * 获取数据库中的部分待查嫌疑人数据或者出区嫌疑人数据
	// *
	// * @return

	// PHCSMP_Suspect selectPersonInforByBandID(int bandId);

	public List<PHCSMP_Suspect> getLeavePoliceSuspect();

	/**
	 * 获取数据库中的部分待查嫌疑人数据或者出区嫌疑人数据
	 * 
	 * @return
	 */

	public List<PHCSMP_Suspect> getOnPoliceSuspect();

	public void updateSwitch(int i, String suspect_ID);

	public void updateSuspect(PHCSMP_Suspect suspectInfor);

	/**
	 * 查询最大id
	 * 
	 * @return
	 */
	public String findByMaxID();

	public List<PHCSMP_Suspect> findByCardId(String searchInfor);

	public List<PHCSMP_Suspect> findBySuspectID1(String searchInfor);

	public List<PHCSMP_Suspect> findByCardIdNow(String searchInfor);

	public List<PHCSMP_Suspect> findBySuspectIdNow(String searchInfor);

	public List<PHCSMP_Suspect> findBySuspectName(String searchInfor);

	public List<PHCSMP_Suspect> findBySuspectNameNow(String searchInfor);

	public void updateIs_RecordVideo_DownLoad(int is_RecordVideo_DownLoad,
			int bandID, String identifyCard_Number);
	/**
	 * 查询所有录像下载失败的嫌疑人信息
	 * 
	 * @return
	 */
	public List<PHCSMP_Suspect> findAllVideoDownloadFailSuspectInfor();

	public List<PHCSMP_Suspect> findAllByIsRecordVedio();



}
