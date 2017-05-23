package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haifeiWu.dao.SuspectDao;
import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.entity.PHCSMP_Dic_IdentifyCard_Type;
import com.haifeiWu.entity.PHCSMP_LogInfo;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.PageBean;

/**
 * 登录犯罪嫌疑人信息的service实现类
 * 
 * @author wuhaifei
 * @d2016年8月14日
 */
@Service("suspectService")
public class SuspectServiceImple implements SuspectService {

	@Autowired
	private SuspectDao suspectDao;
	
	
	/**
	 * 通过身份证查询嫌疑人信息
	 * */
	@Override
	public PHCSMP_Suspect findByidentifyCard_Number(String identifyCard_Number) {
		return suspectDao.findByidentifyCard_Number(identifyCard_Number);
	}
	
	/**
	 * 视频编号放入嫌疑人表中的vedio_Number列中
	 * */
	@Override
	public void updatevedio_Number(String vedio_Number, String suspectId) {
		String hql = "update PHCSMP_Suspect s set s.vedio_Number=? where s.suspect_ID=?";
		suspectDao.update(hql, vedio_Number, suspectId);		
	}

	@Override
	public void saveSuspect(PHCSMP_Suspect model) {
		suspectDao.save(model);
	}

	@Override
	public PHCSMP_Suspect findByBandID(int bandId) {
		return suspectDao.findByBandId(bandId);
	}

	
	@Override
	public PHCSMP_Suspect findByRoomID(int roomId) {
		return suspectDao.findByRoomID(roomId);
	}

	@Override
	public List<PHCSMP_Dic_IdentifyCard_Type> findAllIdentifyCardType() {
		return suspectDao.findAllIdentifyCardType();
	}

	@Override
	public List<PHCSMP_Dic_Action_Cause> findAllSuspectCause() {
		return suspectDao.findAllSuspectCause();
	}

	// 根据Process_Now查的两个表
	@Override
	public List<PHCSMP_Suspect> getLeavePoliceSuspect() {
		return suspectDao.getLeavePoliceSuspect();
	}

	@Override
	public List<PHCSMP_Suspect> getOnPoliceSuspect() {
		return suspectDao.getOnPoliceSuspect();
	}

	@Override
	public PHCSMP_Suspect findBySuspetcId(String suspectId) {
		return suspectDao.findBySuspectID(suspectId);
		// return suspectDao.findByPropertyName("suspect_ID", suspectId);
	}

	@Override
	public void updateSwitch(int cardReader_Switch, String suspect_ID) {
		suspectDao.updateSwitch(cardReader_Switch, suspect_ID);
	}

	@Override
	public void updateLeaveState(int recordVideo_State, int process_Now,
			int cardReader_Switch, String suspetcId) {
		String hql = "update PHCSMP_Suspect s set s.recordVideo_State=? , s.process_Now=? , s.cardReader_Switch=? where s.suspect_ID=?";
		suspectDao.update(hql, recordVideo_State, process_Now,
				cardReader_Switch, suspetcId);

	}

	@Override
	public void updateSuspect(int room_Now, int process_Now,
			int recordVideo_State, String suspect_ID) {
		String hql = "update PHCSMP_Suspect s set s.room_Now=? , s.process_Now=? , s.recordVideo_State=? where s.suspect_ID=?";
		suspectDao.update(hql, room_Now, process_Now, recordVideo_State,
				suspect_ID);

	}

	@Override
	public void updateSuspect(int room_Now, int process_Now, String suspect_ID) {
		String hql = "update PHCSMP_Suspect s set s.room_Now=? , s.process_Now=? where s.suspect_ID=?";
		suspectDao.update(hql, room_Now, process_Now, suspect_ID);
	}

	@Override
	public void updateSuspect(PHCSMP_Suspect suspectInfor) {
		suspectDao.updateSuspect(suspectInfor);
	}

	@Override
	public String getMaxID() {
		return suspectDao.findByMaxID();
	}

	public List<PHCSMP_Suspect> serachInforBySuspectId(String searchInfor) {
		List<PHCSMP_Suspect> suspect = suspectDao.findBySuspectID1(searchInfor);
		return suspect;
	}

	@Override
	public List<PHCSMP_Suspect> findBySuspectName(String searchInfor) {
		return suspectDao.findBySuspectName(searchInfor);
	}

	@Override
	public List<PHCSMP_Suspect> findByCardId(String searchInfor) {
		List<PHCSMP_Suspect> suspect = suspectDao.findByCardId(searchInfor);
		return suspect;
	}

	@Override
	public List<PHCSMP_Suspect> findByCardIdNow(String searchInfor) {
		List<PHCSMP_Suspect> suspects = suspectDao.findByCardIdNow(searchInfor);
		return suspects;
	}

	@Override
	public List<PHCSMP_Suspect> serachInforBySuspectIdNow(String searchInfor) {
		List<PHCSMP_Suspect> suspectNow = suspectDao
				.findBySuspectIdNow(searchInfor);
		return suspectNow;
	}

	@Override
	public List<PHCSMP_Suspect> finBySuspectNameNow(String searchInfor) {
		List<PHCSMP_Suspect> suspectNowList = suspectDao
				.findBySuspectNameNow(searchInfor);
		return suspectNowList;
	}

	public void updateIs_RecordVideo_DownLoad(int is_RecordVideo_DownLoad,
			int bandID, String identifyCard_Number) {
		String hql = "update PHCSMP_Suspect s set s.is_RecordVideo_DownLoad=? where s.band_ID=? and s.identifyCard_Number=?";
		suspectDao.update(hql, is_RecordVideo_DownLoad,bandID,identifyCard_Number);
	}

	@Override
	public void updateSuspectPhotoPath(String fpath, String spath,
			String suspect_ID) {
		String hql = "update PHCSMP_Suspect s set s.frontal_Photo=? , s.sideWays_Photo=? where s.suspect_ID=?";
		suspectDao.update(hql, fpath, spath, suspect_ID);
	}

	@Override
	public List<PHCSMP_Suspect> findAllVideoDownloadFailSuspectInfor() {

		return suspectDao.findAllVideoDownloadFailSuspectInfor();
	}

	@Override
	public List<PHCSMP_Suspect> findAllByIsRecordVedio() {

		return suspectDao.findAllByIsRecordVedio();
	}

	@Override
	public void updateDetainTime(String hours, String suspectID) {
		String hql = "update PHCSMP_Suspect s set s.detain_Time=? where s.suspect_ID=?";
		suspectDao.update(hql, hours, suspectID);
	}
	
	/**
	 * 历史嫌疑人分页显示 pageSize为每页显示的记录数 page为当前页页码
	 */
	@Override
	public PageBean getPageBean(int pageSize, int page) {
		PageBean pageBean = new PageBean();

		String hql = "from PHCSMP_Suspect where process_Now=-1";

		int allRows = suspectDao.getAllRowCount(hql);

		int totalPage = pageBean.getTotalPages(pageSize, allRows);

		int currentPage = pageBean.getCurPage(page);

		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);

		List<PHCSMP_Suspect> list = suspectDao.queryByPage(hql, offset,
				pageSize);

		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);

		return pageBean;
	}

	@Override
	public void updateIs_filename_DownLoad(int i, int policeId,
			String identificationCard) {
		String hql = "update PHCSMP_Suspect s set s.is_RecordVideo_DownLoad=? where s.staff_ID=? and s.identifyCard_Number=?";
		suspectDao.update(hql, i,policeId,identificationCard);
		
	}
	
	// @Override
	// public PHCSMP_Suspect findByRemark(String remark) {
	// // TODO Auto-generated method stub
	// return suspectDao.findByPropertyName("remark", remark);
	// }

}
