package com.haifeiWu.action;

import java.net.URLDecoder;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_Leave_Record;
import com.haifeiWu.entity.PHCSMP_Room;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.entity.Temporary_Leave;
import com.haifeiWu.service.LeaveRecodService;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.service.TemporaryLeaveService;
import com.haifeiWu.utils.CompleteCheck;

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
	// 嫌疑人信息
	@Autowired
	private SuspectService suspectService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private TemporaryLeaveService temporaryLeaveService;
	private Temporary_Leave temporaryLeave;
	private PHCSMP_Suspect suspectInfor;
	private String tempLeave_Time;
	private String tempLeave_Reason;
	private String return_Time;
	private String staff_ID;
	private String personName;
	private String suspectID;

	/**
	 * 点击画面中的“下一步”，提交信息并转发到suspectManage_suspectInforSummary.action
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addLeaveRecordInfor() throws Exception {

		// 打印提交的单条信息
		System.out.println("单条信息：" + model.toString());

		PHCSMP_Suspect SuspectInfor = suspectService.findByRoomID(4);

		this.personName = URLDecoder.decode(SuspectInfor.getSuspect_Name(),
				"utf-8");
		this.suspectID = SuspectInfor.getSuspect_ID();

		/*// 打印list信息
		List<Temporary_Leave> temporaryLeaves = this.getTemporaryLeave();
		System.out.println("多条信息：" + temporaryLeaves.size());
		for (Temporary_Leave temporaryLeave : temporaryLeaves) {
			temporaryLeave.setSuspect_ID(model.getSuspect_ID());// 设置档案号
			System.out.println(temporaryLeave.toString());
		}
*/
		// 通过反射加载离开办案区记录的类
		Class<?> c = Class.forName(PHCSMP_Leave_Record.class.getName());

		int count = CompleteCheck.IsEqualsNull(model, c);
		int fieldsNumber = CompleteCheck.getFieldsNumber(model, c);

		model.setFill_record(fieldsNumber - count - 4);// 设置已填写的字段数
		model.setTotal_record(fieldsNumber - 4);// 设置应填写的字段
		System.out.println("未填写的字段：" + count);
		System.out.println("总字段：" + fieldsNumber);

		/*leaveRecodService.saveLeaveRecordInfor(temporaryLeaves);// 保存多次临时离开的信息
*/		leaveRecodService.saveLeaveRecordInfor(model);// 保存嫌疑人离开信息

		return "addLeaveRecordInfor";
	}
	
	//保存临时出区的信息
	public String addTemporaryLeaveInfor(){
		String roomIP=request.getRemoteAddr();
		PHCSMP_Room room=roomService.findbyIp(roomIP);
		suspectInfor=suspectService.findByRoomID(room.getRoom_ID());
		//获取前台表单数据，并封装成对象.
		Temporary_Leave temporary_Leave=new Temporary_Leave(0,suspectInfor.getSuspect_ID(),
				tempLeave_Time, tempLeave_Reason, return_Time, model.getStaff_ID(), room.getRoom_ID());
		// 打印提交的单条信息
		System.out.println(temporary_Leave.toString());
		temporaryLeave=temporaryLeaveService.IsTemporaryLeaveReturn(suspectInfor.getSuspect_ID());
		//如果是出区保存信息,是出区返回则更新信息
		if(temporaryLeave!=null){
			temporaryLeaveService.updateTemporaryLeaveInfo(temporary_Leave);
			System.out.println("嫌疑人出区返回"+temporary_Leave.getReturn_Time());
		}else{
			temporaryLeaveService.saveTemporaryLeaveInfo(temporary_Leave);
			System.out.println("嫌疑人出区");
		}
		
		return "addTemporaryLeaveInfor";
	}
	
	/* 加载界面信息 */
	public String loadInfor(){
		//加载嫌疑人信息 
		String roomIP=request.getRemoteAddr();
		PHCSMP_Room room=roomService.findbyIp(roomIP);
		suspectInfor=suspectService.findByRoomID(room.getRoom_ID());
		//判断是否出区返回
		temporaryLeave=temporaryLeaveService.IsTemporaryLeaveReturn(suspectInfor.getSuspect_ID());
		//判断是否登录
		PHCSMP_Staff user = (PHCSMP_Staff) request.getSession().getAttribute(
				"user");
		if (user == null) {
			return "unLoginState";
		} else {
			System.out.println("Leave_Recod_Action:loadInfor");
			return "loadInfor";
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
	
	
}
