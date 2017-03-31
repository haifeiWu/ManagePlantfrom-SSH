package com.haifeiWu.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

			// 入区登记
			int complete_degree = CompleteCheck.completeCheck(suspectInfor,
					suspectInfor.getClass(), 3);
			// // 人身检查
			// int complete_degree1 =
			// CompleteCheck.completeCheck(personal_Check,
			// personal_Check.getClass(), 3);
			// // 信息采集
			// int complete_degree2 = CompleteCheck.completeCheck(
			// information_Collection, information_Collection.getClass(),
			// 3);
			// 设置询问询问开始的时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String start_Time = sdf.format(date);

			request.setAttribute("complete_degree", complete_degree);
			// request.setAttribute("complete_degree1", complete_degree1);
			// request.setAttribute("complete_degree2", complete_degree2);

			request.setAttribute("personal_Check", personal_Check);
			request.setAttribute("SuspectInfor", suspectInfor);
			request.setAttribute("information_Collection",
					information_Collection);
			request.setAttribute("start_Time", start_Time);

			// 如果提交失败，需将用户提交的信息显示在页面上
			// String activity_remark = (String) request
			// .getAttribute("activity_remark");
			// String activity_Record = (String) request
			// .getAttribute("activity_Record");
			// // 将提交失败的已输入信息显示在文本框处
			// request.setAttribute("activity_remark", activity_remark);
			// request.setAttribute("activity_Record", activity_Record);

			// 更新录像标志位
			suspectService.updateSwitch(1, suspectInfor.getSuspect_ID());

		} catch (java.lang.Exception e) {
			// response.getWriter()
			// .write("<script type='text/javascript'>alert('当前房间存在多个嫌疑人，可能是上一个嫌疑人出门时未刷卡（请保证进门和出门时成对刷卡），也可能是房间信息不正确');</script>");
			// response.getWriter().flush();
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
