package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.entity.PHCSMP_Dic_IdentifyCard_Type;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.utils.PageBean;

/**
 * 登录犯罪嫌疑人信息的service
 * 
 * @author wuhaifei
 * @d2016年8月14日
 */
public interface SuspectService {
	
	
	/**
	 * 通过身份证查询嫌疑人信息
	 * */
	public PHCSMP_Suspect findByidentifyCard_Number(String identifyCard_Number);
	/**
	 * 视频编号放入嫌疑人表中的vedio_Number列中
	 * */
	public void updatevedio_Number(String vedio_Number,String suspectId);


	
	/**
	 * 历史嫌疑人分页显示
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public PageBean getPageBean(int pageSize, int page);
	/**
	 * 保存嫌疑人信息到数据库
	 * 
	 * @param model
	 */
	public void saveSuspect(PHCSMP_Suspect model);

	public PHCSMP_Suspect findByBandID(int bandId);

	public PHCSMP_Suspect findByRoomID(int roomId);

	/**
	 * 获取数据库中的部分待查嫌疑人数据或者出区嫌疑人数据
	 * 
	 * @return
	 */
	public List<PHCSMP_Suspect> getLeavePoliceSuspect();

	/**
	 * 获取数据库中的部分待查嫌疑人数据或者出区嫌疑人数据
	 * 
	 * @return
	 */
	public List<PHCSMP_Suspect> getOnPoliceSuspect();

	/**
	 * 查找数据库中的所有身份证类型的数据
	 * 
	 * @return 所有身份证类型的数据
	 */
	List<PHCSMP_Dic_IdentifyCard_Type> findAllIdentifyCardType();

	/**
	 * 查詢數據庫的所有案由數據
	 * 
	 * @return
	 */
	List<PHCSMP_Dic_Action_Cause> findAllSuspectCause();

	/**
	 * 获取数据库中的部分待查嫌疑人数据或者出区嫌疑人数据
	 * 
	 * @return
	 */
	public PHCSMP_Suspect findBySuspetcId(String suspectId);

	public void updateSwitch(int cardReader_Switch, String suspect_ID);

	public void updateLeaveState(int recordVideo_State, int process_Now,
			int cardReader_Switch, String suspetcId);

	public void updateSuspect(int room_Now, int process_Now,
			int recordVideo_State, String suspect_ID);

	public void updateSuspect(int room_Now, int process_Now, String suspect_ID);

	// public PHCSMP_Suspect findByRemark(String remark);

	public void updateSuspect(PHCSMP_Suspect suspectInfor);

	public void updateSuspectPhotoPath(String fpath, String spath,
			String suspect_ID);

	public void updateIs_RecordVideo_DownLoad(int is_RecordVideo_DownLoad,
			int bandID, String identifyCard_Number);

	/**
	 * 查询最大的嫌疑人编号
	 * 
	 * @return
	 */
	public String getMaxID();

	// public void updateIs_RecordVideo_DownLoad(int is_RecordVideo_DownLoad,
	// String identifyCard_Number);

	public List<PHCSMP_Suspect> serachInforBySuspectId(String searchInfor);

	public List<PHCSMP_Suspect> findBySuspectName(String searchInfor);

	public List<PHCSMP_Suspect> findByCardId(String searchInfor);

	public List<PHCSMP_Suspect> findByCardIdNow(String searchInfor);

	public List<PHCSMP_Suspect> serachInforBySuspectIdNow(String searchInfor);

	public List<PHCSMP_Suspect> finBySuspectNameNow(String searchInfor);

	public List<PHCSMP_Suspect> findAllVideoDownloadFailSuspectInfor();

	public List<PHCSMP_Suspect> findAllByIsRecordVedio();

	public void updateDetainTime(String detain_Time, String suspectID);
	public void updateIs_filename_DownLoad(int i, int policeId,
			String identificationCard);

}
