package com.haifeiWu.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.haifeiWu.entity.PHCSMP_LogInfo;
import com.haifeiWu.entity.PHCSMP_Process_Log;
import com.haifeiWu.service.LogService;
import com.haifeiWu.utils.PageBean;

@Controller
@RequestMapping("/log")
@Scope("prototype")
public class Log_Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private LogService logService;

	/**
	 * 对loginfor的分页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/executeinfo")
	public String executeinfo(HttpServletRequest request ) throws Exception {
		int page =Integer.parseInt( request.getParameter("page"));
	 // 表示每页显示5条记录，page表示当前网页
	 PageBean pageBean = logService.getInfoPageBean(10, page);
	
	
	 request.setAttribute("pageBean", pageBean);
	
	 return "WEB-INF/jsp/log/logInfor";
	 }
	
	/**
	 * 对processLog的分页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/executeProcess")
	public String executeProcess(HttpServletRequest request) throws Exception {
		int page =Integer.parseInt( request.getParameter("page"));
		System.out.println(request.getParameter("page")+"========================page=============");

	 // 表示每页显示5条记录，page表示当前网页
	 PageBean pageBean = logService.getProcessPageBean(10, page);
	
	
	 request.setAttribute("pageBean", pageBean);
	
	 return "WEB-INF/jsp/log/process_log";
	 }
	
	/**
	 * 加载loginfor页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/logInfo")
	public String logInfo(HttpServletRequest request,HttpServletResponse response){
		List<PHCSMP_LogInfo> staffLogList =logService.findAllStaffLog();
		//List<String> logList=toString(staffLogList);
		request.setAttribute("staffLogList", staffLogList);
		return null;
		
	}
	
	/**
	 * 加载processLog页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/processLog")
	public String processLog(HttpServletRequest request,HttpServletResponse response){
		List<PHCSMP_Process_Log> processLogList =logService.findAllProcessLog();
		//List<String> logList=toString(processLogList);
		request.setAttribute("processLogList", processLogList);
		return null;
		
	}
	
	
	/**
	 * 通过staffName查找loginfor记录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/loginfosearchs")
	public String serchLoginforBystaffName(HttpServletRequest request,HttpServletResponse response){
		String staffName=request.getParameter("staffName");
		int page =Integer.parseInt( request.getParameter("page"));
		String hql = "from PHCSMP_LogInfo where Staff_Name=? order by Log_ID desc";
		 // 表示每页显示5条记录，page表示当前网页
		 PageBean pageBean = logService.findLoginfor(staffName, 10, page,hql);
		
		
		 request.setAttribute("pageBean", pageBean);
		
		 return "WEB-INF/jsp/log/logInforsearchs";
	}
	
	/**
	 * 通过时间查找记录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/loginfosearchd")
	public String serchLoginforByDate(HttpServletRequest request,HttpServletResponse response){
		String year=request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String date = year+"-"+month+"-"+day;
		int page =Integer.parseInt( request.getParameter("page"));
		String hql = "from PHCSMP_LogInfo where date=? order by Log_ID desc";
		 // 表示每页显示5条记录，page表示当前网页
		 PageBean pageBean = logService.findLoginfor(date, 10, page,hql);
		
		
		 request.setAttribute("pageBean", pageBean);
		
		 return "WEB-INF/jsp/log/logInforsearchd";
	}
	
	
	
	
	/**
	 * 通过suspect_ID查找loginfor记录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/logprocesssearchs")
	public String serchLogprocessBysuspect_ID(HttpServletRequest request,HttpServletResponse response){
		System.out.println(request.getParameter("page")+"========================page=============");
		String suspect_Id=request.getParameter("suspect_Id");
		System.out.println(suspect_Id+"+++++++++++++++++++supectid");
		int page =Integer.parseInt( request.getParameter("page"));
		
		String hql = "from PHCSMP_Process_Log as plog where plog.suspect_ID=? order by Log_ID desc";
		 // 表示每页显示5条记录，page表示当前网页
		 PageBean pageBean = logService.findLogprocess(suspect_Id,10, page,hql);
		
		System.out.println(pageBean.getProcessLogList()+"==========list");
		 request.setAttribute("pageBean", pageBean);
		 System.out.println(pageBean.getAllRows()+"==========list");
		 System.out.println(pageBean.getTotalPage()+"==========list");
		 System.out.println(pageBean.getCurrentPage()+"==========list");
		 return "WEB-INF/jsp/log/process_logsearchs";
	}
	
	/**
	 * 通过时间查找记录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/logprocesssearchd")
	public String serchLogprocessByDate(HttpServletRequest request,HttpServletResponse response){
		String year=request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String date = year+"-"+month+"-"+day;
		int page =Integer.parseInt( request.getParameter("page"));
		String hql = "from PHCSMP_Process_Log where date=? order by Log_ID desc";
		 // 表示每页显示5条记录，page表示当前网页
		 PageBean pageBean = logService.findLogprocess(date, 10, page,hql);
		
		
		 request.setAttribute("pageBean", pageBean);
		
		 return "WEB-INF/jsp/log/process_logsearchd";
	}
	

}
