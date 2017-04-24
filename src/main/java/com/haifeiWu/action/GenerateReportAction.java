package com.haifeiWu.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
import com.haifeiWu.utils.PropertiesReadUtils;

/**
 * 生成嫌疑人信息报告的action
 * 
 * @author wuhaifei
 * @d2016年10月17日
 */
@Controller
@Scope("prototype")
public class GenerateReportAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	@RequestMapping(value = "report")
	public String loadInfor(@RequestParam("suspectID") String suspectId,
			HttpServletRequest request) throws IOException {
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
			// request.setAttribute("prisonHour", prisonHour);
			request.setAttribute("reportCreateTime", reportCreateTime);
			request.setAttribute("pdfFilePath",
					PropertiesReadUtils.getRecordConfString("uploadDir") + "//"
							+ suspectId + ".pdf");
			// request.setAttribute("detainTime", suspect.getDetain_Time());
			System.out.println("detainTime=" + detainTime);
			return "/WEB-INF/jsp/recordInfor/report";
		} catch (Exception e) {
			// response.getWriter()
			// .write("<script type='text/javascript'>alert('页面加载失败，可能是pdf配置失败');</script>");
			// response.getWriter().flush();
			return "redirect:/home/index";
		}
	}

	public String getDetainTime() {
		return detainTime;
	}

	public void setDetainTime(String detainTime) {
		this.detainTime = detainTime;
	}

}
