package com.haifeiWu.action;

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

import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.SuspectService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 嫌疑人信息管理action，待查嫌疑人信息，历史嫌疑人信息
 * 
 * @author wuhaifei
 * @d2016年10月17日
 */
@Controller
@Scope("prototype")
public class SuspectManageAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;

	@Autowired
	private SuspectService suspectService;// 嫌疑人信息管理

	/**
	 * 加载嫌疑人信息
	 * 
	 * @return
	 */
	public String loadInfor() {
		System.out.println("历史记录，待办信息");
		// 获取待查嫌疑人信息
		List<PHCSMP_Suspect> suspectCheckInfor = suspectService
				.getCheckingSuspect(0);
		// 获取出区嫌疑人数据
		List<PHCSMP_Suspect> suspectCheckedInfor = suspectService
				.getCheckingSuspect(1);
		// 将信息放入到request中
		request.setAttribute("suspectCheckInfor", suspectCheckInfor);
		request.setAttribute("suspectCheckedInfor", suspectCheckedInfor);
		for (PHCSMP_Suspect phcsmp_Suspect : suspectCheckInfor) {
			System.err.println(phcsmp_Suspect.toString());
		}
		return "loadInfor";
	}

	/**
	 * 根据姓名或者档案编号查找嫌疑人信息
	 * 
	 * @return
	 */
	public String searchsuspectInfor() {
		String str = request.getParameter("searchInfor");
		System.out.println("查询条件：" + str);
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
