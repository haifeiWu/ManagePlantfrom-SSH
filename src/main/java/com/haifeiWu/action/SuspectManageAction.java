package com.haifeiWu.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.LeaveRecodService;
import com.haifeiWu.service.SuspectService;

/**
 * 嫌疑人信息管理action，待查嫌疑人信息，历史嫌疑人信息/demoone
 * 
 * @author wuhaifei
 * @d2016年10月17日
 */
@Controller
@RequestMapping("/suspectmanage")
@Scope("prototype")
public class SuspectManageAction {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;

	@Autowired
	private SuspectService suspectService;// 嫌疑人信息管理
	@Autowired
	private LeaveRecodService leaveRecodService;

	/**
	 * 加载嫌疑人信息
	 * 
	 * @return
	 */
	public String loadInfor() {
		System.out.println("历史记录，待办信息");
		// 获取待查嫌疑人信息
		List<PHCSMP_Suspect> suspectCheckInfor = suspectService
				.getOnPoliceSuspect();
		// 获取出区嫌疑人数据
		List<PHCSMP_Suspect> suspectCheckedInfor = suspectService
				.getLeavePoliceSuspect();
		// List<PHCSMP_Leave_Record>
		if ((suspectCheckInfor == null) || (suspectCheckedInfor == null)) {
			return "NULL";
		}
		// 将信息放入到request中
		request.setAttribute("suspectCheckInfor", suspectCheckInfor);
		request.setAttribute("suspectCheckedInfor", suspectCheckedInfor);
		System.out.println("----------待查的" + suspectCheckInfor);
		System.out.println("----------历史的----" + suspectCheckedInfor);

		for (PHCSMP_Suspect phcsmp_Suspect : suspectCheckedInfor) {
			System.err.println(phcsmp_Suspect.toString());
		}

		// getDistanceTime(suspectCheckedInfor., suspectCheckedInfor.get(14));
		return "loadInfor";
	}

	/**
	 * 根据姓名或者档案编号查找嫌疑人信息
	 * 
	 * @return
	 */
	public String searchsuspectInfor() {
		String searchInfor = request.getParameter("searchInfor");
		/*
		 * 通过正则表达式来区分嫌疑人姓名
		 */
		Pattern p = Pattern.compile("^[\u4E00-\u9FA5]+$");
		Matcher m = p.matcher(searchInfor);
		boolean result = m.find();
		char fir = searchInfor.charAt(0);
		if (fir < 130) {
			if (fir < 60) {
				// 非数组类型的身份证号查找
				List<PHCSMP_Suspect> suspect = suspectService
						.findByCardId(searchInfor);
				List<PHCSMP_Suspect> suspectNow = suspectService
						.findByCardIdNow(searchInfor);
				System.out.println("身份证号：" + searchInfor);
				request.setAttribute("suspect", suspect);
				request.setAttribute("suspectNow", suspectNow);
				System.out.println(suspect);
			} else {
				// 根据档案编号查询嫌疑人信息
				List<PHCSMP_Suspect> suspect = suspectService
						.serachInforBySuspectId(searchInfor);
				List<PHCSMP_Suspect> suspectNow = suspectService
						.serachInforBySuspectIdNow(searchInfor);
				System.out.println("档案编号：" + searchInfor);
				request.setAttribute("suspect", suspect);
				request.setAttribute("suspectNow", suspectNow);
				System.out.println(suspect);
				System.out.println(suspectNow);
			}
		}
		if (result) {
			List<PHCSMP_Suspect> suspect = suspectService
					.findBySuspectName(searchInfor);
			List<PHCSMP_Suspect> suspectNow = suspectService
					.finBySuspectNameNow(searchInfor);
			request.setAttribute("suspect", suspect);
			request.setAttribute("suspectNow", suspectNow);
			System.out.println("嫌疑人姓名：" + searchInfor);
			System.out.println(suspect);
			System.out.println(suspectNow);
		}
		return "searchsuspectInfor";
	}

	/***
	 * 录像下载失败的嫌疑人信息
	 * 
	 * @return
	 */

	public String videoDownFailList() {
		List<String> leaveTimeList = new ArrayList<String>();
		try {
			List<PHCSMP_Suspect> suspectList = suspectService
					.findAllVideoDownloadFailSuspectInfor();
			for (int i = 0; i < suspectList.size(); i++) {

				String suspectId = suspectList.get(i).getSuspect_ID();
				System.out.println("suspectId" + suspectId);
				String leavaTime = leaveRecodService.findLeaveRecordInfor(
						suspectId).getLeave_Time();
				System.out.println("leavetime=" + leavaTime);
				leaveTimeList.add(leavaTime);
				System.out.println("leavetime=" + leaveTimeList.get(i));
			}
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			for (int i = 0; i < suspectList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("suspect_Name", suspectList.get(i).getSuspect_Name());
				map.put("suspect_ID", suspectList.get(i).getSuspect_ID());
				map.put("enter_Time", suspectList.get(i).getEnter_Time());
				map.put("identifyCard_Number", suspectList.get(i)
						.getIdentifyCard_Number());
				map.put("leave_Time", leaveTimeList.get(i));
				list.add(map);

			}
			request.setAttribute("suspect", list);
			return "videoDownFail";
		} catch (Exception e) {
			e.printStackTrace();
			return "videoDownFail";
		}

	}

	public String videoDownSuccessList() {
		List<String> leaveTimeList = new ArrayList<String>();
		try {
			List<PHCSMP_Suspect> suspectList = suspectService
					.findAllByIsRecordVedio();
			for (int i = 0; i < suspectList.size(); i++) {

				String suspectId = suspectList.get(i).getSuspect_ID();
				System.out.println("suspectId" + suspectId);
				String leavaTime = leaveRecodService.findLeaveRecordInfor(
						suspectId).getLeave_Time();
				System.out.println("leavetime=" + leavaTime);
				leaveTimeList.add(leavaTime);
				System.out.println("leavetime=" + leaveTimeList.get(i));
			}
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			for (int i = 0; i < suspectList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("suspect_Name", suspectList.get(i).getSuspect_Name());
				map.put("suspect_ID", suspectList.get(i).getSuspect_ID());
				map.put("enter_Time", suspectList.get(i).getEnter_Time());
				map.put("identifyCard_Number", suspectList.get(i)
						.getIdentifyCard_Number());
				map.put("leave_Time", leaveTimeList.get(i));
				map.put("vedio_number", suspectList.get(i).getVedio_Number());
				list.add(map);

			}
			request.setAttribute("suspect", list);
			return "videoDownSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			return "videoDownSuccess";
		}

	}

	// @Override
	// public void setServletContext(ServletContext application) {
	// this.application = application;
	// }
	//
	// @Override
	// public void setServletResponse(HttpServletResponse response) {
	// this.response = response;
	// }
	//
	// @Override
	// public void setServletRequest(HttpServletRequest request) {
	// this.request = request;
	// }

}
