package com.haifeiWu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.entity.PHCSMP_BelongingS;
import com.haifeiWu.entity.PHCSMP_Information_Collection;
import com.haifeiWu.entity.PHCSMP_Leave_Record;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.entity.Temporary_Leave;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.BelongingInforService;
import com.haifeiWu.service.InformationCollectionService;
import com.haifeiWu.service.LeaveRecodService;
import com.haifeiWu.service.PersonalCheckService;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.service.TemporaryLeaveService;
import com.haifeiWu.utils.PageBean;
import com.haifeiWu.utils.PropertiesReadUtils;
import com.haifeiWu.utils.Video;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * 嫌疑人信息管理action，待查嫌疑人信息，历史嫌疑人信息/demoone
 * 
 * @author wuhaifei
 * @d2016年10月17日
 */
@Controller
@RequestMapping("/suspectManage")
@Scope("prototype")
public class SuspectManageAction {

	@Autowired
	private SuspectService suspectService;// 嫌疑人信息管理
	@Autowired
	private LeaveRecodService leaveRecodService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private BelongingInforService belongingInforService;
	// 嫌疑人的人身检查信息
	@Autowired
	private PersonalCheckService personalCheckService;
	// 询问讯问记录信息登记
	@Autowired
	private ActivityRecordService activityRecordService;
	// 信息采集信息登记
	@Autowired
	private InformationCollectionService informationCollectionService;
	@Autowired
	private TemporaryLeaveService temporaryLeaveService;

	/**
	 * 加载嫌疑人信息
	 * 
	 * @return
	 */

	@RequestMapping(value = "/load")
	public String SM_loadInfor(HttpServletRequest request) {
		/* System.out.println("历史记录，待办信息"); */
		// 获取待查嫌疑人信息
		List<PHCSMP_Suspect> suspectCheckInfor = suspectService
				.getOnPoliceSuspect();
		// 获取出区嫌疑人数据
		List<PHCSMP_Suspect> suspectCheckedInfor = suspectService
				.getLeavePoliceSuspect();
		// System.out.println("----------待查的" + suspectCheckInfor);
		// System.out.println("----------历史的----" + suspectCheckedInfor);
		List<String> roomnameList = new ArrayList<String>();
		// List<PHCSMP_Leave_Record>
		// if ((suspectCheckInfor == null) || (suspectCheckedInfor == null)) {
		// return "/jsp/error/null";
		// }
		// 将信息放入到request中
		if ((suspectCheckInfor != null))
			request.setAttribute("suspectCheckInfor", suspectCheckInfor);
		if (suspectCheckedInfor != null)
			request.setAttribute("suspectCheckedInfor", suspectCheckedInfor);

		// for (PHCSMP_Suspect phcsmp_Suspect : suspectCheckedInfor) {
		// System.err.println(phcsmp_Suspect.toString());
		// }
		for (PHCSMP_Suspect phcsmp_Suspect : suspectCheckInfor) {
			roomnameList.add(roomService.findByRoomID(
					phcsmp_Suspect.getRoom_Now()).getRoom_Name());
		}

		// 获取房间名
		request.setAttribute("roomNameList", roomnameList);
		// getDistanceTime(suspectCheckedInfor., suspectCheckedInfor.get(14));
		return "WEB-INF/jsp/suspectmanage/historyRecord";

	}

	/**
	 * 根据姓名或者档案编号查找嫌疑人信息
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = ("/search"))
	public String searchsuspectInfor(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		String searchInfor = request.getParameter("searchInfor");
		List<PHCSMP_Suspect> suspect = null;
		List<PHCSMP_Suspect> suspectNow = null;
		if (searchInfor == "" || searchInfor == null) {
			return SM_loadInfor(request);
		}
		/*
		 * 通过正则表达式来区分嫌疑人姓名
		 */
		Pattern p = Pattern.compile("^[\u4E00-\u9FA5]+$");
		Matcher m = p.matcher(searchInfor);
		boolean result = m.find();
		char fir = searchInfor.charAt(0);
		if (fir < 130) {
			if (fir < 60) {
				// 非数组类型的身份证号查找
				suspect = suspectService.findByCardId(searchInfor);
				suspectNow = suspectService.findByCardIdNow(searchInfor);
				System.out.println("身份证号：" + searchInfor);
				request.setAttribute("suspect", suspect);
				request.setAttribute("suspectNow", suspectNow);
				System.out.println(suspect);
			} else {
				// 根据档案编号查询嫌疑人信息
				suspect = suspectService.serachInforBySuspectId(searchInfor);
				suspectNow = suspectService
						.serachInforBySuspectIdNow(searchInfor);
				System.out.println("档案编号：" + searchInfor);
				request.setAttribute("suspect", suspect);
				request.setAttribute("suspectNow", suspectNow);
				System.out.println(suspect);
				System.out.println(suspectNow);
			}
		}
		if (result) {
			suspect = suspectService.findBySuspectName(searchInfor);
			suspectNow = suspectService.finBySuspectNameNow(searchInfor);
			request.setAttribute("suspect", suspect);
			request.setAttribute("suspectNow", suspectNow);
			System.out.println("嫌疑人姓名：" + searchInfor);
			System.out.println(suspect);
			System.out.println(suspectNow);
		}

		if (suspect.isEmpty()
				&& (!suspectNow.isEmpty() && suspectNow.size() == 1)) {
			return toGR(suspectNow.get(0).getSuspect_ID(), request, response);
		} else {
			return "WEB-INF/jsp/suspectmanage/suspectInforList";
		}
	}

	/***
	 * 录像下载失败的嫌疑人信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/downVideoFail")
	public String videoDownFailList(HttpServletRequest request) {

		List<String> leaveTimeList = new ArrayList<String>();
		// try {
		List<PHCSMP_Suspect> suspectList = suspectService
				.findAllVideoDownloadFailSuspectInfor();
		for (int i = 0; i < suspectList.size(); i++) {

			String suspectId = suspectList.get(i).getSuspect_ID();
			// System.out.println("suspectId" + suspectId);
			String leavaTime = leaveRecodService
					.findLeaveRecordInfor(suspectId).getLeave_Time();
			// System.out.println("leavetime=sbsbsb+" + leavaTime);
			leaveTimeList.add(leavaTime);
			// System.out.println("leavetime=" + leaveTimeList.get(i));
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < suspectList.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("suspect_Name", suspectList.get(i).getSuspect_Name());
			map.put("suspect_ID", suspectList.get(i).getSuspect_ID());
			map.put("enter_Time", suspectList.get(i).getEnter_Time());
			map.put("identifyCard_Number", suspectList.get(i)
					.getIdentifyCard_Number());
			map.put("leave_Time", leaveTimeList.get(i));
			list.add(map);
		}
		request.setAttribute("suspect", list);
		return "WEB-INF/jsp/suspectmanage/videoDownloadFailSuspectList";
	}

	/**
	 * 再次下载录像文件
	 * 
	 * @param band_ID
	 * @param identificationCard
	 * @return
	 */
	@RequestMapping(value = ("/download"))
	public String downLoadByHands(HttpServletRequest request) throws Exception {
		String suspect_ID = request.getParameter("suspect_ID");
		PHCSMP_Suspect phcsmp_Suspect = suspectService
				.findBySuspetcId(suspect_ID);

		// System.out.println("查询到的嫌疑人信息---------------"
		// + phcsmp_Suspect.toString());
		try {
			Video.setRBServerCfg();
			Video.setFtpServerCfg(phcsmp_Suspect.getBand_ID(),
					phcsmp_Suspect.getIdentifyCard_Number());
			Video.uploadRecFile(phcsmp_Suspect.getBand_ID(),
					phcsmp_Suspect.getIdentifyCard_Number());
		} catch (Exception e) {
			request.setAttribute("msg", "下载失败，请重新下载");
		}
		return videoDownFailList(request);
	}

	@RequestMapping(value = "/downVideoSucc")
	public String videoDownSuccessList(HttpServletRequest request) {
		List<String> leaveTimeList = new ArrayList<String>();
		// try {
		List<PHCSMP_Suspect> suspectList = suspectService
				.findAllByIsRecordVedio();
		for (int i = 0; i < suspectList.size(); i++) {

			String suspectId = suspectList.get(i).getSuspect_ID();
			// System.out.println("suspectId" + suspectId);
			String leavaTime = leaveRecodService
					.findLeaveRecordInfor(suspectId).getLeave_Time();
			// System.out.println("leavetime=" + leavaTime);
			leaveTimeList.add(leavaTime);
			// System.out.println("leavetime=" + leaveTimeList.get(i));
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < suspectList.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("suspect_Name", suspectList.get(i).getSuspect_Name());
			map.put("suspect_ID", suspectList.get(i).getSuspect_ID());
			map.put("enter_Time", suspectList.get(i).getEnter_Time());
			map.put("identifyCard_Number", suspectList.get(i)
					.getIdentifyCard_Number());
			map.put("leave_Time", leaveTimeList.get(i));
			map.put("vedio_number", suspectList.get(i).getVedio_Number());
			list.add(map);

		}

		if (!list.isEmpty()) {
			request.setAttribute("suspect", list);
		}

		// 将path放到前台
		request.setAttribute("vedioPath",
				PropertiesReadUtils.getRecordConfString("uploadDir"));
		return "WEB-INF/jsp/suspectmanage/videoDownloadSuccessSuspectList";

	}

	@RequestMapping("/downloadVeio")
	public String download(String vedioName,HttpServletRequest request, HttpServletResponse response) throws IOException{
		 response.setCharacterEncoding("utf-8");//设置编码
	     response.setContentType("multipart/form-data");//设置类型
	     response.setHeader("Content-Disposition", "attachment;fileName="+ vedioName);                                       //设置响应头
	     try {
	    	//获取服务器根目录
    	    String classPath = Thread.currentThread().getContextClassLoader()  
    	            .getResource("").getPath();  
    	    String rootPath = "";  
    	   
    	    if ("\\".equals(File.separator)) {  
    	        String path = classPath.substring(1,  
    	                classPath.indexOf("/WEB-INF/classes"));  
    	        rootPath = path.substring(0, path.lastIndexOf("/"));  
    	        rootPath = rootPath.replace("/", "\\");  
    	    }  
    	    
    	    String filePath=rootPath+"\\"+"ftp"+"\\"+vedioName;
    	    
    	    System.out.println(filePath+"rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
	        /*String filePath = request.getSession().getServletContext().getRealPath("/"+vedioName);;*/
	        InputStream inputStream = new FileInputStream(new File(filePath));
	        OutputStream os = response.getOutputStream();
	        byte[] b = new byte[2048];
	        int length;
	        while ((length = inputStream.read(b)) > 0) {
	                os.write(b, 0, length);
	        }//边读模板文件边写入输出流
	        os.close();
	        inputStream.close();//关流
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;                //注意此时return null
    }
	/**
	 * 历史嫌疑人分页显示
	 */
	@RequestMapping(value = ("/execute"))
	public String SM_executee(HttpServletRequest request) throws Exception {
		int page = Integer.parseInt(request.getParameter("page"));
		// 获取历史嫌疑人数据，在页面显示数量
		System.out.println("sb--------------------------");
		List<PHCSMP_Suspect> suspectCheckedInfor = suspectService
				.getLeavePoliceSuspect();
		request.setAttribute("suspectCheckedInfor", suspectCheckedInfor);
		// 表示每页显示5条记录，page表示当前网页
		PageBean pageBean = suspectService.getPageBean(10, page);

		// request = ServletActionContext.getRequest();

		request.setAttribute("pageBean", pageBean);

		return "WEB-INF/jsp/suspectmanage/historySuspectInforList";
	}

	/**
	 * 跳转到入区人员信息总汇
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	private String toGR(String suspectId, HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		try {
			// 查找嫌疑人入区信息
			PHCSMP_Suspect suspect = suspectService.findBySuspetcId(suspectId);
			// 嫌疑人随身所有物品检查信息s
			List<PHCSMP_BelongingS> belongingS = belongingInforService
					.selectBelongInfor(suspectId);
			// 嫌疑人人身检查信息
			PHCSMP_Personal_Check personal_Check = personalCheckService
					.findInforBySuspetcId(suspectId);
			// 嫌疑人所有的办案区记录信息
			List<PHCSMP_Activity_Record> activity_Record = activityRecordService
					.selectActivityRecordInfor(suspectId);

			// 嫌疑人信息采集记录
			PHCSMP_Information_Collection information_Collection = informationCollectionService
					.findInforBySuspetcId(suspectId);
			System.out.println("information_Collection="
					+ information_Collection);

			// 嫌疑人离区信息记录
			PHCSMP_Leave_Record leave_Record = leaveRecodService
					.findInforBySuspetcId(suspectId);
			// 暂时出区
			List<Temporary_Leave> temporaryLeaves = temporaryLeaveService
					.findTempLeaveListBySuspectID(suspectId);

			String reportCreateTime = new DateTime()
					.toString("yyyy-MM-dd HH:mm");

			// 将查找到的信息放入request中，然后从页面加载
			request.setAttribute("suspect", suspect);
			request.setAttribute("belongingS", belongingS);
			request.setAttribute("personal_Check", personal_Check);
			request.setAttribute("activity_Record", activity_Record);
			request.setAttribute("information_Collection",
					information_Collection);
			request.setAttribute("leave_Record", leave_Record);
			request.setAttribute("temporaryLeaves", temporaryLeaves);
			request.setAttribute("reportCreateTime", reportCreateTime);
			request.setAttribute("pdfFilePath",
					PropertiesReadUtils.getRecordConfString("uploadDir") + "//"
							+ suspectId + ".pdf");

			return "WEB-INF/jsp/recordInfor/report.jsp";
		} catch (Exception e) {
			// response.getWriter()
			// .write("<script type='text/javascript'>alert('页面加载失败，可能是pdf配置失败');</script>");
			// response.getWriter().flush();
			return "WEB-INF/jsp/suspectmanage/historySuspectInforList.jsp";
		}
	}

}
