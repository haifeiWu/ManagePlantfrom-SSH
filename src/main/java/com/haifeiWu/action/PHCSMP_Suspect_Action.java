package com.haifeiWu.action;

import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_Band;
import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.entity.PHCSMP_Dic_IdentifyCard_Type;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.BandService;
import com.haifeiWu.service.LineService;
import com.haifeiWu.service.SuspectService;
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
	private String message;

	// 加载数据库的信息
	public String loadInfor() throws IOException {

		try {
			PHCSMP_Staff user = (PHCSMP_Staff) request.getSession()
					.getAttribute("user");
			if (user == null) {// 在未登录状态下
				return "unLoginState";
			} else {
				String entry_Time = new DateTime().toString("yyyy-MM-dd HH:mm");// 入区时间
				// 登录状态下，查询字典表的信息，存放到request中
				List<PHCSMP_Band> list = bandService.findAvailableBand();
				List<PHCSMP_Dic_IdentifyCard_Type> identifyCardType = suspectService
						.findAllIdentifyCardType();
				List<PHCSMP_Dic_Action_Cause> actionCause = suspectService
						.findAllSuspectCause();
				request.setAttribute("Suspect_ID", setSuspectId(entry_Time));
				// request.setAttribute("nEntryTime", nEntryTime);
				request.setAttribute("bundList", list);
				request.setAttribute("identifyCardType", identifyCardType);
				request.setAttribute("entry_Time", entry_Time);
				System.out.println("=======入区时间========" + entry_Time);
				request.setAttribute("actionCause", actionCause);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("<script> alert('信息加载失败！'); </script>");
			response.getWriter().flush();
			return "success";
		}
		return "loadInfor";
	}

	/**
	 * 将图片拷贝到服务器下
	 * 
	 * @param path
	 * @param fileName
	 * @throws IOException
	 */
	public String addSuspectInfor() throws IOException {
		try {
			fullCheck();
			// 手环必须填写
			// if (model.getBand_ID() == 0) {
			// response.getWriter().write(
			// "<script> alert('提交失败，请填写手环'); </script>");
			// response.getWriter().flush();
			// return "loadInfor";
			// }
			
			// 更新手环的is_Used状态
			bandService.update(1, model.getBand_ID());//使用时是1，未使用时为0
			// 回路饱和性验证
			if (lineService.isFull()) {// 可以录像
				model.setRecordVideo_State(1);
				lineService.startLine();
				System.out.println("判断是否为满");
			}
			//String maxSuspectID = suspectService.getMaxID().substring(1,23);//此目的为了让程序报错
			
			PHCSMP_Suspect suspect = suspectService.findBySuspetcId(model
					.getSuspect_ID());
			if (suspect == null) {
				suspectService.saveSuspect(model);// 保存嫌疑人信息，
				System.out.println("-----------保存执行了-----------");
				// System.out.println(",,,,,,,,,,,,,,,,,," + suspect_ID);
			} else {
				suspectService.updateSuspect(model);// 更新嫌疑人信息
			}
			return "success";
		} catch (Exception e) {
			bandService.update(0, model.getBand_ID());// 提交失败置0
			lineService.closeLine();//释放一个回路
			//response.getWriter().write(
			//			"<script> alert('提交失败，请重新提交'); </script>");
			//response.getWriter().flush();
			// 应该请求loadInfor的action，使用action链
			// 做异常处理，信息填写失败，加载页面时信息要在页面上显示
			// 考虑回路饱和性验证的成功或失败
			request.setAttribute("msg", "提交失败，请重新提交");//异常处理，在页面上提示错误信息
			//提交失败获取页面填入的信息
			//request.getAttribute("identifyCard_Number");
			//request.getAttribute("now_address");
			//request.getAttribute("phone");
			//request.getAttribute("staff_ID");
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
	public String checkUser() {
		PHCSMP_Staff user = (PHCSMP_Staff) request.getSession().getAttribute(
				"user");
		if (user != null) {
			return "checkUser";
		} else {
			return "loginError";
		}
	}

	private void fullCheck() throws ClassNotFoundException {
		Class<?> c = Class.forName(PHCSMP_Suspect.class.getName());
		int count = CompleteCheck.IsEqualsNull(model, c);// 获取model对象不为空的字段的个数
		int fieldsNumber = CompleteCheck.getFieldsNumber(model, c);// 返回实体类中总字段数
		model.setFill_record(fieldsNumber - count - 3);//
		// 设置已填写的字段数，，，3应该是除去主键、FillRecord、TotalRecord
		model.setTotal_record(fieldsNumber - 3);// 设置应填写的字段

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
	 * @author zhangxf
	 * 档案编号的生成
	 * @param entryTime 入区时间
	 * @return 档案编号
	 */
	public String setSuspectId(String entryTime){
		String maxSuspectID = suspectService.getMaxID();
		if(maxSuspectID==null||maxSuspectID.equals("")){
			maxSuspectID="LB-HB-20170321001";
		}
		String oldDate = maxSuspectID.substring(6, 14);
		String newSuspectID = maxSuspectID.substring(14, 17);
		String newDate = entryTime.substring(0, 10)
				.replaceAll("-", "");
		String suspectID;
		if (newDate.equals(oldDate)) {
			String num = String
					.valueOf(Integer.parseInt(newSuspectID) + 1);
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
