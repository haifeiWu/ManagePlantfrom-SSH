package com.haifeiWu.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.entity.PHCSMP_Information_Collection;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.entity.PHCSMP_Process_Log;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.Dic_ProcessService;
import com.haifeiWu.service.InformationCollectionService;
import com.haifeiWu.service.LeaveRecodService;
import com.haifeiWu.service.LogService;
import com.haifeiWu.service.PersonalCheckService;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.StaffService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.service.UserService;
import com.haifeiWu.utils.CompleteCheck;

/**
 * 活动记录
 * 
 * @author wuhaifei
 * @d2016年9月28日
 */
@Controller
@RequestMapping("/activity")
@Scope("prototype")
public class Activity_Record_Action {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1201107017949225716L;
	@Autowired
	private RoomService roomService;
	@Autowired
	private UserService userService;
	// 活动记录实例类
	@Autowired
	private ActivityRecordService activityRecordService;
	// 嫌疑人信息
	@Autowired
	private SuspectService suspectService;
	// 嫌疑人的人身检查信息
	@Autowired
	private PersonalCheckService personalCheckService;
	@Autowired
	private LeaveRecodService leaveRecodService;
	// 信息采集信息登记
	@Autowired
	private InformationCollectionService informationCollectionService;
	@Autowired
	private LogService logService; // 日志
	@Autowired
	private Dic_ProcessService processService;//流程名
	@Autowired
	private StaffService staffService;//办案人员名
	
	
	
	// 活动记录表list，用于前台提交的多个数据

	/**
	 * 添加活动记录信息
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addActivityRecordInfor(
			@RequestParam("suspect_ID") String suspectId,
			PHCSMP_Activity_Record activity, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			request.setAttribute("suspectId", suspectId);
			// 加载当前房间的嫌疑人
			int roomId = roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID();
			Integer staff_ID = Integer.parseInt(request
					.getParameter("staff_ID"));
			// PHCSMP_Activity_Record activity = new PHCSMP_Activity_Record();
			// 获取前台数据
			// String start_Time = request.getParameter("start_Time");
			// String activity_Record = request.getParameter("activity_Record");
			// String activity_remark = request.getParameter("remark");
			// activity.setStart_Time(start_Time);
			// activity.setRemark(activity_remark);
			// activity.setActivity_Record(activity_Record);

			// 设置询问讯问结束的时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String End_Time = sdf.format(date);
			// activity.setSuspect_ID(suspectId);
			activity.setRoom_ID(roomId);
			activity.setEnd_Time(End_Time);
			activity.setStaff_ID(staff_ID);
			request.setAttribute("staff_ID", staff_ID);
			fullCheck(activity);
			//将询问记录交给日志
			request.setAttribute("remark", activity.getRemark());
			// 保存
			activityRecordService.saveActivityRecordInfor(activity);
			// 提示成功
			return "redirect:/home/index";
		} catch (Exception e) {
			// 提示失败
			// response.getWriter()
			// .write("<script type='text/javascript'>alert('提交失败，请重新提交');</script>");
			// response.getWriter().flush();
			// 将信息传递到loadInfor action,显示在页面上
			request.setAttribute("error", "error");
			String activity_Record = request.getParameter("activity_Record");
			String activity_remark = request.getParameter("remark");
			request.setAttribute("activity_Record", activity_Record);
			request.setAttribute("activity_remark", activity_remark);
			return "redirect:/load";
		}
	}

	private void fullCheck(PHCSMP_Activity_Record activity)
			throws ClassNotFoundException {
		Class<?> c = Class.forName(PHCSMP_Activity_Record.class.getName());
		// 统计未填写的字段
		int count = CompleteCheck.IsEqualsNull(activity, c);
		// 统计所有的字段
		int fieldsNumber = CompleteCheck.getFieldsNumber(activity, c);

		activity.setFill_record(fieldsNumber - count - 2 - 1);// 设置已填写的字段数
		activity.setTotal_record(fieldsNumber - 3);// 设置应填写的字段
	}

	/**
	 * 加载活动记录信息
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public String AR_loadInfor(HttpServletRequest request,
			@RequestParam("suspectID") String suspectId) throws IOException {
		// suspectId = "LB-HB-201703115";
//		try {
			// 提交失败时将信息再次显示
			if (request.getAttribute("activity_remark") != null) {
				String activity_remark = (String) request
						.getAttribute("activity_remark");
				String activity_Record = (String) request
						.getAttribute("activity_Record");
				request.setAttribute("activity_remark", activity_remark);
				request.setAttribute("activity_Record", activity_Record);
			}
			System.out.println("进入活动记录");
			// 维护进出门的标志位
			int roomId = roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID();
			PHCSMP_Suspect suspectInfor = suspectService
					.findBySuspetcId(suspectId);
			List<PHCSMP_Staff> staff = userService.findAllStaffs();
			request.setAttribute("staff", staff);
			int complete_degree = (int) (suspectInfor.getFill_record()
					/ (float) suspectInfor.getTotal_record() * 100);
			request.setAttribute("complete_degree", complete_degree);
			request.setAttribute("SuspectInfor", suspectInfor);
			//读取加载嫌疑人日志信息
			List<PHCSMP_Process_Log> suspectLog = getLogBysuspectId(suspectId);
			List<String> processNameList = new ArrayList<String>();
			List<String> staffNameList = new ArrayList<String>(); 
			request.setAttribute("suspectLog", suspectLog);
			for(PHCSMP_Process_Log suspect : suspectLog){
				int process = suspect.getProcess_ID();
				int staffid = suspect.getStaff_ID();
				if(staffService.getStaffName(staffid)!=null){
				String staffName = staffService.getStaffName(staffid);
				staffNameList.add(staffName);
				}
				String processName =processService.getProcessName(process);
				processNameList.add(processName);
				
			}
			request.setAttribute("processNameList", processNameList);
			request.setAttribute("staffNameList", staffNameList);
			
			// 人身安全检查
//			PHCSMP_Personal_Check personal_Check = personalCheckService
//					.findInforBySuspetcId(suspectId);
//			if (personal_Check != null) {
//				int complete_degree1 = (int) (personal_Check.getFill_record()
//						/ (float) personal_Check.getTotal_record() * 100);
//				String checkRoomName = roomService.findByRoomID(
//						personal_Check.getRoom_ID()).getRoom_Name();
//				request.setAttribute("checkRoomName", checkRoomName);
//				request.setAttribute("complete_degree1", complete_degree1);
//				request.setAttribute("personal_Check", personal_Check);
//			} else {
//				request.setAttribute("complete_degree1", "0");
//			}
//			// 信息采集
//			PHCSMP_Information_Collection information_Collection = informationCollectionService
//					.findInforBySuspetcId(suspectId);
//
//			if (information_Collection != null) {
//				int complete_degree2 = (int) (information_Collection
//						.getFill_record()
//						/ (float) information_Collection.getTotal_record() * 100);
//				String ICRoomName = roomService.findByRoomID(
//						information_Collection.getRoom_ID()).getRoom_Name();
//				request.setAttribute("ICRoomName", ICRoomName);
//				request.setAttribute("complete_degree2", complete_degree2);
//				request.setAttribute("information_Collection",
//						information_Collection);
//			} else {
//				request.setAttribute("complete_degree2", "0");
//			}
//			// 询问讯问活动记录
//			List<PHCSMP_Activity_Record> activity_record_infor = activityRecordService
//					.findInforBySuspetcId(suspectId);
//
//			if (activity_record_infor != null) {
//
//				List<String> roomList = new ArrayList<String>();
//				List<Map<String, Object>> activity_record = new ArrayList<Map<String, Object>>();
//				for (PHCSMP_Activity_Record activity : activity_record_infor) {
//					String roomName = roomService.findByRoomID(
//							activity.getRoom_ID()).getRoom_Name();
//
//					roomList.add(roomName);
//				}
//				for (int i = 0; i < activity_record_infor.size(); i++) {
//					Map<String, Object> map = new HashMap<String, Object>();
//					map.put("activity_Record", activity_record_infor.get(i)
//							.getActivity_Record());
//					map.put("room_Name", roomList.get(i));
//					map.put("start_Time", activity_record_infor.get(i)
//							.getStart_Time());
//					map.put("end_Time", activity_record_infor.get(i)
//							.getEnd_Time());
//
//					map.put("remark", activity_record_infor.get(i).getRemark());
//					activity_record.add(map);
//				}
//				request.setAttribute("activity_record", activity_record);
//			}

			// 设置询问询问开始的时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String start_Time = sdf.format(date);
			request.setAttribute("start_Time", start_Time);

			// 维护进出门状态
			suspectService.updateSwitch(1, suspectId);
			return "WEB-INF/jsp/recordInfor/activity";
//		} catch (Exception e) {
//			// response.getWriter()
//			// .write("<script type='text/javascript'>alert('当前房间存在多个嫌疑人，可能是上一个嫌疑人出门时未刷卡（请保证进门和出门时成对刷卡），也可能是房间信息不正确');</script>");
//			// response.getWriter().flush();
//			request.setAttribute("error", "error");
//			return "redirect:/load";
//		}
	}

	
	/**
	 * 按suspectId查询嫌疑人日志
	 * 
	 * @param suspectId
	 * @return
	 */
	private List<PHCSMP_Process_Log> getLogBysuspectId(String suspectId) {
		return logService.findlogBysuspect(suspectId);

	}
}
