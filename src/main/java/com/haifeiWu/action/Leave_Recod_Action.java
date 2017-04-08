package com.haifeiWu.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.entity.PHCSMP_Dic_Keeping_Way;
import com.haifeiWu.entity.PHCSMP_Dic_Leaving_Reason;
import com.haifeiWu.entity.PHCSMP_Dic_Treatment_Method;
import com.haifeiWu.entity.PHCSMP_Information_Collection;
import com.haifeiWu.entity.PHCSMP_Leave_Record;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.entity.PHCSMP_Room;
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
import com.haifeiWu.utils.CompleteCheck;
import com.haifeiWu.utils.Video;

/**
 * 离开办案区的action
 * 
 * @author wuhaifei
 * @d2016年8月17日
 */

@Controller
@Scope("prototype")
public class Leave_Recod_Action extends BaseAction<PHCSMP_Leave_Record> {
	/**
	 * 序列化的字段
	 */
	private static final long serialVersionUID = 1L;

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

	private PHCSMP_Suspect suspect;
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
	 * @throws IOException
	 * @throws Exception
	 */
	public String addLeaveRecordInfor() throws IOException {
		try {
			PHCSMP_Room room = roomService.findbyIp(request.getRemoteAddr());
			// 设置最终离开时间和 领取时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String leavetime = sdf.format(date);
			model.setLeave_Time(leavetime);
			model.setTreatment_Time(leavetime);
			// 设置离区 嫌疑人的ID
			model.setSuspect_ID(suspectInfor.getSuspect_ID());
			// 动态设置离区嫌疑人的字段信息
			Class<?> c = Class.forName(PHCSMP_Leave_Record.class.getName());
			int count1 = CompleteCheck.IsEqualsNull(model, c);
			int fieldsNumber1 = CompleteCheck.getFieldsNumber(model, c);
			model.setFill_record(fieldsNumber1 - count1 - 4);// 设置已填写的字段数
			model.setTotal_record(fieldsNumber1 - 4);// 设置应填写的字段
			// 设置羁押时间
			String detain_Time = getDistanceTime(suspectInfor.getEnter_Time(),
					leavetime);
			suspectInfor.setDetain_Time(detain_Time);

			// 保证不插入重复数据
			PHCSMP_Leave_Record LeaveRecordInfor = leaveRecodService
					.findLeaveRecordInfor(suspectInfor.getSuspect_ID());
			if (LeaveRecordInfor == null) {
				leaveRecodService.saveLeaveRecordInfor(model);// 保存嫌疑人离开信息，
			} else {
				leaveRecodService.updateLeaveRecordInfor(model);// 更新嫌疑人离开信息
			}
			// 停止录像,手环
			String stopRecording = Video.stopRecording(
					suspectInfor.getBand_ID(), room.getLine_Number(),
					suspectInfor.getIdentifyCard_Number());

			// 释放回路
			if (suspectInfor.getRecordVideo_State() != 0)
				lineService.closeLine();
			// 释放手环
			bandService.update(0, suspectInfor.getBand_ID());
			// 将录像的标志位置为0
			suspectService.updateLeaveState(3, -1, 0,
					suspectInfor.getSuspect_ID());
			return "success";

		} catch (Exception e) {
			response.getWriter()
					.write("<script type='text/javascript'> alert('提交失败，请重新提交'); </script>");
			response.getWriter().flush();

			request.setAttribute("leaveRecordLoadInfor", model);

			return "leaveRecordLoadInfor";
		}

	}

	// 求两个时间的间隔
	public static String getDistanceTime(String str1, String str2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date one;
		Date two;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			// day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000));
			// min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			// sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min *
			// 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return hour + "小时";
	}

	// 保存临时出区的信息
	public String addTemporaryLeaveInfor() throws IOException {
		try {
			// 根据ip找到房间
			PHCSMP_Room room = roomService.findbyIp(request.getRemoteAddr());
			// suspectInfor = suspectService.findByRoomID(room.getRoom_ID());

			// 获取前台表单数据，并封装成对象.
			Temporary_Leave temporary_Leave = new Temporary_Leave(0,
					suspectInfor.getSuspect_ID(), tempLeave_Time,
					tempLeave_Reason, return_Time, model.getStaff_ID(),
					room.getRoom_ID(), manager);
			// 如果是出区保存信息,是出区返回则更新信息
			temporaryLeave = temporaryLeaveService
					.IsTemporaryLeaveReturn(suspectInfor.getSuspect_ID());
			// 临时离开返回
			if (temporaryLeave != null) {
				// 更新临时离开返回时间
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String temporaryReturnTime = sdf.format(date);

				temporaryLeaveService.updateReturnTime(temporaryReturnTime,
						temporary_Leave.getSuspect_ID());
				// 如果出区返回时值班室管理员变了，则将新的管理员也保存进数据库
				if (temporaryLeave.getManager() != manager) {
					temporaryLeaveService.updateManager(
							temporaryLeave.getManager() + "," + manager,
							temporary_Leave.getSuspect_ID());
				}
			} else {// 临时离开
				// 设置临时离开时间
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String temporaryLeaveTime = sdf.format(date);

				temporary_Leave.setTempLeave_Time(temporaryLeaveTime);
				temporaryLeaveService.saveTemporaryLeaveInfo(temporary_Leave);
			}
			return "success";

		} catch (Exception e) {
			response.getWriter()
					.write("<script type='text/javascript'>alert('提交失败，请重新提交');</script>");
			response.getWriter().flush();

			String tempLeave_Reason = request.getParameter("tempLeave_Reason");
			request.setAttribute("tempLeave_Reason", tempLeave_Reason);
			String staff_ID = request.getParameter("staff_ID");
			request.setAttribute("staff_ID", staff_ID);
			String manager_name = request.getParameter("manager_name");
			request.setAttribute("manager_name", manager_name);

			return "temporaryLeaveload";
		}
	}

	/* 加载界面信息 */
	public String loadInfor() throws IOException {
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
			String suspectId = (String) request.getAttribute("suspectID");

			// 离区前提示前四个业务的完整性
			// 根据嫌疑人id查找嫌疑人前四个业务的信息
			sb = new StringBuilder("");
			// 查入区登记信息
			suspect = suspectService.findBySuspetcId(suspectId);
			// 并不需要再次进行完整性检查，只需读取数据，除一下即可
			suspectComplete = (int) (suspect.getFill_record()
					/ (float) suspect.getTotal_record() * 100);
			if (suspectComplete != 100) {// 信息不完整
				sb.append("入区登记信息填写不完整!  ");
			}
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
			// 查询问讯问信息
			activityRecordList = activityRecordService
					.findInforBySuspetcId(suspectId);
			completeMap = new HashMap<Integer, Integer>();
			if (activityRecordList != null) {
				int j = 0;
				int i = 1;
				for (PHCSMP_Activity_Record Activity_Record : activityRecordList) {
					int activityRecordComplete = (int) (Activity_Record
							.getFill_record()
							/ (float) Activity_Record.getTotal_record() * 100);
					completeMap.put(j, activityRecordComplete);
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
			// 判断是否登录
			// PHCSMP_Staff user = (PHCSMP_Staff) request.getSession()
			// .getAttribute("user");

			// 判断进度条
			// PHCSMP_Suspect suspect =
			// suspectService.findBySuspetcId(suspectId);
			// PHCSMP_Personal_Check personalCheck = personalCheckService
			// .findInforBySuspetcId(suspectId);
			// PHCSMP_Information_Collection informationCollection =
			// informationCollectionService
			// .findInforBySuspetcId(suspectId);
			// List<PHCSMP_Activity_Record> activityRecordlist =
			// activityRecordService
			// .selectActivityRecordInfor(suspectId);
			// PHCSMP_Leave_Record leaveRecord = leaveRecodService
			// .findInforBySuspetcId(suspectId);
			request.setAttribute("suspect", 1);
			request.setAttribute("personalCheck", personalCheck);
			request.setAttribute("informationCollection", informationCollection);
			request.setAttribute("activityRecord", activityRecordList);
			// request.setAttribute("leaveRecord", leaveRecord);
			return "loadInfor";
		} catch (Exception e) {
			// 异常处理
			response.getWriter()
					.write("<script type='text/javascript'>alert('加载失败，可能是房间或读卡设备配置错误，修改配置后刷新页面');</script>");
			response.getWriter().flush();
			// suspectService.updateSwitch(0, suspectInfor.getSuspect_ID());
			// 转到
			return "success";
		}
	}

	// 未登录状态时
	public String unlogin_load() {
		return "unlogin_load";
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

	public PHCSMP_Suspect getSuspectInfor() {
		return suspectInfor;
	}

	public void setSuspectInfor(PHCSMP_Suspect suspectInfor) {
		this.suspectInfor = suspectInfor;
	}

	public Temporary_Leave getTemporaryLeave() {
		return temporaryLeave;
	}

	public void setTemporaryLeave(Temporary_Leave temporaryLeave) {
		this.temporaryLeave = temporaryLeave;
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

	public PHCSMP_Suspect getSuspect() {
		return suspect;
	}

	public void setSuspect(PHCSMP_Suspect suspect) {
		this.suspect = suspect;
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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public void setSb(StringBuilder sb) {
		this.sb = sb;
	}

	public List<PHCSMP_Activity_Record> getActivityRecordList() {
		return activityRecordList;
	}

	public void setActivityRecordList(
			List<PHCSMP_Activity_Record> activityRecordList) {
		this.activityRecordList = activityRecordList;
	}

}
