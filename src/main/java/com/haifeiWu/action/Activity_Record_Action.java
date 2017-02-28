package com.haifeiWu.action;

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
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.InformationCollectionService;
import com.haifeiWu.service.PersonalCheckService;
import com.haifeiWu.service.SuspectService;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

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
	private List<PHCSMP_Activity_Record> activity = new ArrayList<PHCSMP_Activity_Record>();

	private String suspect_ID;

	/**
	 * 添加活动记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addActivityRecordInfor() throws java.lang.Exception {

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
		//
		// // ActivityRecordService service = new ActivityRecordServiceImple();
		// //保存活动记录信息
		// // service.saveActivityRecordInfor(model);

		List<PHCSMP_Activity_Record> activitys = this.getActivity();
		suspect_ID = this.getSuspect_ID();
		if (activitys == null) {// 当获取的数据为空时
			return "NULL";
		}

		for (PHCSMP_Activity_Record activity : activitys) {// 遍历list
			activity.setSuspect_ID(suspect_ID);
			System.out.println(activity.toString());
		}

		activityRecordService.saveActivitysInfor(activitys);
		return "addActivityRecordInfor";
	}

	/**
	 * 加载活动记录信息
	 * 
	 * @return
	 */
	public String loadInfor() {
		PHCSMP_Suspect SuspectInfor = suspectService.findInfroByActiveCode(1);

		String suspectId = SuspectInfor.getSuspect_ID();

		PHCSMP_Personal_Check personal_Check = personalCheckService
				.findInforBySuspetcId(suspectId);

		PHCSMP_Information_Collection information_Collection = informationCollectionService
				.findInforBySuspetcId(suspectId);
		//这是之前的版本，必须完成之前的业务才能进行询问/讯问，此处需要修改
		if ((SuspectInfor == null) || (personal_Check == null)
				|| (information_Collection == null)) {
			return "NULL";
		}
		// 将信息从数据库查找到之后，存入session，更新session
		request.setAttribute("SuspectInfor", SuspectInfor);
		request.setAttribute("personal_Check", personal_Check);
		request.setAttribute("information_Collection", information_Collection);

		PHCSMP_Staff user = (PHCSMP_Staff) request.getSession().getAttribute(
				"user");
		if (user == null) {
			return "unLoginState";
		} else {
			System.out.println("Activity_Record_Action:loadInfor");
			return "loadInfor";
		}
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

	public List<PHCSMP_Activity_Record> getActivity() {
		return activity;
	}

	public void setActivity(List<PHCSMP_Activity_Record> activity) {
		this.activity = activity;
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

	public String getSuspect_ID() {
		return suspect_ID;
	}

	public void setSuspect_ID(String suspect_ID) {
		this.suspect_ID = suspect_ID;
	}

}
