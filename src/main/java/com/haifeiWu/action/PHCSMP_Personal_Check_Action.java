package com.haifeiWu.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.entity.PHCSMP_BelongingS;
import com.haifeiWu.entity.PHCSMP_Cabinet;
import com.haifeiWu.entity.PHCSMP_Dic_Inspection_Situation;
import com.haifeiWu.entity.PHCSMP_Dic_Keeping_Way;
import com.haifeiWu.entity.PHCSMP_Information_Collection;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.BelongingInforService;
import com.haifeiWu.service.InformationCollectionService;
import com.haifeiWu.service.LeaveRecodService;
import com.haifeiWu.service.PersonalCheckService;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.service.UserService;
import com.haifeiWu.utils.CompleteCheck;

/**
 * 人身检查记录信息，对应第二个页面 人身检查，随身物品登记
 * 
 * @author wuhaifei
 * @d2016年8月14日
 */
@Controller
@RequestMapping("/check")
@Scope("prototype")
public class PHCSMP_Personal_Check_Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2170461315353274840L;
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
	@Autowired
	private UserService userService;
	@Autowired
	private InformationCollectionService informationCollectionService;
	@Autowired
	private ActivityRecordService activityRecordService;
	@Autowired
	private LeaveRecodService leaveRecodService;
	// 嫌疑人基本信息
	@Autowired
	SuspectService suspectService;
	@Autowired
	private RoomService roomService;

	/**
	 * 加载当前房间的嫌疑人的信息
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public String loadInfor(@RequestParam("suspectID") String suspectId,
			HttpServletRequest request) throws IOException {
		try {
			// 维护进出门的标志位
			int roomId = roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID();
			request.setAttribute("roomid", roomId);
			// 使用log4j打印日志信息
			logger.debug("Personal_Check----------------------------"
					+ suspectId);
			PHCSMP_Suspect suspectInfor = suspectService
					.findBySuspetcId(suspectId);
			String start_time_time = new DateTime()
					.toString("yyyy-MM-dd HH:mm");// 记录人身检查的开始时间
			List<PHCSMP_Dic_Inspection_Situation> InspectionSituationType = personalCheckService
					.findAllInspectionSituation();// 人身检查记录字
			List<PHCSMP_Dic_Keeping_Way> Keeping_WayType = personalCheckService
					.findAllKeepingWay();// 随身物品保管措施
			List<PHCSMP_Cabinet> PHCSMPCabinetType = personalCheckService
					.findAllPHCSMPCabinet();// 保管柜信息
			PHCSMP_Personal_Check checkRecord = (PHCSMP_Personal_Check) request
					.getAttribute("checkRecord");
			// 如果提交失败，保证填写的信息要显示在页面上
			if (checkRecord != null)
				request.setAttribute("checkRecord", checkRecord);

			// 嫌疑人离开房间，再次进入时填写的信息要显示在页面上
			PHCSMP_Personal_Check updateCheckInfor = personalCheckService
					.findInforBySuspetcId(suspectId);
			if (updateCheckInfor != null) {
				request.setAttribute("checkRecord", updateCheckInfor);
			}
			List<PHCSMP_Staff> staff = userService.findAllStaffs();
			request.setAttribute("staff", staff);
			request.setAttribute("start_time_time", start_time_time);
			request.setAttribute("SuspectInfor", suspectInfor);
			request.setAttribute("InspectionSituationType",
					InspectionSituationType);
			request.setAttribute("Keeping_WayType", Keeping_WayType);
			request.setAttribute("PHCSMPCabinetType", PHCSMPCabinetType);
			// 更新录像状态的标志位
			suspectService.updateSwitch(1, suspectId);
			// 判断进度条
			PHCSMP_Personal_Check personalCheck = personalCheckService
					.findInforBySuspetcId(suspectId);
			PHCSMP_Information_Collection informationCollection = informationCollectionService
					.findInforBySuspetcId(suspectId);
			List<PHCSMP_Activity_Record> activityRecordlist = activityRecordService
					.selectActivityRecordInfor(suspectId);
			if (informationCollection != null) {
				request.setAttribute("informationCollection", 1);
			}
			if (activityRecordlist.size() != 0) {
				request.setAttribute("activityRecord", 1);
			}
			return "WEB-INF/jsp/recordInfor/check";
		} catch (Exception e) {
			return "redirect:/home/index";
		}

	}

	/**
	 * (王金改：对所属物品的值获取做了修改) 添加用户人身检查信息
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCheckPersonInfor(
			@RequestParam List<String> Belonging_Number,
			@RequestParam List<String> Belonging_Name,
			@RequestParam List<String> Belonging_Character,
			@RequestParam List<Integer> Belonging_Count,
			@RequestParam List<String> Keeping_ID,
			@RequestParam List<String> Belonging_Unit,
			@RequestParam List<String> Cabinet_Number,
			PHCSMP_Personal_Check model, HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			ClassNotFoundException {
		System.out.println("--------------=============----------");
		// try {
		System.out.println(Belonging_Number.toString() + "            "
				+ Belonging_Number.size() + "      " + Belonging_Number.get(0));
		System.out.println(Belonging_Name.toString() + "           "
				+ Belonging_Name.size());
		System.out.println(Belonging_Character.toString());
		System.out.println(Belonging_Count.toString());
		System.out.println(Keeping_ID.toString());
		System.out.println(Belonging_Unit.toString());
		System.out.println(Cabinet_Number.toString());
		System.out.println("ip地址" + request.getRemoteAddr());
		int roomId = roomService.findbyIp(request.getRemoteAddr()).getRoom_ID();
		System.out.println("------------" + model.toString());

		String suspectId = model.getSuspect_ID();
		request.setAttribute("suspectId", suspectId);
		model.setCheck_EndTime(new DateTime().toString("yyyy-MM-dd HH:mm"));
		model.setRoom_ID(roomId);

		String[] str = model.getStaff_ID().split(",");

		model.setStaff_ID(str[0]);
		System.out.println(str[0]);
		request.setAttribute("staff_Name", str[0]);

		List<PHCSMP_BelongingS> vaildBelong = new ArrayList<PHCSMP_BelongingS>();// 填写的有效信息
		for (int i = 0; i < Belonging_Name.size(); i++) {
			PHCSMP_BelongingS belongS = new PHCSMP_BelongingS();
			if(Belonging_Character.size()!=0){
			belongS.setBelonging_Character(Belonging_Character.get(i));
			}
			if(Belonging_Name.size()!=0){
			belongS.setBelonging_Name(Belonging_Name.get(i));
			}
			if(Belonging_Count.size()!=0){
			belongS.setBelonging_Count(Belonging_Count.get(i));
			}
			if(Keeping_ID.size()!=0){
			belongS.setKeeping_ID(Keeping_ID.get(i));
			}
			if(Cabinet_Number.size()!=0){
			belongS.setCabinet_Number(Cabinet_Number.get(i));
			}
			if(Belonging_Unit.size()!=0){
			belongS.setBelonging_Unit(Belonging_Unit.get(i));
			}
			if(Belonging_Number.size()!=0){
			belongS.setBelonging_Number(Belonging_Number.get(i));
			}
			System.out.println("belongS               " + belongS.toString());
			if (!(belongS.getBelonging_Name().equals("") || belongS
					.getBelonging_Name() == null)) {// 提交的信息为空
				belongS.setSuspect_ID(model.getSuspect_ID());// 设置档案编号
				belongS.setRoom_ID(roomId);// 设置登记信息的房间编号
				belongS.setStaff_ID(str[1].equals("") ? null : str[1]);
				vaildBelong.add(belongS);
				System.out.println("vaildBelong                    "
						+ vaildBelong.toString());
			}
		}

		/* List<PHCSMP_BelongingS> belongs = this.getBelong(); */
		// List<PHCSMP_BelongingS> vaildBelong = new
		// ArrayList<PHCSMP_BelongingS>();// 填写的有效信息
		// for (PHCSMP_BelongingS belong : belongs) {// 要考虑物品为空的情况
		// if
		// (!(belong.getBelonging_Name().equals("")||belong.getBelonging_Name()==null))
		// {// 提交的信息为空
		// belong.setSuspect_ID(model.getSuspect_ID());// 设置档案编号
		// belong.setRoom_ID(roomId);// 设置登记信息的房间编号
		// belong.setStaff_ID(str[1].equals("") ? null : str[1]);
		// vaildBelong.add(belong);
		//
		// System.out.println("vaildBelong                    "+vaildBelong.toString());
		// }
		// }
		fullCheck(model,request);
		request.setAttribute("check_Situation", model.getCheck_Situation());
		// 判断要更新还是插入
		PHCSMP_Personal_Check old = personalCheckService
				.findInforBySuspetcId(suspectId);
		if (old != null) {// 删去
			personalCheckService.deleteInfor(suspectId);
		}
		// 保存人身检查记录
		personalCheckService.saveCheckPersonInfor(model);
		if (vaildBelong.size() != 0) {
			belongingInforService.saveBelongInforList(vaildBelong);// 批量保存随身物品信息
		}
		// 主动失败
		// throw new Exception();
		// 提示成功
		// response.getWriter().write("<script>alert('后台提交成功');</script>");
		// response.getWriter().flush();
		return "redirect:/home/index";
		// } catch (Exception e) {
		// // response.getWriter()
		// //
		// .write("<script type='text/javascript'>alert('提交失败，请重新提交');</script>");
		// // response.getWriter().flush();
		// request.setAttribute("error", "error");
		// request.setAttribute("checkRecord", model);
		// return "redirect:load";
		// }
	}

	// public String unlogin_load() {
	//
	// return "unlogin_load";
	// }

	// 返回修改人身检查信息
	// public String updateInfor() {
	// logger.debug("档案编号：" + request.getParameter("Suspect_ID"));
	// logger.debug("updateInfor：修改人身检查信息！");
	// return "updateInfor";
	// }

	public List<PHCSMP_BelongingS> getBelong() {
		return belong;
	}

	public void setBelong(List<PHCSMP_BelongingS> belongs) {
		this.belong = belongs;
	}

	private void fullCheck(PHCSMP_Personal_Check model,HttpServletRequest request)
			throws ClassNotFoundException {
		Class<?> c = Class.forName(PHCSMP_Personal_Check.class.getName());//
		// 通过反射找到该类的字段

		int count = CompleteCheck.IsEqualsNull(model, c);
		int fieldsNumber = CompleteCheck.getFieldsNumber(model, c);

		model.setFill_record(fieldsNumber - count - 3);// 设置已填写的字段数
		model.setTotal_record(fieldsNumber - 3);// 设置应填写的字段
		int complete = (int) ((int)(fieldsNumber - count - 3)/(float) (fieldsNumber - 3) * 100);
		request.setAttribute("complete", complete);
		System.out.println("未填写的字段：" + count);
		System.out.println("总字段：" + fieldsNumber);
	}
}
