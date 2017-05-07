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
	 * 添加用户人身检查信息
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCheckPersonInfor(PHCSMP_Personal_Check model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			System.out.println("ip地址" + request.getRemoteAddr());
			int roomId = roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID();
			System.out.println("------------" + model.toString());

			String suspectId = model.getSuspect_ID();

			model.setCheck_EndTime(new DateTime().toString("yyyy-MM-dd HH:mm"));
			model.setRoom_ID(roomId);

			String[] str = model.getStaff_ID().split(",");

			model.setStaff_ID(str[0]);
			request.setAttribute("staff_Name", str[0]);
			List<PHCSMP_BelongingS> belongs = this.getBelong();
			List<PHCSMP_BelongingS> vaildBelong = new ArrayList<PHCSMP_BelongingS>();// 填写的有效信息
			for (PHCSMP_BelongingS belong : belongs) {// 要考虑物品为空的情况
				if (!belong.getBelonging_Name().equals("")) {// 提交的信息为空
					belong.setSuspect_ID(model.getSuspect_ID());// 设置档案编号
					belong.setRoom_ID(roomId);// 设置登记信息的房间编号
					belong.setStaff_ID(str[1].equals("") ? null : str[1]);
					vaildBelong.add(belong);
				}
			}
			fullCheck(model);
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
		} catch (Exception e) {
			// response.getWriter()
			// .write("<script type='text/javascript'>alert('提交失败，请重新提交');</script>");
			// response.getWriter().flush();
			request.setAttribute("error", "error");
			request.setAttribute("checkRecord", model);
			return "redirect:load";
		}
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

	private void fullCheck(PHCSMP_Personal_Check model)
			throws ClassNotFoundException {
		Class<?> c = Class.forName(PHCSMP_Personal_Check.class.getName());//
		// 通过反射找到该类的字段

		int count = CompleteCheck.IsEqualsNull(model, c);
		int fieldsNumber = CompleteCheck.getFieldsNumber(model, c);

		model.setFill_record(fieldsNumber - count - 3);// 设置已填写的字段数
		model.setTotal_record(fieldsNumber - 3);// 设置应填写的字段
		System.out.println("未填写的字段：" + count);
		System.out.println("总字段：" + fieldsNumber);
	}
}
