package com.manageplantfrom.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.manageplantfrom.base.BaseAction;
import com.manageplantfrom.entity.PHCSMP_Activity_Record;
import com.manageplantfrom.entity.PHCSMP_BelongingS;
import com.manageplantfrom.entity.PHCSMP_Information_Collection;
import com.manageplantfrom.entity.PHCSMP_Leave_Record;
import com.manageplantfrom.entity.PHCSMP_Personal_Check;
import com.manageplantfrom.entity.PHCSMP_Suspect;
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
import com.opensymphony.xwork2.ModelDriven;

/**
 * 嫌疑人信息管理action，待查嫌疑人信息，历史嫌疑人信息
 * @author wuhaifei
 * @d2016年10月17日
 */

public class SuspectManageAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware,ServletContextAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;

	
	public String loadInfor(){
		System.out.println("历史记录，待办信息");
		return "loadInfor";
	}
	
	public String suspectInforSummary(){
		
		System.out.println("SuspectManageAction:suspectInforSummary");
		return "suspectInforSummary";
	}
	public String searchsuspectInfor(){
		/*
		 *根据姓名与档案编号查找信息 
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
