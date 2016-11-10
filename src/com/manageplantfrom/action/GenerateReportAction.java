package com.manageplantfrom.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.manageplantfrom.service.ActivityRecordService;
import com.manageplantfrom.service.BelongingInforService;
import com.manageplantfrom.service.InformationCollectionService;
import com.manageplantfrom.service.LeaveRecodService;
import com.manageplantfrom.service.PersonalCheckService;
import com.manageplantfrom.service.SuspectService;
import com.manageplantfrom.serviceImple.ActivityRecordServiceImple;
import com.manageplantfrom.serviceImple.BelongingInforServiceImple;
import com.manageplantfrom.serviceImple.InformationCollectionServiceImple;
import com.manageplantfrom.serviceImple.LeaveRecodServiceImple;
import com.manageplantfrom.serviceImple.PersonalCheckServiceImple;
import com.manageplantfrom.serviceImple.SuspectServiceImple;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 生成嫌疑人信息报告的action
 * @author wuhaifei
 * @d2016年10月17日
 */

public class GenerateReportAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware,ServletContextAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;

	//嫌疑人的入区登记信息
	private SuspectService suspectService = new SuspectServiceImple();
	//嫌疑人随身物品登记信息
	private BelongingInforService inforService = new BelongingInforServiceImple();
	//嫌疑人的人身检查信息
	private PersonalCheckService checkService = new PersonalCheckServiceImple();
	//询问讯问记录信息登记
	private ActivityRecordService activityRecordService = new ActivityRecordServiceImple();
	//信息采集信息登记
	private InformationCollectionService collectionService = new InformationCollectionServiceImple();
	//嫌疑人出区信息登记
	private LeaveRecodService leaveRecodService = new LeaveRecodServiceImple();
	
	
	public String loadInfor(){
		System.out.println("嫌疑人入区信息报告");
		return "loadInfor";
	}
	
	public String suspectInforSummary(){
		/*
		 * 加载当前嫌疑人的所有的信息
		 */
//		//获取档案编号
//		String suspectId = (String) request.getAttribute("suspectId");
//		//查找嫌疑人入区信息
//		PHCSMP_Suspect suspect = suspectService.findInforBySuspetcId(suspectId);
//		//嫌疑人随身物品检查信息
//		PHCSMP_BelongingS belongingS = inforService.findInforBySuspetcId(suspectId);
//		//嫌疑人人身检查信息
//		PHCSMP_Personal_Check personal_Check = checkService.findInforBySuspetcId(suspectId);
//		//嫌疑人询问讯问记录信息
//		PHCSMP_Activity_Record activity_Record = activityRecordService.findInforBySuspetcId(suspectId);
//		//嫌疑人信息采集记录
//		PHCSMP_Information_Collection information_Collection = collectionService.findInforBySuspetcId(suspectId);
//		//嫌疑人出区信息记录
//		PHCSMP_Leave_Record leave_Record = leaveRecodService.findInforBySuspetcId(suspectId);
//		
//		//将查找到的信息放入request中，然后从页面加载
//		request.setAttribute("suspect",suspect );
//		request.setAttribute("belongingS",belongingS );
//		request.setAttribute("personal_Check",personal_Check );
//		request.setAttribute("activity_Record",activity_Record );
//		request.setAttribute("information_Collection",information_Collection );
//		request.setAttribute("leave_Record",leave_Record );
		
		System.out.println("SuspectManageAction:suspectInforSummary");
		return "suspectInforSummary";
	}
	public String searchsuspectInfor(){
		/*
		 *根据 
		 */
		System.out.println("SuspectManageAction:searchsuspectInfor");
		return "searchsuspectInfor";
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
