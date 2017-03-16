package com.haifeiWu.action;

import java.io.IOException;
import java.util.ArrayList;
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
	// 信息采集信息登记
	@Autowired
	private InformationCollectionService informationCollectionService;
	// 活动记录表list，用于前台提交的多个数据
	private List<PHCSMP_Activity_Record> activitys = new ArrayList<PHCSMP_Activity_Record>();

	// private String suspect_ID;

	/**
	 * 添加活动记录信息
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public String addActivityRecordInfor() throws IOException {
		try {
			// 维护进出门的标志位
			int roomId = roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID();
			String suspectId = suspectService.findByRoomID(roomId)
					.getSuspect_ID();
			List<PHCSMP_Activity_Record> validActivitys = new ArrayList<PHCSMP_Activity_Record>();
			for (PHCSMP_Activity_Record activity : activitys) {// 遍历list
				if (!(activity.getStart_Time().equals("") || activity
						.getStart_Time() == null)) {
					activity.setSuspect_ID(suspectId);
					activity.setRoom_ID(1);
					fullCheck(activity);

					validActivitys.add(activity);
				}
				System.out.println("--------------------------"
						+ activity.toString() + "---------------");
			}
			activityRecordService.saveActivitysInfor(validActivitys);
			// 提示成功
			response.getWriter().write("<script>alert('后台提交成功');</script>");
			response.getWriter().flush();
			return "success";
		} catch (Exception e) {
			response.getWriter().write("<script>alert('提交失败，请重新提交');</script>");
			response.getWriter().flush();
			return "loadInfor";
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
	 */
	public String loadInfor() throws IOException {
		try {
			// 维护进出门的标志位
			int roomId = roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID();
			PHCSMP_Suspect suspectInfor = suspectService.findByRoomID(roomId);
			PHCSMP_Personal_Check personal_Check = personalCheckService
					.findInforBySuspetcId(suspectInfor.getSuspect_ID());
			PHCSMP_Information_Collection information_Collection = informationCollectionService
					.findInforBySuspetcId(suspectInfor.getSuspect_ID());

			request.setAttribute("personal_Check", personal_Check);
			request.setAttribute("SuspectInfor", suspectInfor);
			request.setAttribute("information_Collection",
					information_Collection);

			suspectService.updateSwitch(1, suspectInfor.getSuspect_ID());
		} catch (java.lang.Exception e) {
			response.getWriter()
					.write("<script>alert('当前房间存在多个嫌疑人，可能是上一个嫌疑人出门时未刷卡（请保证进门和出门时成对刷卡），也可能是房间信息不正确');</script>");
			response.getWriter().flush();
			// System.out
			// .println("当前房间存在多个嫌疑人，可能是上一个嫌疑人出门时未刷卡（请保证进门和出门时成对刷卡），也可能是房间信息不正确");
		}
		return "loadInfor";
	}

	// // 未登录状态时
	// public String unlogin_load() {
	// return "unlogin_load";
	// }

	// 返回修改活动记录信息
	public String updateInfor() {
		System.out.println("档案编号：" + request.getParameter("Suspect_ID"));
		System.out.println("updateInfor：修改活动记录信息！");
		return "updateInfor";
	}

	public List<PHCSMP_Activity_Record> getActivity() {
		return activitys;
	}

	public void setActivity(List<PHCSMP_Activity_Record> activitys) {
		this.activitys = activitys;
	}

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

	// public String getSuspect_ID() {
	// return suspect_ID;
	// }
	//
	// public void setSuspect_ID(String suspect_ID) {
	// this.suspect_ID = suspect_ID;
	// }

}
