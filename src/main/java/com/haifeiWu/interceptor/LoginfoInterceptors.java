package com.haifeiWu.interceptor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.haifeiWu.entity.PHCSMP_LogInfo;
import com.haifeiWu.service.LogService;

public class LoginfoInterceptors implements HandlerInterceptor {

	@Autowired
	private LogService loginfoService;

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式

	/**
	 *      * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。
	 *      * 该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行， 这个方法的主要作用是用于清理资源的，
	 *      
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("拦截结束" + arg2.toString());

	}

	/**
	 *      * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。
	 *      * postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之后， 也就是在Controller的方法调用之后执行，
	 *      * 但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操作。
	 *      * 这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，
	 *      * 这跟Struts2里面的拦截器的执行过程有点像，
	 *      * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，
	 *      * Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor或者是调用action，
	 *      * 然后要在Interceptor之前调用的内容都写在调用invoke之前，
	 * 要在Interceptor之后调用的内容都写在调用invoke方法之后。      
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

		String arg = arg2.toString();
		if(arg.indexOf("[")==-1){
			arg = arg.substring(0,arg.indexOf("("));
			arg = arg.substring(arg.lastIndexOf(".")+1, arg.length());
		}
		System.out.println("进入拦截器"+arg);
		String username = arg0.getParameter("Staff_Name");
		System.out.println(username+"===========================");
		PHCSMP_LogInfo log = judgeRuquest(arg, username, arg0,arg1);
		System.out.println(log.getOperation_Time());
		if(log.getOperation_Time() != null && log.getOperation_Time()!="" ){
			saveLogInfo(log);
		}
		System.out.println("进入中间"+arg2.toString());
	}

	/**
	 *      * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
	 *      * SpringMVC中的Interceptor拦截器是链式的，可以同时存在多个Interceptor，
	 *      * 然后SpringMVC会根据声明的前后顺序一个接一个的执行，
	 *      * 而且所有的Interceptor中的preHandle方法都会在Controller方法调用之前调用。
	 *      * SpringMVC的这种Interceptor链式结构也是可以进行中断的，
	 *      * 这种中断方式是令preHandle的返回值为false，当preHandle的返回值为false的时候整个请求就结束了。      
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {

		String arg = arg2.toString();
		if (arg.indexOf("[") == -1) {
			arg = arg.substring(0, arg.indexOf("("));
			arg = arg.substring(arg.lastIndexOf(".") + 1, arg.length());
		}
		System.out.println("进入拦截器" + arg);
		String username = arg0.getParameter("Staff_Name");
		System.out.println(username + "===========================");
		PHCSMP_LogInfo log = judgeRuquest(arg, username, arg0);
		System.out.println(log.getOperation_Time());
		if (log.getOperation_Time() != null && log.getOperation_Time() != "") {
			saveLogInfo(log);
		}

		return true;
	}

	/**
	 * 判断拦截到的方法，并封装loginfo的相应参数
	 * 
	 * @param method
	 * @param processLog
	 * @return
	 * @throws IOException 
	 */

   private PHCSMP_LogInfo  judgeRuquest(String arg ,String username,HttpServletRequest request,HttpServletResponse response) throws IOException{
	   PHCSMP_LogInfo pHCSMP_LogInfo = new PHCSMP_LogInfo(); 
	   switch (arg){
	   case "searchsuspectInfor"://查询嫌疑人信息
	   {
//		   pHCSMP_LogInfo.setStaff_Name(request.getSession().getAttribute("staffname").toString());
		   String searchInfor = request.getParameter("searchInfor");
		   pHCSMP_LogInfo.setOperation_Model("入区人员信息汇总模块");
		   pHCSMP_LogInfo.setOperation_Info("通过 "+searchInfor+" 查询嫌疑人信息");
		   pHCSMP_LogInfo.setOperation_Time(df.format(new Date()));
		   pHCSMP_LogInfo.setDate(sf.format(new Date()));
		   break;
	   }
	   case "SM_executee" :{
//		   pHCSMP_LogInfo.setStaff_Name(request.getSession().getAttribute("staffname").toString());
		   pHCSMP_LogInfo.setOperation_Time(df.format(new Date()));
		   pHCSMP_LogInfo.setDate(sf.format(new Date()));
		   pHCSMP_LogInfo.setOperation_Model("入区人员信息汇总模块");
		   pHCSMP_LogInfo.setOperation_Info("查看历史嫌疑人信息"); 
		   break;
	   }
	   case "SM_loadInfor" :{
//		   pHCSMP_LogInfo.setStaff_Name((String)request.getSession().getAttribute("staffname"));
		   pHCSMP_LogInfo.setOperation_Model("入区人员信息汇总模块");
		   pHCSMP_LogInfo.setOperation_Info("进入入区人员入区汇总");
		   pHCSMP_LogInfo.setOperation_Time(df.format(new Date()));
		   pHCSMP_LogInfo.setDate(sf.format(new Date()));
		   break;
	   }
	   case "downLoadByHands"://下载录像
	   {
//		   pHCSMP_LogInfo.setStaff_Name(request.getSession().getAttribute("staffname").toString());
		   String suspect_ID =request.getParameter("suspect_ID"); 
		   pHCSMP_LogInfo.setOperation_Model("录像下载模块");
		   pHCSMP_LogInfo.setOperation_Info("手动下载"+suspect_ID+"录像信息");
		   pHCSMP_LogInfo.setOperation_Time(df.format(new Date()));
		   pHCSMP_LogInfo.setDate(sf.format(new Date()));
		   break;
	   }
	   
	   case "RG_loadInfor":
	   {
//		   pHCSMP_LogInfo.setStaff_Name(request.getSession().getAttribute("staffname").toString());
		   String suspect_ID =request.getParameter("suspectID"); 
		   pHCSMP_LogInfo.setOperation_Model("临时报告区模块");
		   pHCSMP_LogInfo.setOperation_Info("查看档案号 "+suspect_ID+" 入区报告");
		   pHCSMP_LogInfo.setOperation_Time(df.format(new Date()));
		   pHCSMP_LogInfo.setDate(sf.format(new Date()));
		   break;
	   }
	   
	   case "updateRoom":{
//		   pHCSMP_LogInfo.setStaff_Name(request.getSession().getAttribute("staffname").toString());
		   pHCSMP_LogInfo.setOperation_Time(df.format(new Date()));
		   pHCSMP_LogInfo.setDate(sf.format(new Date()));
		   pHCSMP_LogInfo.setOperation_Model("房间管理");
		   pHCSMP_LogInfo.setOperation_Info("进行房间参数修改"); 
		   
		   
		   break;
	   }
	   
	   case "updateCardReader"://读卡器初始化
	   {
//		   pHCSMP_LogInfo.setStaff_Name(request.getSession().getAttribute("staffname").toString());
		   pHCSMP_LogInfo.setOperation_Time(df.format(new Date()));
		   pHCSMP_LogInfo.setDate(sf.format(new Date()));
		   pHCSMP_LogInfo.setOperation_Model("读卡器设置");
		   pHCSMP_LogInfo.setOperation_Info("进行读卡器参数修改"); 
		   break;
	   }
	   
	   case "updateBand":{
//		   pHCSMP_LogInfo.setStaff_Name(request.getSession().getAttribute("staffname").toString());
		   pHCSMP_LogInfo.setOperation_Time(df.format(new Date()));
		   pHCSMP_LogInfo.setDate(sf.format(new Date()));
		   pHCSMP_LogInfo.setOperation_Model("手环设置");
		   pHCSMP_LogInfo.setOperation_Info("进行手环参数修改"); 
		   
		   break;
	   }
	  
	   }
	 
		   
	return pHCSMP_LogInfo;
	   
   }
   
   
   
   /**

	 * 保存loginfo到数据库
	 * 
	 * @param request
	 * @param loginfo
	 */
	private void saveLogInfo(PHCSMP_LogInfo loginfo) {
		loginfoService.save(loginfo);
	}
}
