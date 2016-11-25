package com.haifeiWu.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
import com.haifeiWu.utils.FTPClientUtils;
import com.haifeiWu.utils.PropertiesReadUtils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 生成嫌疑人信息报告的action
 * @author wuhaifei
 * @d2016年10月17日
 */
@Controller
@Scope("prototype")
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
	@Autowired
	private SuspectService suspectService;
	//嫌疑人随身物品登记信息
	@Autowired
	private BelongingInforService belongingInforService;
	//嫌疑人的人身检查信息
	@Autowired
	private PersonalCheckService personalCheckService;
	//询问讯问记录信息登记
	@Autowired
	private ActivityRecordService activityRecordService;
	//信息采集信息登记
	@Autowired
	private InformationCollectionService informationCollectionService;
	//嫌疑人出区信息登记
	@Autowired
	private LeaveRecodService leaveRecodService;
	
	/**
	 * 生成嫌疑人入区信息报告
	 * @return
	 */
	public String loadInfor(){
		System.out.println("嫌疑人入区信息报告");
		
		System.out.println("嫌疑人姓名："+request.getParameter("personName"));
		System.out.println("档案编号："+request.getParameter("suspectID"));
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
	/**
	 * 嫌疑人信息搜索
	 * @return
	 */
	public String searchsuspectInfor(){
		System.out.println("SuspectManageAction:searchsuspectInfor");
		return "searchsuspectInfor";
	}
	/**
	 * 嫌疑人入区视频文件文件下载
	 * @return
	 * @throws Exception
	 */
	public String downFile() throws Exception{
		FTPClientUtils ftp = new FTPClientUtils();
		ftp.setHost(PropertiesReadUtils.getString("remoteServerIP"));
		ftp.setPort(21);
		ftp.setBinaryTransfer(true);
		ftp.setPassiveMode(true);
		ftp.setEncoding("utf-8");
		String date = request.getParameter("date");
		String fileName = request.getParameter("fileName");
		System.out.println("入区时间："+date+"\n文件名："+fileName);
		
		String[] str = date.split("-");
		StringBuilder str1 = new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			str1.append(str[i]+"/");
			System.out.println(str1);
		}
		String downLoadFile = str1.toString()+fileName;
		boolean flag = ftp
				.get("/recordfiles/"+downLoadFile,"F://testDownLoad/"+fileName);
		System.out.println("下载目录：/recordfiles/"+downLoadFile);
		System.out.println("下载成功："+flag);
		return "downFile";
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
