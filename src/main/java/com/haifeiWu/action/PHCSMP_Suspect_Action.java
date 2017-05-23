package com.haifeiWu.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.haifeiWu.entity.PHCSMP_Band;
import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.entity.PHCSMP_Dic_IdentifyCard_Type;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.BandService;
import com.haifeiWu.service.InformationCollectionService;
import com.haifeiWu.service.LeaveRecodService;
import com.haifeiWu.service.LineService;
import com.haifeiWu.service.PersonalCheckService;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.service.UserService;
import com.haifeiWu.utils.Base64;
import com.haifeiWu.utils.CompleteCheck;

/**
 * 嫌疑人入区信息登记的action
 * 
 * @author wuhaifei
 * @d2016年10月17日
 */
@Controller
@RequestMapping("/suspect")
@Scope("prototype")
public class PHCSMP_Suspect_Action {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -5503195229207984964L;
	private Logger log = Logger.getLogger(UserAction.class);
	@Autowired
	private SuspectService suspectService;
	@Autowired
	private LineService lineService;
	@Autowired
	private BandService bandService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private PersonalCheckService personalCheckService;
	@Autowired
	private InformationCollectionService informationCollectionService;
	@Autowired
	private ActivityRecordService activityRecordService;
	@Autowired
	private LeaveRecodService leaveRecodService;
	@Autowired
	private UserService userService;

	private String message;

	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File getSfile() {
		return sfile;
	}

	public void setSfile(File sfile) {
		this.sfile = sfile;
	}

	public String getSfileContentType() {
		return sfileContentType;
	}

	public void setSfileContentType(String sfileContentType) {
		this.sfileContentType = sfileContentType;
	}

	public String getSfileFileName() {
		return sfileFileName;
	}

	public void setSfileFileName(String sfileFileName) {
		this.sfileFileName = sfileFileName;
	}

	private String fileContentType;
	private String fileFileName;

	private File sfile;
	private String sfileContentType;
	private String sfileFileName;

	// 加载数据库的信息
	@RequestMapping(value = "/load")
	public String SUP_loadInfor(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			String entry_Time = new DateTime().toString("yyyy-MM-dd HH:mm");// 入区时间
			// 登录状态下，查询字典表的信息，存放到request中
			List<PHCSMP_Band> list = bandService.findAvailableBand();
			List<PHCSMP_Dic_IdentifyCard_Type> identifyCardType = suspectService
					.findAllIdentifyCardType();
			List<PHCSMP_Dic_Action_Cause> actionCause = suspectService
					.findAllSuspectCause();
			List<PHCSMP_Staff> staff = userService.findAllStaffs();
			request.setAttribute("staff", staff);
			request.setAttribute("Suspect_ID", getSuspectId(entry_Time));// 自动生成嫌疑人编号
			request.setAttribute("suspectId", getSuspectId(entry_Time));// 自动生成嫌疑人编号
			request.setAttribute("bundList", list);
			request.setAttribute("identifyCardType", identifyCardType);
			request.setAttribute("entry_Time", entry_Time);
			request.setAttribute("actionCause", actionCause);
			/*
			 * List<PHCSMP_Staff> staff = userService.findAllStaffs();
			 * request.setAttribute("staff", staff);
			 * 
			 * for (PHCSMP_Staff phcsmp_Staff : staff) {
			 * System.out.println(phcsmp_Staff.toString()); }
			 */
		} catch (Exception e) {
			// response.getWriter().write("<script> alert('信息加载失败!'); </script>");
			// response.getWriter().flush();
			// // 失败应该重定向到/home/index
			return "redirect:/home/index";
		}
		return "WEB-INF/jsp/recordInfor/entrance";
	}

	/**
	 * 
	 * 
	 * @param path
	 * @param fileName
	 * @throws IOException
	 */
	@RequestMapping(value = "/add")
	public String addSuspectInfor(PHCSMP_Suspect model,
			@RequestParam("file") MultipartFile file,
			@RequestParam("sfile") MultipartFile sfile,
			HttpServletRequest request) throws IOException {
		log.debug("-------" + model.toString());
		boolean useLine = false;// 定义一个标志位，判断是否开启一路回路，便于在异常的时候判断是否需要释放回路

		try {
			// 将照片保存到model中
			if (!file.isEmpty()) {
				model.setFrontal_Photo("data:image/jpg;base64,"
						+ Base64.file2base64(file.getInputStream()));
			}
			if (!sfile.isEmpty()) {
				model.setSideWays_Photo("data:image/jpg;base64,"
						+ Base64.file2base64(sfile.getInputStream()));
			}
			// 将房间号设置进去
			model.setRoom_Now(roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID());
			System.out
					.println("房间ip为------------------------------------------"
							+ request.getRemoteAddr());
			// 更新手环的is_Used状态
			bandService.update(1, model.getBand_ID());// 使用时是1，未使用时为0
			// 回路饱和性验证
			if (lineService.isFull()) {// 可以录像
				model.setRecordVideo_State(1);
				lineService.startLine();
				useLine = true;
				request.setAttribute("videoStetus", "1");
			} else {// 不可以录像
				model.setRecordVideo_State(0);
				request.setAttribute("videoStetus", "0");
			}
			fullCheck(model,request);
			//设置入区流程号
			model.setProcess_Now(6);
			//将suspected_Cause交给日志接收
			request.setAttribute("suspected_Cause", model.getSuspected_Cause());
			request.setAttribute("roomid", model.getRoom_Now());
			request.setAttribute("suspectId", model.getSuspect_ID());
			request.setAttribute("staff_ID", model.getStaff_ID());
			suspectService.saveSuspect(model);// 保存嫌疑人信息，
			// String vedioState = "";
			// if (model.getRecordVideo_State() == 0) {
			// vedioState = "因录播系统饱和，不";
			// }
			// response.getWriter().write(
			// "<script type='text/javascript'>alert('信息提交成功，该嫌疑人使用的是"
			// + model.getBand_ID() + "号手环，" + vedioState
			// + "可以进行录像');</script>");
			// response.getWriter().flush();
			return "redirect:/home/index";
		} catch (Exception e) {
			bandService.update(0, model.getBand_ID());// 提交失败置0
			if (useLine)
				lineService.closeLine();// 释放一个回路
			// request.setAttribute("msg", "提交失败，请重新提交");// 异常处理，在页面上提示错误信息
			return "redirect:/load";
		}
	}

	private void fullCheck(PHCSMP_Suspect model,HttpServletRequest request) throws ClassNotFoundException {
		Class<?> c = Class.forName(PHCSMP_Suspect.class.getName());
		int count = CompleteCheck.IsEqualsNull(model, c);// 获取model对象不为空的字段的个数
		// 返回实体类中总字段数
		model.setFill_record(27 - count + 2);// 填的+
		// 设置已填写的字段数，，，4应该是除去主键、FillRecord、TotalRecord/羁押时间
		model.setTotal_record(27);// 设置应填写的字段
		int complete_degree = (int) (27 - count + 2
				/ (float) 27 * 100);
		request.setAttribute("complete_degree", complete_degree);

	}

	// public String updateInfor() {
	// System.out.println("档案编号：" + request.getParameter("Suspect_ID"));
	// System.out.println("updateInfor：修改嫌疑人信息！");
	// return "updateInfor";
	// }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @author zhangxf 档案编号的生成
	 * @param entryTime
	 *            入区时间
	 * @return 档案编号
	 */
	public String getSuspectId(String entryTime) {
		String maxSuspectID = suspectService.getMaxID();
		if (maxSuspectID == null || maxSuspectID.equals("")) {
			maxSuspectID = "LB-HB-20170321001";
		}
		String oldDate = maxSuspectID.substring(6, 14);
		String newSuspectID = maxSuspectID.substring(14, 17);
		String newDate = entryTime.substring(0, 10).replaceAll("-", "");
		String suspectID;
		if (newDate.equals(oldDate)) {
			String num = String.valueOf(Integer.parseInt(newSuspectID) + 1);
			if (num.length() < 3) {
				for (int i = 0; i < 4 - num.length(); i++) {
					num = "0" + num;
				}
			}
			suspectID = maxSuspectID.substring(0, 6) + newDate + num;
		} else {
			suspectID = maxSuspectID.substring(0, 6) + newDate + "001";
		}
		return suspectID;
	}
}