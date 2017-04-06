package com.haifeiWu.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.entity.PHCSMP_BelongingS;
import com.haifeiWu.entity.PHCSMP_Cabinet;
import com.haifeiWu.entity.PHCSMP_Dic_Inspection_Situation;
import com.haifeiWu.entity.PHCSMP_Dic_Keeping_Way;
import com.haifeiWu.entity.PHCSMP_Information_Collection;
import com.haifeiWu.entity.PHCSMP_Leave_Record;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.BelongingInforService;
import com.haifeiWu.service.InformationCollectionService;
import com.haifeiWu.service.LeaveRecodService;
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
	private static final long serialVersionUID = 2170461315353274840L;
	// 记录开始时间的私有变量
	// private String start_time_time;
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
	public String loadInfor() throws IOException {
		try {
			
			// 维护进出门的标志位
			int roomId = roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID();
			PHCSMP_Suspect SuspectInfor = suspectService.findByRoomID(roomId);
			String suspectId = SuspectInfor.getSuspect_ID();
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
			if (checkRecord != null)
				request.setAttribute("checkRecord", checkRecord);
			request.setAttribute("start_time_time", start_time_time);
			request.setAttribute("SuspectInfor", SuspectInfor);
			request.setAttribute("InspectionSituationType",
					InspectionSituationType);
			request.setAttribute("Keeping_WayType", Keeping_WayType);
			request.setAttribute("PHCSMPCabinetType", PHCSMPCabinetType);
			// 更新录像状态的标志位
			suspectService.updateSwitch(1, suspectId);
			
			System.out.println("suspectId"+suspectId);
			//判断进度条
			//String suspectId=setSuspectId(entry_Time);
			//String suspectId="LB-HB-20170317005";
			PHCSMP_Suspect suspect=suspectService.findBySuspetcId(suspectId);
			PHCSMP_Personal_Check personalCheck=personalCheckService.findInforBySuspetcId(suspectId);
			PHCSMP_Information_Collection informationCollection=informationCollectionService.findInforBySuspetcId(suspectId);
			List<PHCSMP_Activity_Record> activityRecordlist=activityRecordService.selectActivityRecordInfor(suspectId);
			PHCSMP_Leave_Record leaveRecord=leaveRecodService.findInforBySuspetcId(suspectId);
			request.setAttribute("suspect", suspect);
			request.setAttribute("personalCheck", personalCheck);
			request.setAttribute("informationCollection", informationCollection);
			request.setAttribute("activityRecord", activityRecordlist);
			request.setAttribute("leaveRecord", leaveRecord);
			System.out.println("suspect="+suspect+" "+"personalCheck="+personalCheck+" "+"informationCollection="+informationCollection+" "+"activityRecord="+activityRecordlist+" "+"leaveRecord="+leaveRecord);
		} catch (Exception e) {
			// 提示可能是房间、读卡器等设备配置错误
			response.getWriter()
					.write("<script type='text/javascript'>alert('加载失败，可能是房间或读卡设备配置错误，修改配置后刷新页面');</script>");
			response.getWriter().flush();
			// 转到
			return "success";
		}
		return "loadInfor";
	}

	/**
	 * 添加用户人身检查信息
	 * 
	 * @throws IOException
	 */
	public String addCheckPersonInfor() throws IOException {
		try {
			System.out.println("ip地址"+request.getRemoteAddr());
			int roomId = roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID();
			String suspectId = suspectService.findByRoomID(roomId)
					.getSuspect_ID();
			// 设置人身检查的结束时间
			model.setCheck_EndTime(new DateTime().toString("yyyy-MM-dd HH:mm"));
			model.setRoom_ID(roomId);
			String[] str = model.getStaff_ID().split(",");
			model.setStaff_ID(str[0]);
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
			fullCheck();
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
			response.getWriter().write("<script>alert('后台提交成功');</script>");
			response.getWriter().flush();
			return "success";
		} catch (Exception e) {
			response.getWriter()
					.write("<script type='text/javascript'>alert('提交失败，请重新提交');</script>");
			response.getWriter().flush();
			request.setAttribute("checkRecord", model);
			return "chainLoadInfor";
		}
	}

	// public String unlogin_load() {
	//
	// return "unlogin_load";
	// }

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

	// public String getStart_time_time() {
	// return start_time_time;
	// }
	//
	// public void setStart_time_time(String start_time_time) {
	// this.start_time_time = start_time_time;
	// }
}
