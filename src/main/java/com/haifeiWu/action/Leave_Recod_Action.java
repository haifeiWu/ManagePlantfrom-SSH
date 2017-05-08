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

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.entity.PHCSMP_Dic_Keeping_Way;
import com.haifeiWu.entity.PHCSMP_Dic_Leaving_Reason;
import com.haifeiWu.entity.PHCSMP_Dic_Treatment_Method;
import com.haifeiWu.entity.PHCSMP_Information_Collection;
import com.haifeiWu.entity.PHCSMP_Leave_Record;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.entity.PHCSMP_Room;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.entity.Temporary_Leave;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.BandService;
import com.haifeiWu.service.DicService;
import com.haifeiWu.service.InformationCollectionService;
import com.haifeiWu.service.LeaveRecodService;
import com.haifeiWu.service.LineService;
import com.haifeiWu.service.PersonalCheckService;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.service.TemporaryLeaveService;
import com.haifeiWu.service.UserService;
import com.haifeiWu.utils.CompleteCheck;
import com.haifeiWu.utils.HtmlToPdf;
import com.haifeiWu.utils.Video;

/**
 * 离开办案区的action
 * 
 * @author wuhaifei
 * @d2016年8月17日
 */

@Controller
@RequestMapping("/leave")
@Scope("prototype")
public class Leave_Recod_Action {
	/**
	 * 序列化的字段
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	@Autowired
	private LeaveRecodService leaveRecodService;
	// 入区登记
	@Autowired
	private SuspectService suspectService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private TemporaryLeaveService temporaryLeaveService;
	@Autowired
	private LineService lineService;
	@Autowired
	private BandService bandService;
	@Autowired
	private DicService dicService;
	// 注入前四个业务的service,查询嫌疑人前四个业务的信息以进行完整性检查
	// 人身检查
	@Autowired
	private PersonalCheckService personalCheckService;
	// 信息采集
	@Autowired
	private InformationCollectionService informationCollectionService;
	// 询问讯问
	@Autowired
	private ActivityRecordService activityRecordService;

	private Temporary_Leave temporaryLeave;
	private PHCSMP_Suspect suspectInfor;

	private String tempLeave_Time;
	private String tempLeave_Reason;
	private String return_Time;
	private String staff_ID;
	private String manager;
	private String personName;
	private String suspectID;

	// private PHCSMP_Suspect suspect;
	private PHCSMP_Personal_Check personalCheck;
	private PHCSMP_Information_Collection informationCollection;
	private List<PHCSMP_Activity_Record> activityRecordList;

	private int suspectComplete;
	private int personalCheckComplete;
	private int informationCollectionComplete;
	private Map<Integer, Integer> completeMap;
	private StringBuilder sb;

	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addLeaveRecordInfor(PHCSMP_Leave_Record model,
			HttpServletRequest request, HttpServletResponse response
	// ,@RequestParam("suspectID") String
	// suspectID,@RequestParam("suspectInfor")PHCSMP_Suspect suspectInfor
	) throws Exception {
		// try {
		String suspectID = request.getParameter("suspectID");
		PHCSMP_Suspect suspectInfor = (PHCSMP_Suspect) request
				.getAttribute("suspectInfor");
		PHCSMP_Room room = roomService.findbyIp(request.getRemoteAddr());
		// 设置最终离开时间和 领取时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String leavetime = sdf.format(date);
		model.setLeave_Time(leavetime);
		model.setTreatment_Time(leavetime);
		// 获取staffID
		String staff_ID = request.getParameter("staff_ID");
		model.setStaff_ID(staff_ID);
		request.setAttribute("staff_ID", staff_ID);
		// // 设置离区 嫌疑人的ID

		model.setSuspect_ID(suspectID);
		suspectInfor = suspectService.findBySuspetcId(suspectID);
		// 动态设置离区嫌疑人的字段信息
		Class<?> c = Class.forName(PHCSMP_Leave_Record.class.getName());
		int count1 = CompleteCheck.IsEqualsNull(model, c);
		int fieldsNumber1 = CompleteCheck.getFieldsNumber(model, c);
		model.setFill_record(fieldsNumber1 - count1 - 4);// 设置已填写的字段数
		model.setTotal_record(fieldsNumber1 - 4);// 设置应填写的字段

		// 设置羁押时间
		DateTimeFormatter format = DateTimeFormat
				.forPattern("yyyy-MM-dd HH:mm");
		DateTime enter = DateTime.parse(suspectInfor.getEnter_Time(), format);

		DateTime leave = DateTime.parse(leavetime, format);
		int hours = Hours.hoursBetween(enter, leave).getHours();
		// 设置却没有保存到数据库
		suspectService.updateDetainTime(hours + "小时", suspectID);
		// 保证不插入重复数据
		PHCSMP_Leave_Record LeaveRecordInfor = leaveRecodService
				.findLeaveRecordInfor(suspectID);
		if (LeaveRecordInfor == null) {
			leaveRecodService.saveLeaveRecordInfor(model);// 保存嫌疑人离开信息，
		} else {
			leaveRecodService.updateLeaveRecordInfor(model);// 更新嫌疑人离开信息
		}
		// 停止录像// 将录像的标志位置为0
		if (suspectInfor.getRecordVideo_State() != 0) {
			String stopRecording = Video.stopRecording(
					suspectInfor.getBand_ID(), room.getLine_Number(),
					suspectInfor.getIdentifyCard_Number());
			suspectService.updateLeaveState(3, -1, 0,
					suspectInfor.getSuspect_ID());
		} else {
			suspectService.updateLeaveState(0, -1, 0,
					suspectInfor.getSuspect_ID());
		}

		// 释放回路
		if (suspectInfor.getRecordVideo_State() != 0)
			lineService.closeLine();
		// 释放手环
		bandService.update(0, suspectInfor.getBand_ID());
		// 下载PDF
		HtmlToPdf.createPdf(suspectID);
		// 请求上传录像文件

		if (suspectInfor.getRecordVideo_State() != 0) {
			Video.setRBServerCfg();// 远程服务器已配置
			Video.setFtpServerCfg(suspectInfor.getBand_ID(),
					suspectInfor.getIdentifyCard_Number());// ftp服务器已配置
			Video.uploadRecFile(suspectInfor.getBand_ID(),
					suspectInfor.getIdentifyCard_Number());
		}
		return "redirect:/home/index";
		// } catch (Exception e) {
		// // response.getWriter()
		// //
		// .write("<script type='text/javascript'> alert('提交失败，请重新提交'); </script>");
		// // response.getWriter().flush();
		// //
		// // request.setAttribute("leaveRecordLoadInfor", model);
		//
		// return "redirect:/load";
		// }
	}

	// 保存临时出区的信息
	@RequestMapping(value = "/addtemp", method = RequestMethod.POST)
	public String addTemporaryLeaveInfor(Temporary_Leave model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			// 根据ip找到房间
			PHCSMP_Room room = roomService.findbyIp(request.getRemoteAddr());
			// 获取前台表单数据，并封装成对象.
			Temporary_Leave temporary_Leave = new Temporary_Leave(suspectID,
					tempLeave_Time, tempLeave_Reason, return_Time,
					model.getStaff_ID(), room.getRoom_ID(), manager);
			// 如果是出区保存信息,是出区返回则更新信息
			temporaryLeave = temporaryLeaveService
					.IsTemporaryLeaveReturn(suspectID);
			// 临时离开返回
			if (temporaryLeave != null) {
				// 更新临时离开返回时间
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String temporaryReturnTime = sdf.format(date);

				temporaryLeaveService.updateReturnTime(temporaryReturnTime,
						suspectID);
				// 如果出区返回时值班室管理员变了，则将新的管理员也保存进数据库
				if (temporaryLeave.getManager() != manager) {
					temporaryLeaveService.updateManager(
							temporaryLeave.getManager() + "," + manager,
							suspectID);
				}
			} else {// 临时离开
				// 设置临时离开时间
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String temporaryLeaveTime = sdf.format(date);

				temporary_Leave.setTempLeave_Time(temporaryLeaveTime);
				temporaryLeaveService.saveTemporaryLeaveInfo(temporary_Leave);
			}

			return "redirect:/home/index";
		} catch (Exception e) {
			// response.getWriter()
			// .write("<script type='text/javascript'>alert('提交失败，请重新提交');</script>");
			// response.getWriter().flush();
			request.setAttribute("error", "error");
			return "redirect:/load";
		}
	}

	/* 加载界面信息 */
	@RequestMapping(value = "/load")
	public String loadInfor(@RequestParam("suspectID") String suspectID,
			HttpServletRequest request
	// @RequestParam("sb") StringBuilder
	// sb,@RequestParam("suspectInfor")PHCSMP_Suspect suspectInfor,
	// @RequestParam("suspectComplete")int
	// suspectComplete,@RequestParam("personalCheck")PHCSMP_Personal_Check
	// personalCheck,
	// @RequestParam("personalCheckComplete")int
	// personalCheckComplete,@RequestParam("informationCollection")PHCSMP_Information_Collection
	// informationCollection,
	// @RequestParam("informationCollectionComplete")int
	// informationCollectionComplete,@RequestParam("activityRecordList")List<PHCSMP_Activity_Record>
	// activityRecordList,
	// @RequestParam("completeMap")Map<Integer, Integer>
	// completeMap,@RequestParam("temporaryLeave")Temporary_Leave temporaryLeave
	) throws IOException {
		try {
			// 异常处理的代码
			if (request.getAttribute("leaveRecordLoadInfor") != null) {
				PHCSMP_Leave_Record lr = (PHCSMP_Leave_Record) request
						.getAttribute("leaveRecordLoadInfor");
				request.setAttribute("PHCSMP_Leave_Record", lr);
				// 这是异常处理
				String tempLeave_Reason = (String) request
						.getAttribute("tempLeave_Reason");
				String staff_ID = (String) request.getAttribute("staff_ID");
				String manager_name = (String) request
						.getAttribute("manager_name");

				request.setAttribute("tempLeave_Reason", tempLeave_Reason);
				request.setAttribute("staff_ID", staff_ID);
				request.setAttribute("manager_name", manager_name);
			}
			// 加载嫌疑人信息
			PHCSMP_Room room = roomService.findbyIp(request.getRemoteAddr());
			String suspectId = (String) request.getParameter("suspectID");

			// 离区前提示前四个业务的完整性
			// 根据嫌疑人id查找嫌疑人前四个业务的信息
			sb = new StringBuilder("");
			// 查入区登记信息
			suspectInfor = suspectService.findBySuspetcId(suspectId);
			// 并不需要再次进行完整性检查，只需读取数据，除一下即可
			suspectComplete = (int) (suspectInfor.getFill_record()
					/ (float) suspectInfor.getTotal_record() * 100);
			if (suspectComplete != 100) {// 信息不完整
				sb.append("入区登记信息填写不完整!  ");
			}

			request.setAttribute("suspectInfor", suspectInfor);
			request.setAttribute("suspectComplete", suspectComplete);
			// 查人身检查信息
			personalCheck = personalCheckService
					.findInforBySuspetcId(suspectId);
			if (personalCheck != null) {
				personalCheckComplete = (int) (personalCheck.getFill_record()
						/ (float) personalCheck.getTotal_record() * 100);
				if (personalCheckComplete != 100) {// 信息不完整
					sb.append("人身检查信息填写不完整!  ");
				}
			} else {
				sb.append("该嫌疑人并未进行人身检查操作! ");
			}
			request.setAttribute("personalCheckComplete", personalCheckComplete);
			// 查信息采集信息
			informationCollection = informationCollectionService
					.findInforBySuspetcId(suspectId);
			if (informationCollection != null) {
				informationCollectionComplete = (int) (informationCollection
						.getFill_record()
						/ (float) informationCollection.getTotal_record() * 100);
				if (informationCollectionComplete != 100) {// 信息不完整
					sb.append("信息采集信息填写不完整!  ");
					System.out.println(sb + "3");
				}
			} else {
				sb.append("该嫌疑人并未进行信息采集操作!  ");
			}
			request.setAttribute("informationCollectionComplete",
					informationCollectionComplete);
			// 查询问讯问信息
			activityRecordList = activityRecordService
					.findInforBySuspetcId(suspectId);
			completeMap = new HashMap<Integer, Integer>();
			if (activityRecordList != null) {
				List activityRecordCompleteList = new ArrayList<>();
				int j = 0;
				int i = 1;
				for (PHCSMP_Activity_Record Activity_Record : activityRecordList) {
					int activityRecordComplete = (int) (Activity_Record
							.getFill_record()
							/ (float) Activity_Record.getTotal_record() * 100);
					completeMap.put(j, activityRecordComplete);
					activityRecordCompleteList.add(activityRecordComplete);
					request.setAttribute("activityRecordCompleteList", activityRecordCompleteList);
					j++;
					if (activityRecordComplete != 100) {// 信息不完整
						sb.append("询问讯问" + i + "信息填写不完整!  ");
						i++;
					}
				}
				for (Map.Entry<Integer, Integer> entry : completeMap.entrySet()) {
					System.out.println("Key = " + entry.getKey() + ", Value = "
							+ entry.getValue());
				}
			} else {
				sb.append("该嫌疑人并未进行询问讯问操作!  ");
			}
			request.setAttribute("activityRecordList", activityRecordList);
			// 判断是否出区返回
			temporaryLeave = temporaryLeaveService
					.IsTemporaryLeaveReturn(suspectId);
			// 向前台放置一些dic表信息
			List<PHCSMP_Dic_Leaving_Reason> leaveReason = dicService
					.findLeaveReason();
			List<PHCSMP_Dic_Keeping_Way> keepingWay = dicService
					.findKeepingWay();
			List<PHCSMP_Dic_Treatment_Method> treatmentMethod = dicService
					.findTreatmentMethod();
			request.setAttribute("leaveReason", leaveReason);
			request.setAttribute("keepingWay", keepingWay);
			request.setAttribute("treatmentMethod", treatmentMethod);
			// 维护进出门的标志位
			suspectService.updateSwitch(1, suspectId);

			// 判断进度条
			if (personalCheck != null) {
				request.setAttribute("personalCheck", personalCheck);
			}
			if (informationCollection != null) {
				request.setAttribute("informationCollection",
						informationCollection);
			}
			if (activityRecordList.size() != 0) {
				request.setAttribute("activityRecord", activityRecordList);
			}
			request.setAttribute("sb", sb);
			request.setAttribute("suspectInfor", suspectInfor);
			List<PHCSMP_Staff> staff = userService.findAllStaffs();
			request.setAttribute("staff", staff);
			System.out.println("+++++++++++++++====" + staff.get(1));
			return "WEB-INF/jsp/recordInfor/leave";
		} catch (Exception e) {
			// 异常处理
			// response.getWriter()
			// .write("<script type='text/javascript'>alert('加载失败，可能是房间或读卡设备配置错误，修改配置后刷新页面');</script>");
			// response.getWriter().flush();
			// 转到
			return "redirect:/home/index";
		}
	}

	// 未登录状态时
	public String unlogin_load() {
		return "unlogin_load";
	}

	// -------------------------------------------------------
	public LeaveRecodService getLeaveRecodService() {
		return leaveRecodService;
	}

	public void setLeaveRecodService(LeaveRecodService leaveRecodService) {
		this.leaveRecodService = leaveRecodService;
	}

	public SuspectService getSuspectService() {
		return suspectService;
	}

	public void setSuspectService(SuspectService suspectService) {
		this.suspectService = suspectService;
	}

	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	public TemporaryLeaveService getTemporaryLeaveService() {
		return temporaryLeaveService;
	}

	public void setTemporaryLeaveService(
			TemporaryLeaveService temporaryLeaveService) {
		this.temporaryLeaveService = temporaryLeaveService;
	}

	public LineService getLineService() {
		return lineService;
	}

	public void setLineService(LineService lineService) {
		this.lineService = lineService;
	}

	public BandService getBandService() {
		return bandService;
	}

	public void setBandService(BandService bandService) {
		this.bandService = bandService;
	}

	public DicService getDicService() {
		return dicService;
	}

	public void setDicService(DicService dicService) {
		this.dicService = dicService;
	}

	public PersonalCheckService getPersonalCheckService() {
		return personalCheckService;
	}

	public void setPersonalCheckService(
			PersonalCheckService personalCheckService) {
		this.personalCheckService = personalCheckService;
	}

	public InformationCollectionService getInformationCollectionService() {
		return informationCollectionService;
	}

	public void setInformationCollectionService(
			InformationCollectionService informationCollectionService) {
		this.informationCollectionService = informationCollectionService;
	}

	public ActivityRecordService getActivityRecordService() {
		return activityRecordService;
	}

	public void setActivityRecordService(
			ActivityRecordService activityRecordService) {
		this.activityRecordService = activityRecordService;
	}

	public Temporary_Leave getTemporaryLeave() {
		return temporaryLeave;
	}

	public void setTemporaryLeave(Temporary_Leave temporaryLeave) {
		this.temporaryLeave = temporaryLeave;
	}

	public PHCSMP_Suspect getSuspectInfor() {
		return suspectInfor;
	}

	public void setSuspectInfor(PHCSMP_Suspect suspectInfor) {
		this.suspectInfor = suspectInfor;
	}

	public String getTempLeave_Time() {
		return tempLeave_Time;
	}

	public void setTempLeave_Time(String tempLeave_Time) {
		this.tempLeave_Time = tempLeave_Time;
	}

	public String getTempLeave_Reason() {
		return tempLeave_Reason;
	}

	public void setTempLeave_Reason(String tempLeave_Reason) {
		this.tempLeave_Reason = tempLeave_Reason;
	}

	public String getReturn_Time() {
		return return_Time;
	}

	public void setReturn_Time(String return_Time) {
		this.return_Time = return_Time;
	}

	public String getStaff_ID() {
		return staff_ID;
	}

	public void setStaff_ID(String staff_ID) {
		this.staff_ID = staff_ID;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getSuspectID() {
		return suspectID;
	}

	public void setSuspectID(String suspectID) {
		this.suspectID = suspectID;
	}

	public PHCSMP_Personal_Check getPersonalCheck() {
		return personalCheck;
	}

	public void setPersonalCheck(PHCSMP_Personal_Check personalCheck) {
		this.personalCheck = personalCheck;
	}

	public PHCSMP_Information_Collection getInformationCollection() {
		return informationCollection;
	}

	public void setInformationCollection(
			PHCSMP_Information_Collection informationCollection) {
		this.informationCollection = informationCollection;
	}

	public List<PHCSMP_Activity_Record> getActivityRecordList() {
		return activityRecordList;
	}

	public void setActivityRecordList(
			List<PHCSMP_Activity_Record> activityRecordList) {
		this.activityRecordList = activityRecordList;
	}

	public int getSuspectComplete() {
		return suspectComplete;
	}

	public void setSuspectComplete(int suspectComplete) {
		this.suspectComplete = suspectComplete;
	}

	public int getPersonalCheckComplete() {
		return personalCheckComplete;
	}

	public void setPersonalCheckComplete(int personalCheckComplete) {
		this.personalCheckComplete = personalCheckComplete;
	}

	public int getInformationCollectionComplete() {
		return informationCollectionComplete;
	}

	public void setInformationCollectionComplete(
			int informationCollectionComplete) {
		this.informationCollectionComplete = informationCollectionComplete;
	}

	public Map<Integer, Integer> getCompleteMap() {
		return completeMap;
	}

	public void setCompleteMap(Map<Integer, Integer> completeMap) {
		this.completeMap = completeMap;
	}

	public StringBuilder getSb() {
		return sb;
	}

	public void setSb(StringBuilder sb) {
		this.sb = sb;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// ================================================

}
