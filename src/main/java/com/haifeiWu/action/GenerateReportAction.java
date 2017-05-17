package com.haifeiWu.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.entity.PHCSMP_BelongingS;
import com.haifeiWu.entity.PHCSMP_Information_Collection;
import com.haifeiWu.entity.PHCSMP_Leave_Record;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.entity.PHCSMP_Process_Log;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.entity.Temporary_Leave;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.BelongingInforService;
import com.haifeiWu.service.Dic_ProcessService;
import com.haifeiWu.service.InformationCollectionService;
import com.haifeiWu.service.KeepService;
import com.haifeiWu.service.LeaveRecodService;
import com.haifeiWu.service.LogService;
import com.haifeiWu.service.PersonalCheckService;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.StaffService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.service.TemporaryLeaveService;
import com.haifeiWu.service.UserService;
import com.haifeiWu.utils.PropertiesReadUtils;

/**
 * 生成嫌疑人信息报告的action
 * 
 * @author wuhaifei
 * @d2016年10月17日
 */
@Aspect
@Controller
@RequestMapping("/report")
@Scope("prototype")
public class GenerateReportAction {
	protected String detainTime;

	@Autowired
	private SuspectService suspectService;// 嫌疑人的入区登记信息
	@Autowired
	private BelongingInforService belongingInforService;// 嫌疑人随身物品登记信息
	@Autowired
	private PersonalCheckService personalCheckService; // 嫌疑人的人身检查信息
	@Autowired
	private ActivityRecordService activityRecordService;// 询问讯问记录信息登记
	@Autowired
	private InformationCollectionService informationCollectionService;// 信息采集信息登记
	@Autowired
	private LeaveRecodService leaveRecodService;// 嫌疑人出区信息登记
	@Autowired
	private TemporaryLeaveService temporaryLeaveService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private UserService userService;
	@Autowired
	private LogService logService; // 日志
	@Autowired
	private Dic_ProcessService processService;//流程名
	@Autowired
	private StaffService staffService;//办案人员名
	@Autowired
	KeepService keepService;
	/**
	 * 生成嫌疑人入区信息报告 (杜意权改，嫌疑人报告办离区活动记录模块)
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/load")
	public String GR_loadInfor(HttpServletRequest request) throws IOException {
		String suspectId = request.getParameter("suspectID");
		// try {
		// 查找嫌疑人日志
		//读取加载嫌疑人日志信息
		List<PHCSMP_Process_Log> suspectLog = getLogBysuspectId(suspectId);
		List<String> processNameList = new ArrayList<String>();
		List<String> keepNameList = new ArrayList<String>();
		List<String> staffNameList = new ArrayList<String>(); 
		request.setAttribute("suspectLog", suspectLog);
		for(PHCSMP_Process_Log suspect : suspectLog){
			int process = suspect.getProcess_ID();
			int staffid = suspect.getStaff_ID();
			if(staffService.getStaffName(staffid)!=null){
			String staffName = staffService.getStaffName(staffid);
			
			staffNameList.add(staffName);
			
			}else{
				staffNameList.add(" ");
			}
	System.out.println(process+"-----------------------------process");
			if(processService.getProcessName(process)!=null){
			String processName =processService.getProcessName(process);
			processNameList.add(processName);
			}
			
		}
		request.setAttribute("processNameList", processNameList);
		request.setAttribute("staffNameList", staffNameList);
		// 查找嫌疑人入区信息
		PHCSMP_Suspect suspect = suspectService.findBySuspetcId(suspectId);


		int suspect_complete_degree = (int) (suspect.getFill_record()
				/ (float) suspect.getTotal_record() * 100);
		System.out.println("suspect_complete_degree" + suspect_complete_degree
				+ "------------------------------------");
		request.setAttribute("suspect_complete_degree", suspect_complete_degree);
		// 嫌疑人随身所有物品检查信息s

		List<PHCSMP_BelongingS> belongingS = belongingInforService
				.selectBelongInfor(suspectId);
		if(belongingS!=null && belongingS.size()!=0){
		int belongstaffid = belongingS.get(0).getStaff_ID_Belonging();
		int staffid = Integer.parseInt(belongingS.get(0).getStaff_ID());
		if(staffService.getStaffName(belongstaffid)!=null){
			String belongstaffname = staffService.getStaffName(belongstaffid);
			String  staffname = staffService.getStaffName(staffid);
			request.setAttribute("belongstaffname", belongstaffname);
			request.setAttribute("staffname", staffname);
		}
//		}
//		if(belongingS!=null){
			for(PHCSMP_BelongingS belong: belongingS){
				int keepid = Integer.parseInt(belong.getKeeping_ID());
			String keepname = keepService.getKeepname(keepid);
			keepNameList.add(keepname);
			}
			request.setAttribute("keepNameList", keepNameList);
		}

		// 查询办案人名
		PHCSMP_Personal_Check personcheck = personalCheckService
				.findInforBySuspetcId(suspectId);
		if (personcheck != null) {
			PHCSMP_Staff staff = userService.finstaffById(Integer
					.parseInt(personcheck.getStaff_ID()));
			String staffname = staff.getStaff_Name();
			request.setAttribute("staffname", staffname);
		}
		System.out
				.println(belongingS.toString()
						+ "---------------------------------------------------------------");

		// 嫌疑人人身检查信息
		PHCSMP_Personal_Check personal_Check = personalCheckService
				.findInforBySuspetcId(suspectId);

		if (personal_Check != null) {
			int personal_Check_complete_degree = (int) (personal_Check
					.getFill_record()
					/ (float) personal_Check.getTotal_record() * 100);
			System.out.println("personal_Check_complete_degree"
					+ personal_Check_complete_degree
					+ "------------------------------------");
			request.setAttribute("personal_Check_complete_degree",
					personal_Check_complete_degree);
		}
		// 嫌疑人所有的办案区记录信息
		List<PHCSMP_Activity_Record> activity_Record = activityRecordService
				.selectActivityRecordInfor(suspectId);
		List<String> room_Name = new ArrayList<String>();
		for (PHCSMP_Activity_Record phcsmp_Activity_Record : activity_Record) {

			room_Name.add(roomService.findByRoomID(
					phcsmp_Activity_Record.getRoom_ID()).getRoom_Name());
		}
		request.setAttribute("room_Name", room_Name);

		// 嫌疑人信息采集记录
		PHCSMP_Information_Collection information_Collection = informationCollectionService
				.findInforBySuspetcId(suspectId);

		if (information_Collection != null) {
			int information_Collection_complete_degree = (int) (information_Collection
					.getFill_record()
					/ (float) information_Collection.getTotal_record() * 100);
			System.out.println("information_Collection_complete_degree"
					+ information_Collection_complete_degree
					+ "------------------------------------");
			request.setAttribute("information_Collection_complete_degree",
					information_Collection_complete_degree);
		}

		// 嫌疑人离区信息记录
		PHCSMP_Leave_Record leave_Record = leaveRecodService
				.findInforBySuspetcId(suspectId);
		// 暂时出区
		List<Temporary_Leave> temporaryLeaves = temporaryLeaveService
				.findTempLeaveListBySuspectID(suspectId);
		String reportCreateTime = new DateTime().toString("yyyy/MM/dd");
		// 将查找到的信息放入request中，然后从页面加载
		request.setAttribute("suspectId", suspect.getSuspect_ID());
		request.setAttribute("suspect", suspect);
		request.setAttribute("belongingS", belongingS);
		request.setAttribute("personal_Check", personal_Check);
		request.setAttribute("activity_Record", activity_Record);
		request.setAttribute("information_Collection", information_Collection);
		request.setAttribute("leave_Record", leave_Record);
		request.setAttribute("temporaryLeaves", temporaryLeaves);
		// request.setAttribute("prisonHour", prisonHour);
		request.setAttribute("reportCreateTime", reportCreateTime);
		request.setAttribute("pdfFilePath",
				PropertiesReadUtils.getPDFString("relatePath") + "\\"
						+ suspectId + ".pdf");
		return "WEB-INF/jsp/recordInfor/report";
		// } catch (Exception e) {

		// response.getWriter()
		// .write("<script type='text/javascript'>alert('页面加载失败，可能是pdf配置失败');</script>");
		// response.getWriter().flush();
		// request.setAttribute("error", "error");

		// return "redirect:/home/index";
	}

	// }

	/**
	 * 按suspectId查询嫌疑人日志
	 * 
	 * @param suspectId
	 * @return
	 */
	private List<PHCSMP_Process_Log> getLogBysuspectId(String suspectId) {
		return logService.findlogBysuspect(suspectId);

	}

	public String getDetainTime() {
		return detainTime;
	}

	public void setDetainTime(String detainTime) {
		this.detainTime = detainTime;
	}
}
