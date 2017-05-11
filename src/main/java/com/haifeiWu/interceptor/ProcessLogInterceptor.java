package com.haifeiWu.interceptor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.haifeiWu.entity.PHCSMP_Process_Log;
import com.haifeiWu.entity.PHCSMP_Room;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.LogService;

public class ProcessLogInterceptor implements HandlerInterceptor {

	@Autowired
	private LogService loginfoService;

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		String arg = arg2.toString();
		if (arg.indexOf("[") == -1) {
			arg = arg.substring(0, arg.indexOf("("));
			arg = arg.substring(arg.lastIndexOf(".") + 1, arg.length());
		}
		if(arg == "addSuspectInfor"){
			checkWay(arg0, arg1);
		}

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// 获取时间，设置格式
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sdate = sf.format(date);
		String ddate = df.format(date);
		// 获取拦截到的方法
		String arg = arg2.toString();
		if (arg.indexOf("[") == -1) {
			arg = arg.substring(0, arg.indexOf("("));
			arg = arg.substring(arg.lastIndexOf(".") + 1, arg.length());
		}
		System.out.println(arg + "---------================");
		// 获取日志实例
		// PHCSMP_Process_Log processLog = new PHCSMP_Process_Log();
		// 判断拦截的方法,根据方法封装好processLog
		PHCSMP_Process_Log process = jugeMethod(arg, sdate, ddate, arg0, arg1);
		System.out.println(process.getEnd_Time() + "============endtime");
		if (process.getEnd_Time() != null && process.getEnd_Time() != ""
				&& process.getEnd_Time() == "0-0") {
			saveprocess(process);
		}
		if (process.getEnd_Time() != "0-0") {

			updateprocess(process);
		}
		if (process.getStaff_ID() != 0) {
			updateporStaff(process);
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	//
	// /**
	// * 判断拦截到的方法，并封装processLog的相应参数
	// *
	// * @param method
	// * @param processLog
	// * @return
	// */
	private PHCSMP_Process_Log jugeMethod(String method, String sdate,
			String ddate, HttpServletRequest arg0, HttpServletResponse response)
			throws IOException {
		PHCSMP_Process_Log log = new PHCSMP_Process_Log();
		
		switch (method) {
		case "readRFID": {
			log = getProcessLog(arg0, sdate, ddate);
			int process = log.getProcess_ID();
			switch (process) {
			case 1: {
				log.setSuspect_active("进行人身安全检查"); // 封装嫌疑人活动
				break;
			}
			case 2: {
				log.setSuspect_active("进行信息采集");
				break;
			}
			case 3: {
				log.setSuspect_active("进行询问讯问");
				break;
			}
			case 4: {
				log.setSuspect_active("侯问时间");
				break;
			}
			case 5: {
				log.setSuspect_active("离区");
				break;
			}
			}
			break;
		}
		// 人身安全檢查提交
		case "addCheckPersonInfor": {
			if (staffIsNull() != null) {
				log = loginfoService.searchEmpstaff();
				log.setStaff_ID(Integer.parseInt((String) arg0.getAttribute("staff_Name")));
			}
			String error = (String) arg0.getAttribute("error");
			if (error == "error") {
				response.getWriter().write(
						"<script>alert('提交失败，请重新提交');</script>");
				response.getWriter().flush();

			}
			break;
		}
		// 信息采集提交
		case "addInformationCollection": {
			if (staffIsNull() != null) {
				log = loginfoService.searchEmpstaff();
				log.setStaff_ID(Integer.parseInt((String) arg0.getAttribute("staff_ID")));
			}
			String error = (String) arg0.getAttribute("error");
			if (error == "error") {
				response.getWriter().write(
						"<script>alert('提交失败，请重新提交');</script>");
				response.getWriter().flush();

			}
			break;
		}
		// 询问讯问提交
		case "addActivityRecordInfor": {
			if (staffIsNull() != null) {
				log = loginfoService.searchEmpstaff();
				log.setStaff_ID(Integer.parseInt((String) arg0.getAttribute("staff_ID")));
			}
			String error = (String) arg0.getAttribute("error");
			if (error == "error") {
				response.getWriter().write(
						"<script>alert('提交失败，请重新提交');</script>");
				response.getWriter().flush();

			}
			break;
		}
		// 离区提交
		case "addLeaveRecordInfor": {
			if (staffIsNull() != null) {
				log = loginfoService.searchEmpstaff();
				log.setStaff_ID(Integer.parseInt((String) arg0.getAttribute("staff_ID")));
			}
			String error = (String) arg0.getAttribute("error");
			if (error == "error") {
				response.getWriter().write(
						"<script>alert('提交失败，请重新提交');</script>");
				response.getWriter().flush();
			}
			break;
		}
		// 进入询问
		case "AR_loadInfor": {
			String error = (String) arg0.getAttribute("error");
			if (error == "error") {
				response.getWriter()
						.write("<script type='text/javascript'>alert('当前房间存在多个嫌疑人，可能是上一个嫌疑人出门时未刷卡（请保证进门和出门时成对刷卡），也可能是房间信息不正确');</script>");
				response.getWriter().flush();

			}
			break;
		}
		// 加载入区报告
		case "RG_loadInfor": {
			String error = (String) arg0.getAttribute("error");
			if (error == "error") {
				response.getWriter()
						.write("<script type='text/javascript'>alert('页面加载失败，可能是pdf配置失败');</script>");
				response.getWriter().flush();

			}
			break;
		}
		// 进入信息采集
		case "IM_loadInfor": {
			String error = (String) arg0.getAttribute("error");
			if (error == "error") {
				response.getWriter()
						.write("<script type='text/javascript'>alert('加载失败，可能是房间或读卡设备配置错误，修改配置后刷新页面');</script>");
				response.getWriter().flush();

			}
			break;
		}
		// 临时离区
		case "addTemporaryLeaveInfor": {
			String error = (String) arg0.getAttribute("error");
			if (error == "error") {
				response.getWriter().write(
						"<script>alert('提交失败，请重新提交');</script>");
				response.getWriter().flush();

			}
			break;
		}
		}
		return log;
	}

	private void checkWay(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String videoStetus = (String) request.getAttribute("videoStetus");
		if (videoStetus == "1") {
			response.getWriter().write(
					"<script>alert('回路正常，开始录像');</script>");
			response.getWriter().flush();

		}
		else if(videoStetus == "0"){
			response.getWriter().write(
					"<script>alert('回路饱和，录像失败');</script>");
			response.getWriter().flush();
		}
	}
	
	/**
	 * 查询数据库中endtime为0-0的记录（有且只能能是最后一条） 以Bool作为返回值是为了防止空指针
	 * 
	 * @return
	 */
	private PHCSMP_Process_Log endtimeIsZero() {
		try {
			PHCSMP_Process_Log processLog = loginfoService.searchEmpEndTime();
			System.out.println(processLog + "=====____+++++++"
					+ processLog.getLog_ID());
			return processLog;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 查询数据库中staff为xxx的记录（有且只能能是最后一条） 以Bool作为返回值是为了防止空指针
	 * 
	 * @return
	 */
	private PHCSMP_Process_Log staffIsNull() {
		try {
			PHCSMP_Process_Log processLog = loginfoService.searchEmpstaff();
			System.out.println(processLog + "=====____+++++++"
					+ processLog.getLog_ID());
			return processLog;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 封装获得processLog
	 * 
	 * @param arg0
	 * @param sdate
	 */
	private PHCSMP_Process_Log getProcessLog(HttpServletRequest arg0,
			String sdate, String ddate) {
		// 判断数据库中是否存在endtime为空的条目
		// 不存在，表示没有残缺的记录，每条记录都有成对的出入门时间，则创建新的记录
		PHCSMP_Process_Log processLog = new PHCSMP_Process_Log();
		if (endtimeIsZero() == null) {
			
			PHCSMP_Suspect suspect = (PHCSMP_Suspect) arg0
					.getAttribute("suspect");
			String suspectId = "";
			if(suspect!=null){
			 suspectId = suspect.getSuspect_ID(); // 获取嫌疑人id
			}
			PHCSMP_Room room = (PHCSMP_Room) arg0.getAttribute("room");
			if(room!=null){
			int roomId = room.getRoom_ID(); // 获取房间ID
			processLog.setProcess_ID(room.getProcess_ID()); // 封装流程号
			processLog.setiP_Address(room.getRoom_IPAddress()); // 获取房间IP
			}
			processLog.setStart_Time(sdate); // 设置开始进入时间
			processLog.setEnd_Time("0-0");
			processLog.setDate(ddate);
			processLog.setStaff_ID(0);
			processLog.setSuspect_ID(suspectId); // 封装嫌疑人ID
			
			System.out.println(suspectId + "----------------进入try");

		}
		// 存在表示当前记录值记录了入门阶段，则对其进行出门endtime的补全
		else {
			// 获得当前的残缺记录
			processLog = loginfoService.searchEmpEndTime();

			processLog.setEnd_Time(sdate);
		}

		return processLog;
	}

	/**
	 * 保存processLog进数据库
	 * 
	 * @param process
	 */
	private void saveprocess(PHCSMP_Process_Log process) {
		loginfoService.save(process);
	}

	/**
	 * 更新记录，即补全endtime
	 * 
	 * @param process
	 */
	private void updateprocess(PHCSMP_Process_Log process) {
		loginfoService.update(process);
	}

	/**
	 * 更新每个活动的staff
	 * 
	 * @param process
	 */
	private void updateporStaff(PHCSMP_Process_Log process) {
		loginfoService.updateStaff(process);
	}
}