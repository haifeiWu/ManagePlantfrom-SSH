package com.manageplantfrom.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.manageplantfrom.base.BaseAction;
import com.manageplantfrom.entity.PHCSMP_Activity_Record;
import com.manageplantfrom.entity.PHCSMP_Information_Collection;
import com.manageplantfrom.entity.PHCSMP_Suspect;
import com.manageplantfrom.service.ActivityRecordService;
import com.manageplantfrom.service.SuspectService;
import com.manageplantfrom.serviceImple.ActivityRecordServiceImple;
import com.manageplantfrom.serviceImple.SuspectServiceImple;
import com.manageplantfrom.utils.CompleteCheck;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 活动记录
 * @author wuhaifei
 * @d2016年9月28日
 */
public class Activity_Record_Action extends ActionSupport implements ServletRequestAware,
ServletResponseAware,ServletContextAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;
	//活动记录实例类
	private ActivityRecordService service = new ActivityRecordServiceImple();
	//嫌疑人信息
	private SuspectService suspectService = new SuspectServiceImple();
	//信息人活动信息
	private ActivityRecordService recordService = new ActivityRecordServiceImple();
	//活动记录表list
	private List<PHCSMP_Activity_Record> activity = new ArrayList<PHCSMP_Activity_Record>();
	
	/**
	 * 添加活动记录信息
	 * @return
	 * @throws Exception
	 */
	public String addActivityRecordInfor() throws Exception{
		
		//通过反射加载身物品检查记录的类
//		Class<?> c = Class.forName(PHCSMP_Activity_Record.class.getName());
//		//统计未填写的字段
//		int count = CompleteCheck.IsEqualsNull(model, c);
//		//统计所有的字段
//		int fieldsNumber = CompleteCheck.getFieldsNumber(model, c);
//		
//		model.setFill_record(fieldsNumber-count-3);//设置已填写的字段数
//		model.setTotal_record(fieldsNumber-3);//设置应填写的字段
//		System.out.println("未填写的字段："+count);
//		System.out.println("总字段："+(fieldsNumber-3));
//		
////		ActivityRecordService service = new ActivityRecordServiceImple();
//		//保存活动记录信息
////		service.saveActivityRecordInfor(model);
		
		List<PHCSMP_Activity_Record> activitys = this.getActivity();
		
		recordService.saveActivitysInfor(activitys);
		
		for (PHCSMP_Activity_Record activity : activitys) {//遍历list
			System.out.println(activity.toString());
		}
		
		System.out.println("Activity_Record_Action:addActivityRecordInfor");
		return "addActivityRecordInfor";
	}
	
	/**
	 * 加载活动记录信息
	 * @return
	 */
	public String loadInfor(){
//		
//		SuspectService suspectService = new SuspectServiceImple();
//		System.out.println("wuhaifei");
//		PHCSMP_Suspect SuspectInfor =  suspectService.findInfroByActiveCode(5);
////		//将信息从数据库查找到之后，存入session，更新session
//		request.setAttribute("SuspectInfor", SuspectInfor);
		System.out.println("Activity_Record_Action:loadInfor");
		return "loadInfor";
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

}
