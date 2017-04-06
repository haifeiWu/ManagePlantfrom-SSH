package com.haifeiWu.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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
import com.haifeiWu.utils.CompleteCheck;
import com.haifeiWu.utils.Video;
import org.apache.commons.io.FileUtils;
import java.io.File;
import org.apache.struts2.ServletActionContext;
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
	private PHCSMP_Activity_Record activityRecord;

	private int suspectComplete;
	private int personalCheckComplete;
	private int informationCollectionComplete;
	private int activityRecordComplete;
	private StringBuilder sb;

	private File file;
	private String fileContentType;
	private String fileFileName;
	
	private File sfile;
	private String sfileContentType;
	private String sfileFileName;
	
	/*
	 * 上传图片
	 * */
	public void uploadPhoto(){
		//找到当前嫌疑人
		String ip = request.getRemoteAddr();
		PHCSMP_Room room = roomService.findbyIp(ip);
		int room_id = room.getRoom_ID();
		System.out.println("获取到roomid为" + room_id);
		PHCSMP_Suspect suspectInfor = suspectService.findByRoomID(room_id);

		String path=ServletActionContext.getServletContext().getRealPath("/images");
		System.out.println(path+"///////////////////////////");
		try {
			//删除原图片
			String curPath=ServletActionContext.getServletContext().getRealPath("/")+suspectInfor.getFrontal_Photo();
			new File(curPath).delete();
			String curPath2=ServletActionContext.getServletContext().getRealPath("/")+suspectInfor.getSideWays_Photo();
			new File(curPath2).delete();
			//将新头像上传到服务器
			FileUtils.copyFile(file, new File(new File(path),fileFileName));
			FileUtils.copyFile(sfile, new File(new File(path),sfileFileName));
			//修改数据库中用户的头像路径信息
			String fpath="images/"+fileFileName;
			String spath="images/"+sfileFileName;
			suspectService.updateSuspectPhotoPath(fpath,spath,suspectInfor.getSuspect_ID());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @return
	 * @throws Exception
	 */
	public String addLeaveRecordInfor() throws Exception {
		try {
			// 打印提交的单条信息
			System.out.println("单条信息：" + model.toString());

			// 获得RoomIP地址，根据ip地址找到房间号，根据房间号找到当前嫌疑人
			String ip = request.getRemoteAddr();
			PHCSMP_Room room = roomService.findbyIp(ip);
			int room_id = room.getRoom_ID();
			System.out.println("获取到roomid为" + room_id);
			PHCSMP_Suspect suspectInfor = suspectService.findByRoomID(room_id);

			// 设置最终离开时间和 领取时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String leavetime = sdf.format(date);
			model.setLeave_Time(leavetime);
			model.setTreatment_Time(leavetime);
			
			//设置离区   嫌疑人的ID
			model.setSuspect_ID(suspectInfor.getSuspect_ID());	
			
			//动态设置离区嫌疑人的字段信息
			Class<?> c = Class.forName(PHCSMP_Leave_Record.class.getName());
			int count1 = CompleteCheck.IsEqualsNull(model, c);
			int fieldsNumber1 = CompleteCheck.getFieldsNumber(model, c);
			model.setFill_record(fieldsNumber1 - count1 - 4);// 设置已填写的字段数
			model.setTotal_record(fieldsNumber1 - 4);// 设置应填写的字段
			System.out.println("未填写的字段：" + count1);
			System.out.println("总字段：" + fieldsNumber1);

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

			// 停止录像
			String stopRecording = Video.stopRecording(room.getCardReader_ID(),
					room.getLine_Number(),
					suspectInfor.getIdentifyCard_Number());
			
			// 释放回路
			lineService.closeLine();
			
			// 释放手环
			bandService.update(0, suspectInfor.getBand_ID());
			
			// 将录像的标志位置为0
			suspectService.updateLeaveState(3, -1, 0,
					suspectInfor.getSuspect_ID());
			System.out.println("state=" + suspectInfor.getRecordVideo_State()
					+ " " + "Process_Now=" + suspectInfor.getProcess_Now());

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
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return day + "天" + hour + "小时" + min + "分";
	}

	// 保存临时出区的信息
	public String addTemporaryLeaveInfor() throws IOException {
		try {
			//根据ip找到当前临时出区嫌疑人
			String roomIP = request.getRemoteAddr();
			PHCSMP_Room room = roomService.findbyIp(roomIP);
			suspectInfor = suspectService.findByRoomID(room.getRoom_ID());

			// 获取前台表单数据，并封装成对象.
			Temporary_Leave temporary_Leave = new Temporary_Leave(0,
					suspectInfor.getSuspect_ID(), tempLeave_Time,
					tempLeave_Reason, return_Time, model.getStaff_ID(),
					room.getRoom_ID(),manager);

			// 打印提交的单条信息
			System.out.println(temporary_Leave.toString());
			
			// 如果是出区保存信息,是出区返回则更新信息
			temporaryLeave = temporaryLeaveService
					.IsTemporaryLeaveReturn(suspectInfor.getSuspect_ID());			
			if (temporaryLeave != null) {
				// 更新临时离开返回时间
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
				String temporaryReturnTime = sdf.format(date);
				
				temporaryLeaveService.updateReturnTime(temporaryReturnTime,
						temporary_Leave.getSuspect_ID());
				
				System.out
						.println("嫌疑人出区返回" + temporary_Leave.getReturn_Time());
				
				//如果出区返回时值班室管理员变了，则将新的管理员也保存进数据库
				if(temporaryLeave.getManager()!=manager){
					temporaryLeaveService.updateManager(temporaryLeave.getManager()+","+manager,temporary_Leave.getSuspect_ID());
				}
				
			} else {
				// 设置临时离开时间
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
				String temporaryLeaveTime = sdf.format(date);
				temporary_Leave.setTempLeave_Time(temporaryLeaveTime);
				temporaryLeaveService.saveTemporaryLeaveInfo(temporary_Leave);
				System.out.println("嫌疑人出区");
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
			PHCSMP_Leave_Record lr = (PHCSMP_Leave_Record) request
					.getAttribute("leaveRecordLoadInfor");
			request.setAttribute("PHCSMP_Leave_Record", lr);

			String tempLeave_Reason = (String) request
					.getAttribute("tempLeave_Reason");
			String staff_ID = (String) request.getAttribute("staff_ID");
			String manager_name = (String) request.getAttribute("manager_name");

			request.setAttribute("tempLeave_Reason", tempLeave_Reason);
			request.setAttribute("staff_ID", staff_ID);
			request.setAttribute("manager_name", manager_name);

			// 加载嫌疑人信息
			String roomIP = request.getRemoteAddr();
			PHCSMP_Room room = roomService.findbyIp(roomIP);
			suspectInfor = suspectService.findByRoomID(room.getRoom_ID());

			// 离区前提示前四个业务的完整性
			// 根据嫌疑人id查找嫌疑人前四个业务的信息
			String suspect_id = suspectInfor.getSuspect_ID();
			sb = new StringBuilder("");
			
			// 查入区登记信息
			suspect = suspectService.findBySuspetcId(suspect_id);
			if(suspect!=null){
				suspectComplete = CompleteCheck.completeCheck(suspect,
						suspect.getClass(), 3);
				System.out.println(suspectComplete
						+ "=============================");
				if (suspectComplete != 100) {// 信息不完整
					sb.append("入区登记信息填写不完整!  ");
					System.out.println(sb + "1");
				}
			}else{
 				sb.append("该嫌疑人并未进行入区登记操作!  ");
 			}
			
			//查人身检查信息
 			personalCheck=personalCheckService.findInforBySuspetcId(suspect_id);
			if(personalCheck!=null){
 				personalCheckComplete=CompleteCheck.completeCheck(personalCheck, personalCheck.getClass(),3);
 				System.out.println(personalCheckComplete+"");
 				if(personalCheckComplete!=100){//信息不完整
 					sb.append("人身检查信息填写不完整!  ");
					System.out.println(sb+"2");
				}
 			}else{
 				sb.append("该嫌疑人并未进行人身检查操作! ");
 			}
			
 			//查信息采集信息
			informationCollection=informationCollectionService.findInforBySuspetcId(suspect_id);
 			if(informationCollection!=null){
 				informationCollectionComplete=CompleteCheck.completeCheck(informationCollection, informationCollection.getClass(),3);
 				System.out.println(informationCollectionComplete+"=============================");
 				if(informationCollectionComplete!=100){//信息不完整
 					sb.append("信息采集信息填写不完整!  ");
 					System.out.println(sb+"3");
 				}
 			}else{
				sb.append("该嫌疑人并未进行信息采集操作!  ");
 			}
			
 			//查询问讯问信息
 			activityRecord=activityRecordService.findInforBySuspetcId(suspect_id);
 			if(activityRecord!=null){
 				activityRecordComplete=CompleteCheck.completeCheck(activityRecord, activityRecord.getClass(),3);
 				System.out.println(activityRecordComplete+"=============================");
				if(activityRecordComplete!=100){//信息不完整
					sb.append("询问讯问信息填写不完整!  ");
					System.out.println(sb+"4");
 				}
 			}else{
 				sb.append("该嫌疑人并未进行询问讯问操作!  ");
 			}		

			// 维护进出门的标志位
			suspectService.updateSwitch(1, suspectInfor.getSuspect_ID());

			// 判断是否出区返回
			temporaryLeave = temporaryLeaveService
					.IsTemporaryLeaveReturn(suspectInfor.getSuspect_ID());

			List<PHCSMP_Dic_Leaving_Reason> leaveReason = dicService
					.findLeaveReason();
			List<PHCSMP_Dic_Keeping_Way> keepingWay = dicService
					.findKeepingWay();			
			List<PHCSMP_Dic_Treatment_Method> treatmentMethod=dicService
					.findTreatmentMethod();
			
			request.setAttribute("leaveReason", leaveReason);
			request.setAttribute("keepingWay", keepingWay);
			request.setAttribute("treatmentMethod", treatmentMethod);
			
			// 判断是否登录
			PHCSMP_Staff user = (PHCSMP_Staff) request.getSession()
					.getAttribute("user");
			if (user == null) {
				return "unLoginState";
			} else {
				return "loadInfor";
			}
		} catch (Exception e) {
			// 异常处理
			response.getWriter()
					.write("<script type='text/javascript'>alert('加载失败，可能是房间或读卡设备配置错误，修改配置后刷新页面');</script>");
			response.getWriter().flush();
			suspectService.updateSwitch(0, suspectInfor.getSuspect_ID());
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

	public PHCSMP_Activity_Record getActivityRecord() {
		return activityRecord;
	}

	public void setActivityRecord(PHCSMP_Activity_Record activityRecord) {
		this.activityRecord = activityRecord;
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

	public int getActivityRecordComplete() {
		return activityRecordComplete;
	}

	public void setActivityRecordComplete(int activityRecordComplete) {
		this.activityRecordComplete = activityRecordComplete;
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File getSfile() {
		return sfile;
	}

	public void setSfile(File sfile) {
		this.sfile = sfile;
	}

	public String getSfileContentType() {
		return sfileContentType;
	}

	public void setSfileContentType(String sfileContentType) {
		this.sfileContentType = sfileContentType;
	}

	public String getSfileFileName() {
		return sfileFileName;
	}

	public void setSfileFileName(String sfileFileName) {
		this.sfileFileName = sfileFileName;
	}

}
