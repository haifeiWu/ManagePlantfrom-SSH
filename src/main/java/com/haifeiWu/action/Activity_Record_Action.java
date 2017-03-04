package com.haifeiWu.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.entity.PHCSMP_Room;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.SuspectService;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

/**
 * 活动记录
 * 
 * @author wuhaifei
 * @d2016年9月28日
 */
@Controller
@Scope("prototype")
public class Activity_Record_Action extends BaseAction<PHCSMP_Activity_Record> {
	// extends ActionSupport implements
	// ServletRequestAware, ServletResponseAware, ServletContextAware
	private static final long serialVersionUID = 1201107017949225716L;
	// protected HttpServletRequest request;
	// protected HttpServletResponse response;
	// protected ServletContext application;

	@Autowired
	private RoomService roomService;
	@Autowired
	private SuspectService suspectService;
	@Autowired
	private ActivityRecordService activityRecordService;

	// 活动记录表list，用于前台提交的多个数据
	private List<PHCSMP_Activity_Record> activity = new ArrayList<PHCSMP_Activity_Record>();

	/**
	 * 加载活动记录信息
	 */
	public String loadInfor() {
		PHCSMP_Room room = roomService.findbyIp(request.getRemoteAddr());
		PHCSMP_Suspect SuspectInfor = suspectService.findByRoomID(room
				.getRoom_ID());

		String suspectId = SuspectInfor.getSuspect_ID();

		request.setAttribute("SuspectInfor", SuspectInfor);

		PHCSMP_Staff user = (PHCSMP_Staff) request.getSession().getAttribute(
				"user");
		if (user == null) {
			return "unLoginState";
		}
		return "loadInfor";
	}

	//
	// private String suspect_ID;
	/**
	 * 添加活动记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addActivityRecordInfor() throws java.lang.Exception {

		// for (PHCSMP_Activity_Record a : activity) {// 遍历list
		// // a.setSuspect_ID();
		// // System.out.println(activitya.toString());
		// }
		activityRecordService.saveActivityRecordInfor(model);
		return "addActivityRecordInfor";
	}

	// 未登录状态时
	public String unlogin_load() {
		return "unlogin_load";
	}

	// 返回修改活动记录信息
	public String updateInfor() {
		System.out.println("档案编号：" + request.getParameter("Suspect_ID"));
		System.out.println("updateInfor：修改活动记录信息！");
		return "updateInfor";
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
	// public List<PHCSMP_Activity_Record> getActivity() {
	// return activity;
	// }
	//
	// public void setActivity(List<PHCSMP_Activity_Record> activity) {
	// this.activity = activity;
	// }
	// // 活动记录实例类
	// @Autowired
	// private ActivityRecordService activityRecordService;
	// // 嫌疑人信息
	// @Autowired
	// private SuspectService suspectService;
	// // 嫌疑人的人身检查信息
	// @Autowired
	// private PersonalCheckService personalCheckService;
	// // 信息采集信息登记
	// @Autowired
	// private InformationCollectionService informationCollectionService;

	// //通过反射加载身物品检查记录的类
	// Class<?> c = Class.forName(PHCSMP_Activity_Record.class.getName());
	// //统计未填写的字段
	// int count = CompleteCheck.IsEqualsNull(model, c);
	// //统计所有的字段
	// int fieldsNumber = CompleteCheck.getFieldsNumber(model, c);
	//
	// model.setFill_record(fieldsNumber-count-3);//设置已填写的字段数
	// model.setTotal_record(fieldsNumber-3);//设置应填写的字段
	// System.out.println("未填写的字段："+count);
	// System.out.println("总字段："+(fieldsNumber-3));

	// // 查询人身检查
	// PHCSMP_Personal_Check personal_Check = personalCheckService
	// .findInforBySuspetcId(suspectId);
	// // 查询信息采集
	// PHCSMP_Information_Collection information_Collection =
	// informationCollectionService
	// .findInforBySuspetcId(suspectId);
	// // 如果不为空，就存
	// 将信息从数据库查找到之后，存入session，更新session
	// request.setAttribute("personal_Check", personal_Check);
	// request.setAttribute("information_Collection",
	// information_Collection);

	//
	// // ActivityRecordService service = new ActivityRecordServiceImple();
	// //保存活动记录信息
	// // service.saveActivityRecordInfor(model);
	//
	// List<PHCSMP_Activity_Record> activitys = this.getActivity();
	// suspect_ID = this.getSuspect_ID();
	// if (activitys == null) {// 当获取的数据为空时
	// return "NULL";
	// }
	//
}
