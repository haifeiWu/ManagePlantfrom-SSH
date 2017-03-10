package com.haifeiWu.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_BelongingS;
import com.haifeiWu.entity.PHCSMP_Cabinet;
import com.haifeiWu.entity.PHCSMP_Dic_Inspection_Situation;
import com.haifeiWu.entity.PHCSMP_Dic_Keeping_Way;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.BelongingInforService;
import com.haifeiWu.service.PersonalCheckService;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.CompleteCheck;

/**
 * 人身检查记录信息，对应第二个页面 人身检查，随身物品登记
 * 
 * @author wuhaifei
 * @d2016年8月14日
 */
@Controller
@Scope("prototype")
public class PHCSMP_Personal_Check_Action extends
		BaseAction<PHCSMP_Personal_Check> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//记录开始时间的私有变量
	private String start_time_time;
	

	

	// 用于多条记录的提取，一条人身检查记录对应多个随身物品登记记录
	private List<PHCSMP_BelongingS> belong = new ArrayList<PHCSMP_BelongingS>();
	private Logger logger = Logger
			.getLogger(PHCSMP_Personal_Check_Action.class);
	// 人身检查的service
	@Autowired
	private PersonalCheckService personalCheckService;
	// 随身物品登记
	@Autowired
	private BelongingInforService belongingInforService;
	// 嫌疑人基本信息
	@Autowired
	SuspectService suspectService;
	@Autowired
	private RoomService roomService;

	/**
	 * 加载上一个房间的嫌疑人的信息
	 * 
	 * @return
	 */
	public String loadInfor() {
		// PHCSMP_Staff user = (PHCSMP_Staff) request.getSession().getAttribute(
		// "user");
		// 维护进出门的标志位
		int roomId = roomService.findbyIp(request.getRemoteAddr()).getRoom_ID();
		PHCSMP_Suspect SuspectInfor = suspectService.findByRoomID(roomId);
		SuspectInfor.setCardReader_Switch(1);
		suspectService.saveSuspect(SuspectInfor);
		start_time_time= new DateTime().toString("yyyy-MM-dd HH:mm");
		request.setAttribute("start_time_time", start_time_time);
		String suspectId = SuspectInfor.getSuspect_ID();
		request.setAttribute("SuspectInfor", SuspectInfor);
		// 人身检查记录字
		List<PHCSMP_Dic_Inspection_Situation> InspectionSituationType = personalCheckService
				.findAllInspectionSituation();

		request.setAttribute("InspectionSituationType", InspectionSituationType);
		// 随身物品保管措施
		List<PHCSMP_Dic_Keeping_Way> Keeping_WayType = personalCheckService
				.findAllKeepingWay();
		for (PHCSMP_Dic_Keeping_Way phcsmp_Dic_Keeping_Way : Keeping_WayType) {
			System.out.println(phcsmp_Dic_Keeping_Way.toString());
		}
		request.setAttribute("Keeping_WayType", Keeping_WayType);

		// 保管柜信息
		List<PHCSMP_Cabinet> PHCSMPCabinetType = personalCheckService
				.findAllPHCSMPCabinet();
		for (PHCSMP_Cabinet phcsmp_Cabinet : PHCSMPCabinetType) {
			System.out.println(PHCSMPCabinetType.toString());
		}
		request.setAttribute("PHCSMPCabinetType", PHCSMPCabinetType);
//		System.out.println("开始时间："+start_time_time.toString());
		
		return "loadInfor";

	}

	/**
	 * 添加用户人身检查信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addCheckPersonInfor() throws Exception {
		// 维护进出门的标志位
//		System.out.println("加载开始时间前："+start_time_time.toString());
		int roomId = roomService.findbyIp(request.getRemoteAddr()).getRoom_ID();
		PHCSMP_Suspect SuspectInfor = suspectService.findByRoomID(roomId);
		suspectService.updateSwitch(0, SuspectInfor.getSuspect_ID());
//		model.setCheck_StartTime(start_time_time);
		model.setCheck_EndTime(new DateTime().toString("yyyy-MM-dd HH:mm"));// 设置人身检查的结束时间
		String[] str = model.getStaff_ID().split(",");
		model.setStaff_ID(str[0]);

		System.out.println("---------------------------->model"
				+ model.toString());
		List<PHCSMP_BelongingS> belongs = this.getBelong();
		for (PHCSMP_BelongingS belong : belongs) {// staff_ID_Belonging=null,房间号
			System.out.println("------------------------------->"
					+ belong.toString());
			belong.setSuspect_ID(model.getSuspect_ID());// 设置档案编号
			belong.setRoom_ID(roomId);// 设置登记信息的房间编号
			belong.setStaff_ID(str[1].equals("") ? null : str[1]);

			// System.out.println("------------------------------->"
			// + belong.toString());

		}
//		System.out.println("加载开始时间后："+start_time_time.toString());
		personalCheckService.saveCheckPersonInfor(model);// 保存人身检查记录
		belongingInforService.saveBelongInforList(belongs);// 批量保存随身物品信息
		fullCheck();
		// logger.info("用户 " + " 提交用户嫌疑人人身检查信息，时间："
		// + new DateTime().toString("yyyy-MM-dd hh:mm a E"));
//		System.out.println("开始时间："+start_time_time.toString());
		return "addCheckPersonInfor";
	}

	public String unlogin_load() {

		return "unlogin_load";
	}

	// 返回修改人身检查信息
	public String updateInfor() {
		System.out.println("档案编号：" + request.getParameter("Suspect_ID"));
		System.out.println("updateInfor：修改人身检查信息！");
		return "updateInfor";
	}

	public List<PHCSMP_BelongingS> getBelong() {
		return belong;
	}

	public void setBelong(List<PHCSMP_BelongingS> belongs) {
		this.belong = belongs;
	}

	private void fullCheck() throws ClassNotFoundException {
		Class<?> c = Class.forName(PHCSMP_Personal_Check.class.getName());// 通过反射找到该类的字段

		int count = CompleteCheck.IsEqualsNull(model, c);
		int fieldsNumber = CompleteCheck.getFieldsNumber(model, c);

		model.setFill_record(fieldsNumber - count - 3);// 设置已填写的字段数
		model.setTotal_record(fieldsNumber - 3);// 设置应填写的字段
		System.out.println("未填写的字段：" + count);
		System.out.println("总字段：" + fieldsNumber);
	}
	public String getStart_time_time() {
		return start_time_time;
	}

	public void setStart_time_time(String start_time_time) {
		this.start_time_time = start_time_time;
	}
}
