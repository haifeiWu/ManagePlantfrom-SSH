package com.haifeiWu.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.service.TemporaryLeaveService;
import com.haifeiWu.utils.HtmlToPdf;
import com.haifeiWu.utils.PropertiesReadUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.ssl.internal.www.protocol.https.Handler;

/**
 * 生成嫌疑人信息报告的action
 * 
 * @author wuhaifei
 * @d2016年10月17日
 */
@Controller
@Scope("prototype")
public class GenerateReportAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;
	
	protected String detainTime;
	

	// 嫌疑人的入区登记信息
	@Autowired
	private SuspectService suspectService;
	// 嫌疑人随身物品登记信息
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
	// 嫌疑人出区信息登记
	@Autowired
	private LeaveRecodService leaveRecodService;
	@Autowired
	private TemporaryLeaveService temporaryLeaveService;

	
	/**
	 * 生成嫌疑人入区信息报告
	 * 
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public String loadInfor() throws ParseException {
		System.out.println("嫌疑人入区信息报告");

		System.out.println("嫌疑人姓名：" + request.getParameter("personName"));
		System.out.println("档案编号：" + request.getParameter("suspectID"));
		
		
		
		/*
		 * 加载当前嫌疑人的所有的信息
		 */
		// 获取档案编号
		//String suspectId = (String) request.getParameter("suspectID");
		String suspectId="LB-HB-20170317005";
		
		if (suspectId == null) {
			return "NULL";
		}
		// 查找嫌疑人入区信息
		PHCSMP_Suspect suspect = suspectService.findBySuspetcId(suspectId);
		System.out.println("suspect="+suspect);
		// 嫌疑人随身所有物品检查信息s
		List<PHCSMP_BelongingS> belongingS = belongingInforService
				.selectBelongInfor(suspectId);
		System.out.println("belongingS="+belongingS);
		
		// 嫌疑人人身检查信息
		PHCSMP_Personal_Check personal_Check = personalCheckService
				.findInforBySuspetcId(suspectId);
		System.out.println("personal_Check="+personal_Check);
		// 嫌疑人所有的办案区记录信息
		List<PHCSMP_Activity_Record> activity_Record = activityRecordService
				.selectActivityRecordInfor(suspectId);
		System.out.println("activity_Record="+activity_Record);

		// 嫌疑人信息采集记录
		PHCSMP_Information_Collection information_Collection = informationCollectionService
				.findInforBySuspetcId(suspectId);
		System.out.println("information_Collection="+information_Collection);
		
		// 嫌疑人出区信息记录
		PHCSMP_Leave_Record leave_Record = leaveRecodService
				.findInforBySuspetcId(suspectId);
		System.out.println("leave_Record="+leave_Record);
		//暂时离区
		List<Temporary_Leave> temporaryLeaves=temporaryLeaveService.findTempLeaveListBySuspectID(suspectId);		
		System.out.println("leave_Record="+temporaryLeaves);
		// 犯人羁押时间
		// DateTimeFormatter format = DateTimeFormat
		// .forPattern("yyyy-MM-dd HH:mm");
		// DateTime startTime = DateTime.parse(suspect.getEnter_Time());
		// DateTime endTime = DateTime.parse(leave_Record.getLeave_Time());

		// int prisonHour = Hours.hoursBetween(startTime, endTime).getHours();

		String reportCreateTime = new DateTime().toString("yyyy-MM-dd HH:mm");

		if ((suspect == null) && (belongingS == null)
				&& (personal_Check == null) && (activity_Record == null)
				&& (information_Collection == null) && (leave_Record == null)&&(temporaryLeaves==null)) {
			return "NULL";
		}
	    
		
		
		
		// 将查找到的信息放入request中，然后从页面加载
		request.setAttribute("suspect", suspect);
		request.setAttribute("belongingS", belongingS);
		request.setAttribute("personal_Check", personal_Check);
		request.setAttribute("activity_Record", activity_Record);
		request.setAttribute("information_Collection", information_Collection);
		request.setAttribute("leave_Record", leave_Record);
		request.setAttribute("temporaryLeaves", temporaryLeaves);
		// request.setAttribute("prisonHour", prisonHour);
		request.setAttribute("reportCreateTime", reportCreateTime);
		
		
		request.setAttribute("detainTime", suspect.getDetain_Time());
		System.out.println("detainTime="+detainTime);
		
		//生成PDF
		/*try {
			createPdf(suspect.getSuspect_ID());
		} catch (IOException e) {
			System.out.println("pdf生成失败！");
			
		}*/

		return "loadInfor";
	}
	

	public String suspectInforSummary() {
		/*
		 * 加载当前嫌疑人的所有的信息
		 */
		// //获取档案编号
		// String suspectId = (String) request.getAttribute("suspectId");
		// //查找嫌疑人入区信息
		// PHCSMP_Suspect suspect =
		// suspectService.findInforBySuspetcId(suspectId);
		// //嫌疑人随身物品检查信息
		// PHCSMP_BelongingS belongingS =
		// inforService.findInforBySuspetcId(suspectId);
		// //嫌疑人人身检查信息
		// PHCSMP_Personal_Check personal_Check =
		// checkService.findInforBySuspetcId(suspectId);
		// //嫌疑人询问讯问记录信息
		// PHCSMP_Activity_Record activity_Record =
		// activityRecordService.findInforBySuspetcId(suspectId);
		// //嫌疑人信息采集记录
		// PHCSMP_Information_Collection information_Collection =
		// collectionService.findInforBySuspetcId(suspectId);
		// //嫌疑人出区信息记录
		// PHCSMP_Leave_Record leave_Record =
		// leaveRecodService.findInforBySuspetcId(suspectId);
		//
		// //将查找到的信息放入request中，然后从页面加载
		// request.setAttribute("suspect",suspect );
		// request.setAttribute("belongingS",belongingS );
		// request.setAttribute("personal_Check",personal_Check );
		// request.setAttribute("activity_Record",activity_Record );
		// request.setAttribute("information_Collection",information_Collection
		// );
		// request.setAttribute("leave_Record",leave_Record );

		System.out.println("SuspectManageAction:suspectInforSummary");
		return "suspectInforSummary";
	}

	/**
	 * 嫌疑人信息搜索
	 * 
	 * @return
	 */
	public String searchsuspectInfor() {
		System.out.println("SuspectManageAction:searchsuspectInfor");
		return "searchsuspectInfor";
	}
	
	/**
     * 从输入流中获取字节数组
     * 
     * @param in
     * @return
     * @throws IOException
     */
    private byte[] getByteData(InputStream in) throws IOException {
        byte[] b = new byte[1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len = 0;
        while ((len = in.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        if(null!=bos){
            bos.close();
        }
        return bos.toByteArray();
    }
     /**
      * 下载PDF文件
      * @return
      * @throws IOException
      */
	public String createPdf(  ) throws IOException
	{
		String suspectId=request.getParameter("suspectId");
		System.out.println("开始下载PDF");
		// String path = "http://localhost:8080/ManagePlantfrom-SSH/GR_loadInfor.action?suspectID="+suspectId;        
		String path = "http://localhost:8080/ManagePlantfrom-SSH/GR_loadInfor.action?suspectID="+request.getParameter("suspectId");
		System.out.println("path="+path);  
	        
	        //获取pdf的临时保存路径  
	        //tmp为网站下的目录  
	        //把生成的pdf放到网站下以便下载  
	        String pdfPath = request.getSession().getServletContext().getRealPath("/tmp");  
	        System.out.println("pdfPath="+pdfPath);
	        
	         String pdfName=suspectId+".pdf";
	         
	         System.out.println("pdfName="+pdfName);
	        if(HtmlToPdf.convert(path, pdfPath + "/" + pdfName)){  
	           //response.sendRedirect(request.getContextPath() + "/tmp/" + pdfName);
	        	request.setAttribute("a", pdfPath + "/" + pdfName);
	        	System.out.println(pdfPath + "/" + pdfName);
	           System.out.println("pdf成功生成！");
	           
	          
	        }
	      request.setAttribute("suspectID", suspectId);
		return "createPdf";
	       
	 }
	 
	        
	       
	/**
	 * 嫌疑人入区视频文件文件下载
	 * 
	 * @return
	 * @throws Exception
	 */
	public String downFile() throws Exception {
		String date = request.getParameter("date");
		String fileName = request.getParameter("fileName");
		System.out.println("入区时间：" + date + "\n文件名：" + fileName);

		String[] str = date.split("-");
		StringBuilder str1 = new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			str1.append(str[i] + "/");
			System.out.println(str1);
		}
		String downLoadFile = str1.toString() + fileName;

		response.sendRedirect("ftp://"
				+ PropertiesReadUtils.getString("remoteServerIP")
				+ "/recordfiles/" + downLoadFile);

		System.out.println("下载目录：/recordfiles/" + downLoadFile);
		System.out.println("下载成功：" + true);
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
	public String getDetainTime() {
		return detainTime;
	}
	public void setDetainTime(String detainTime) {
		this.detainTime = detainTime;
	}

}
