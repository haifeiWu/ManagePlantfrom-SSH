package com.haifeiWu.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.entity.PHCSMP_BelongingS;
import com.haifeiWu.entity.PHCSMP_Information_Collection;
import com.haifeiWu.entity.PHCSMP_Leave_Record;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.BelongingInforService;
import com.haifeiWu.service.InformationCollectionService;
import com.haifeiWu.service.LeaveRecodService;
import com.haifeiWu.service.PersonalCheckService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.serviceImple.ActivityRecordServiceImple;
import com.haifeiWu.serviceImple.BelongingInforServiceImple;
import com.haifeiWu.serviceImple.InformationCollectionServiceImple;
import com.haifeiWu.serviceImple.LeaveRecodServiceImple;
import com.haifeiWu.serviceImple.PersonalCheckServiceImple;
import com.haifeiWu.serviceImple.SuspectServiceImple;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 嫌疑人信息管理action，待查嫌疑人信息，历史嫌疑人信息
 * @author wuhaifei
 * @d2016年10月17日
 */
@Controller
@Scope("prototype")
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
