package com.haifeiWu.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.entity.PHCSMP_Information_Collection;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.InformationCollectionService;
import com.haifeiWu.service.LeaveRecodService;
import com.haifeiWu.service.PersonalCheckService;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.CompleteCheck;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 活动记录
 * 
 * @author wuhaifei
 * @d2016年9月28日
 */
@Controller
@Scope("prototype")
public class Activity_Record_Action extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1201107017949225716L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;
	@Autowired
	private RoomService roomService;
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
	// 活动记录表list，用于前台提交的多个数据
	private PHCSMP_Activity_Record activity = new PHCSMP_Activity_Record();

	/**
	 * 添加活动记录信息
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */

	public String addActivityRecordInfor() throws IOException {
		try {
			// 加载当前房间的嫌疑人
			int roomId = roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID();
			String suspectId = suspectService.findByRoomID(roomId)
					.getSuspect_ID();

			// 获取前台数据
			String start_Time = request.getParameter("start_Time");
			String activity_Record = request.getParameter("activity_Record");
			String activity_remark = request.getParameter("remark");
			activity.setStart_Time(start_Time);
			activity.setRemark(activity_remark);
			activity.setActivity_Record(activity_Record);

			// 设置询问讯问结束的时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String End_Time = sdf.format(date);
			activity.setSuspect_ID(suspectId);
			activity.setRoom_ID(1);
			activity.setEnd_Time(End_Time);
			fullCheck(activity);
			// 保存
			activityRecordService.saveActivityRecordInfor(activity);
			// 提示成功
			response.getWriter().write("<script>alert('后台提交成功');</script>");
			response.getWriter().flush();
			return "success";
		} catch (Exception e) {
			// 提示失败
			response.getWriter()
					.write("<script type='text/javascript'>alert('提交失败，请重新提交');</script>");
			response.getWriter().flush();
			// 将信息传递到loadInfor action,显示在页面上
			String activity_Record = request.getParameter("activity_Record");
			String activity_remark = request.getParameter("remark");
			request.setAttribute("activity_Record", activity_Record);
			request.setAttribute("activity_remark", activity_remark);
			return "addActivityRecordInfor";
		}
	}

	private void fullCheck(PHCSMP_Activity_Record activity)
			throws ClassNotFoundException {
		Class<?> c = Class.forName(PHCSMP_Activity_Record.class.getName());
		// 统计未填写的字段
		int count = CompleteCheck.IsEqualsNull(activity, c);
		// 统计所有的字段
		int fieldsNumber = CompleteCheck.getFieldsNumber(activity, c);

		activity.setFill_record(fieldsNumber - count - 3);// 设置已填写的字段数
		activity.setTotal_record(fieldsNumber - 3);// 设置应填写的字段
	}

	/**
	 * 加载活动记录信息
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public String loadInfor() throws IOException {
		try {
			// 提交失败时将信息再次显示
			if (request.getAttribute("activity_remark") != null) {
				String activity_remark = (String) request
						.getAttribute("activity_remark");
				String activity_Record = (String) request
						.getAttribute("activity_Record");
				request.setAttribute("activity_remark", activity_remark);
				request.setAttribute("activity_Record", activity_Record);
			}
			// 维护进出门的标志位
			int roomId = roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID();
			String suspectId = (String) request.getAttribute("suspectID");
			PHCSMP_Suspect suspectInfor = suspectService
					.findBySuspetcId(suspectId);
			// .getRoom_ID();
			// request.setAttribute("roomId", roomId);
			//
			// // 入区人员信息登记
			// PHCSMP_Suspect suspectInfor =
			// suspectService.findByRoomID(roomId);
			// String suspectId = suspectInfor.getSuspect_ID();

			int complete_degree = (int) (suspectInfor.getFill_record()
					/ (float) suspectInfor.getTotal_record() * 100);
			request.setAttribute("complete_degree", complete_degree);
			request.setAttribute("SuspectInfor", suspectInfor);

			// 人身安全检查
			PHCSMP_Personal_Check personal_Check = personalCheckService
					.findInforBySuspetcId(suspectId);
			if (personal_Check != null) {
				int complete_degree1 = (int) (personal_Check.getFill_record()
						/ (float) personal_Check.getTotal_record() * 100);
				request.setAttribute("complete_degree1", complete_degree1);
				request.setAttribute("personal_Check", personal_Check);
			} else {
				request.setAttribute("complete_degree1", "0");
			}
			// 信息采集
			PHCSMP_Information_Collection information_Collection = informationCollectionService
					.findInforBySuspetcId(suspectId);

			if (information_Collection != null) {
				int complete_degree2 = (int) (information_Collection
						.getFill_record()
						/ (float) information_Collection.getTotal_record() * 100);
				request.setAttribute("complete_degree2", complete_degree2);
				request.setAttribute("information_Collection",
						information_Collection);
			} else {
				request.setAttribute("complete_degree2", "0");
			}
			// 询问讯问活动记录
			List<PHCSMP_Activity_Record> activity_record_infor = activityRecordService
					.findInforBySuspetcId(suspectId);
			if (activity_record_infor != null) {
				request.setAttribute("activity_record_infor",
						activity_record_infor);
			}

			// 设置询问询问开始的时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String start_Time = sdf.format(date);
			request.setAttribute("start_Time", start_Time);
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

			// request.setAttribute("suspect", 1);
			// if (personal_Check != null) {
			// request.setAttribute("personalCheck", 1);
			// }
			// if (information_Collection != null) {
			// request.setAttribute("informationCollection", 1);
			// }
			// request.setAttribute("activityRecord", activity_record_infor);
			// 维护进出门状态
			suspectService.updateSwitch(1, suspectId);
		} catch (Exception e) {
			response.getWriter()
					.write("<script type='text/javascript'>alert('当前房间存在多个嫌疑人，可能是上一个嫌疑人出门时未刷卡（请保证进门和出门时成对刷卡），也可能是房间信息不正确');</script>");
			response.getWriter().flush();

			// System.out
			// .println("当前房间存在多个嫌疑人，可能是上一个嫌疑人出门时未刷卡（请保证进门和出门时成对刷卡），也可能是房间信息不正确");
			return "success";
		}
		return "loadInfor";
	}

	// // 未登录状态时
	// public String unlogin_load() {
	// return "unlogin_load";
	// }

	// 返回修改活动记录信息
	// public String updateInfor() {
	// System.out.println("档案编号：" + request.getParameter("Suspect_ID"));
	// System.out.println("updateInfor：修改活动记录信息！");
	// return "updateInfor";
	// }

	@Override
	public void setServletContext(ServletContext application) {
		this.application = application;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
