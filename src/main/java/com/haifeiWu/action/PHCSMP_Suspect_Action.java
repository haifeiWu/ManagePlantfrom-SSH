package com.haifeiWu.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_Band;
import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.entity.PHCSMP_Dic_IdentifyCard_Type;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.BandService;
import com.haifeiWu.service.InformationCollectionService;
import com.haifeiWu.service.LeaveRecodService;
import com.haifeiWu.service.LineService;
import com.haifeiWu.service.PersonalCheckService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.Base64;
import com.haifeiWu.utils.CompleteCheck;

/**
 * 嫌疑人入区信息登记的action
 * 
 * @author wuhaifei
 * @d2016年10月17日
 */
public class PHCSMP_Suspect_Action extends BaseAction<PHCSMP_Suspect> {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -5503195229207984964L;

	@Autowired
	private SuspectService suspectService;
	@Autowired
	private LineService lineService;
	@Autowired
	private BandService bandService;
	@Autowired
	private PersonalCheckService personalCheckService;
	@Autowired
	private InformationCollectionService informationCollectionService;
	@Autowired
	private ActivityRecordService activityRecordService;
	@Autowired
	private LeaveRecodService leaveRecodService;

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

	/*
	 * 上传图片
	 */
	// public void uploadPhoto() {
	// // 找到当前嫌疑人
	// String ip = request.getRemoteAddr();
	// PHCSMP_Room room = roomService.findbyIp(ip);
	// int room_id = room.getRoom_ID();
	// System.out.println("获取到roomid为" + room_id);
	// PHCSMP_Suspect suspectInfor = suspectService.findByRoomID(room_id);
	//
	// String path = ServletActionContext.getServletContext().getRealPath(
	// "/images");
	// System.out.println(path + "///////////////////////////");
	// try {
	// // 删除原图片
	// String curPath = ServletActionContext.getServletContext()
	// .getRealPath("/") + suspectInfor.getFrontal_Photo();
	// new File(curPath).delete();
	// String curPath2 = ServletActionContext.getServletContext()
	// .getRealPath("/") + suspectInfor.getSideWays_Photo();
	// new File(curPath2).delete();
	// // 将新头像上传到服务器
	// FileUtils.copyFile(file, new File(new File(path), fileFileName));
	// FileUtils.copyFile(sfile, new File(new File(path), sfileFileName));
	// // 修改数据库中用户的头像路径信息
	// String fpath = "images/" + fileFileName;
	// String spath = "images/" + sfileFileName;
	// suspectService.updateSuspectPhotoPath(fpath, spath,
	// suspectInfor.getSuspect_ID());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	// 加载数据库的信息
	public String loadInfor() throws IOException {

		try {
			// PHCSMP_Staff user = (PHCSMP_Staff) request.getSession()
			// .getAttribute("user");
			// if (user == null) // 在未登录状态下
			// return "unLoginState";

			String entry_Time = new DateTime().toString("yyyy-MM-dd HH:mm");// 入区时间
			// 登录状态下，查询字典表的信息，存放到request中
			List<PHCSMP_Band> list = bandService.findAvailableBand();
			List<PHCSMP_Dic_IdentifyCard_Type> identifyCardType = suspectService
					.findAllIdentifyCardType();
			List<PHCSMP_Dic_Action_Cause> actionCause = suspectService
					.findAllSuspectCause();
			request.setAttribute("Suspect_ID", getSuspectId(entry_Time));// 自动生成嫌疑人编号
			// request.setAttribute("nEntryTime", nEntryTime);
			request.setAttribute("bundList", list);
			request.setAttribute("identifyCardType", identifyCardType);
			request.setAttribute("entry_Time", entry_Time);
			request.setAttribute("actionCause", actionCause);

			// 判断进度条
			// String suspectId=setSuspectId(entry_Time);
			// String suspectId = "LB-HB-20170317005";
			// PHCSMP_Suspect suspect = suspectService
			// .findBySuspetcId(suspectId);
			// PHCSMP_Personal_Check personalCheck = personalCheckService
			// .findInforBySuspetcId(suspectId);
			// PHCSMP_Information_Collection informationCollection =
			// informationCollectionService
			// .findInforBySuspetcId(suspectId);
			// List<PHCSMP_Activity_Record> activityRecordlist =
			// activityRecordService
			// .selectActivityRecordInfor(suspectId);
			// PHCSMP_Leave_Record leaveRecord = leaveRecodService
			// .findInforBySuspetcId(suspectId);
			// request.setAttribute("suspect", suspect);
			// request.setAttribute("personalCheck", personalCheck);
			// request.setAttribute("informationCollection",
			// informationCollection);
			// request.setAttribute("activityRecord", activityRecordlist);
			// request.setAttribute("leaveRecord", leaveRecord);
			// System.out.println("suspect=" + suspect + " "
			// + "personalCheck=" + personalCheck + " "
			// + "informationCollection=" + informationCollection
			// + " " + "activityRecord=" + activityRecordlist + " "
			// + "leaveRecord=" + leaveRecord);
		} catch (Exception e) {
			response.getWriter().write("<script> alert('信息加载失败！'); </script>");
			response.getWriter().flush();
			return "success";
		}
		return "loadInfor";
	}

	/**
	 * 
	 * 
	 * @param path
	 * @param fileName
	 * @throws IOException
	 */
	public String addSuspectInfor() throws IOException {
		boolean useLine = false;// 定义一个标志位，判断是否开启一路回路，便于在异常的时候判断是否需要释放回路
		try {
			// 验证照片是否为空
			System.out.println("file----------------------" + file);
			String frontal_Photo = "data:image/jpg;base64,"
					+ Base64.file2base64(file);
			String sideWays_Photo = "data:image/jpg;base64,"
					+ Base64.file2base64(sfile);

			model.setFrontal_Photo(frontal_Photo);
			model.setSideWays_Photo(sideWays_Photo);
			// 更新手环的is_Used状态
			bandService.update(1, model.getBand_ID());// 使用时是1，未使用时为0
			// 回路饱和性验证
			if (lineService.isFull()) {// 可以录像
				model.setRecordVideo_State(1);
				lineService.startLine();
				useLine = true;
			} else {// 不可以录像
				model.setRecordVideo_State(0);
			}
			fullCheck();
			suspectService.saveSuspect(model);// 保存嫌疑人信息，
			// response.getWriter().write("<script> alert('提交成功，请'); </script>");
			// response.getWriter().flush();
			return "loadInfor";
		} catch (Exception e) {
			bandService.update(0, model.getBand_ID());// 提交失败置0
			if (useLine)
				lineService.closeLine();// 释放一个回路
			// response.getWriter().write(
			// "<script> alert('提交失败，请重新提交'); </script>");
			// response.getWriter().flush();
			// 应该请求loadInfor的action，使用action链
			// 做异常处理，信息填写失败，加载页面时信息要在页面上显示
			request.setAttribute("msg", "提交失败，请重新提交");// 异常处理，在页面上提示错误信息
			// 提交失败获取页面填入的信息
			// request.getAttribute("identifyCard_Number");
			// request.getAttribute("now_address");
			// request.getAttribute("phone");
			// request.getAttribute("staff_ID");
			System.out
					.println("入区----------------------------------------提交失败，请重新提交");
			return "addSuspectInfor";
		}
	}

	/**
	 * Date:2017.02.26 author:whf
	 * 
	 * // 格式化获取的路径 String path = model.getTdentityID_Imag(); StringBuilder sb =
	 * new StringBuilder(path); path = sb.insert(2, "\\").toString(); //
	 * 获取web根目录 String temp = application.getRealPath("/"); //
	 * 使用身份证的身份证号码作为身份证照片的文件名 String fileName = model.getIdentifyCard_Number();
	 * // 得到身份证照片的相对目录 String picPath = "images/" + fileName + ".bmp"; //
	 * 设置身份证图片的相对目录，数据库中保存的是图片的web目录下的相对目录 model.setTdentityID_Imag(picPath); //
	 * 将改图片拷贝到服务器目录下 CopyFile.copyFile(path, temp + "/" + picPath);
	 */
	/* 检查用户登录信息 */
	// public String checkUser() {
	// PHCSMP_Staff user = (PHCSMP_Staff) request.getSession().getAttribute(
	// "user");
	// if (user != null) {
	// return "checkUser";
	// } else {
	// return "loginError";
	// }
	// }

	private void fullCheck() throws ClassNotFoundException {
		Class<?> c = Class.forName(PHCSMP_Suspect.class.getName());
		int count = CompleteCheck.IsEqualsNull(model, c);// 获取model对象不为空的字段的个数
		int fieldsNumber = CompleteCheck.getFieldsNumber(model, c);// 返回实体类中总字段数
		model.setFill_record(fieldsNumber - count - 4);//
		// 设置已填写的字段数，，，4应该是除去主键、FillRecord、TotalRecord/羁押时间
		model.setTotal_record(fieldsNumber - 4);// 设置应填写的字段

		System.out.println("未填写的字段：" + count);
		System.out.println("总字段：" + fieldsNumber);
	}

	public String updateInfor() {
		System.out.println("档案编号：" + request.getParameter("Suspect_ID"));
		System.out.println("updateInfor：修改嫌疑人信息！");
		return "updateInfor";
	}

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
