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

		try{
		PHCSMP_Staff user = (PHCSMP_Staff) request.getSession().getAttribute(
				"user");
		String entry_Time = new DateTime().toString("yyyy-MM-dd HH:mm");// 入区时间
		//String nEntryTime = new DateTime().toString("yyyy年MM月dd日");// 入区时间
		// 饱和性验证

		if (user == null) {// 在未登录状态下
			return "unLoginState";
		} else {
			// 登录状态下，查询字典表的信息，存放到request中
			List<PHCSMP_Band> list = bandService.findAvailableBand();
			List<PHCSMP_Dic_IdentifyCard_Type> identifyCardType = suspectService
					.findAllIdentifyCardType();
			List<PHCSMP_Dic_Action_Cause> actionCause = suspectService
					.findAllSuspectCause();
			String maxSuspectID=suspectService.getMaxID();
		//	System.out.println("=====最大的id======"+maxSuspectID);
			String oldDate=maxSuspectID.substring(6, 14);
			String newSuspectID=maxSuspectID.substring(14, 17);
		//	System.out.println("=====上一次保存成功的时间======="+oldDate);
			
		//	System.out.println("=====NEWid======"+newSuspectID);
			String newDate=entry_Time.substring(0, 10).replaceAll("-", "");
		//	System.out.println("=====新的日期======"+newDate);
			String suspectID;
			if(newDate.equals(oldDate)){
				String num=String.valueOf(Integer.parseInt(newSuspectID)+1);
				if(num.length()<3){
					for (int i = 0; i < 4-num.length(); i++) {
						num="0"+num;
					}
					
				}
				suspectID=maxSuspectID.substring(0, 6)+newDate+num;
			}else{
				suspectID=maxSuspectID.substring(0, 6)+newDate+"001";
			}
			request.setAttribute("Suspect_ID", suspectID);
			//request.setAttribute("nEntryTime", nEntryTime);
			request.setAttribute("bundList", list);
			request.setAttribute("identifyCardType", identifyCardType);
			request.setAttribute("entry_Time", entry_Time);
//			request.getParameter(entry_Time);
//			model.setEnter_Time(entry_Time);
			System.out.println("=======入区时间========"+entry_Time);
			request.setAttribute("actionCause", actionCause);

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("<script> alert('信息加载失败！'); </script>");
			response.getWriter().flush();

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
		/**
		 * Date:2017.02.26 author:whf
		 * 
		 * // 格式化获取的路径 String path = model.getTdentityID_Imag(); StringBuilder
		 * sb = new StringBuilder(path); path = sb.insert(2, "\\").toString();
		 * // 获取web根目录 String temp = application.getRealPath("/"); //
		 * 使用身份证的身份证号码作为身份证照片的文件名 String fileName =
		 * model.getIdentifyCard_Number(); // 得到身份证照片的相对目录 String picPath =
		 * "images/" + fileName + ".bmp"; // 设置身份证图片的相对目录，数据库中保存的是图片的web目录下的相对目录
		 * model.setTdentityID_Imag(picPath); // 将改图片拷贝到服务器目录下
		 * CopyFile.copyFile(path, temp + "/" + picPath);
		 */


// 		String suspect_ID = request.getParameter("Suspect_ID");
// 		System.out.println("+++++++++++++++" + suspect_ID);

		try {

			fullCheck();
			// 手环必须填写
			if (model.getBand_ID() == 0) {
				response.getWriter().write(
						"<script> alert('提交失败，请填写手环'); </script>");
				response.getWriter().flush();
				return "loadInfor";
			}

			// 更新手环的is_Used状态
			bandService.update(1, model.getBand_ID());

			// if (bandService.find)
			// suspectService.updateSuspect(suspectInfor);

			// System.out.println("----------------------" + model.toString());
			/*
			 * suspectService.saveSuspect(model);// 保存嫌疑人信息，
			 */
			// 回路饱和性验证,
//			 if (lineService.isFull()) {// 可以录像
//				 model.setRecordVideo_State(1);
//			 }else{
//				 
//			 }

			System.out.println("----------------------" + model.toString());
			/*
			 * suspectService.saveSuspect(model);// 保存嫌疑人信息，
			 */// 回路饱和性验证
			if (lineService.isFull()) {// 可以录像
				model.setRecordVideo_State(1);
			}

			// System.out.println(3/0);
			PHCSMP_Suspect suspect = suspectService.findBySuspetcId(model
					.getSuspect_ID());
			if (suspect == null) {
				suspectService.saveSuspect(model);// 保存嫌疑人信息，
				System.out.println("-----------保存执行了-----------");
		//		System.out.println(",,,,,,,,,,,,,,,,,," + suspect_ID);
			} else {
				suspectService.updateSuspect(model);// 更新嫌疑人信息
			}
			return "success";
		} catch (Exception e) {
			response.getWriter().write(
					"<script> alert('提交失败，请重新提交'); </script>");
			bandService.update(0, model.getBand_ID());//提交失败置0

// 			} else {
// 				suspectService.updateSuspect(model);// 更新嫌疑人信息
// 			}

			// System.out.println("----------------------"
			// + suspectService.findBySuspetcId(model.getSuspect_ID())
			// .toString());

			//System.out.println(model.getIdentityCard_Photo());
			// 测试
			// List<PHCSMP_Band> test = bandService.findAllBundInfor();
			// for (PHCSMP_Band t : test) {
			// System.out.println("------------------------------------>"
			// + t.toString());
			// }

// 			return "success";

// 		} catch (Exception e) {
// 			response.getWriter().write(
// 					"<script> alert('提交失败，请重新提交'); </script>");

			response.getWriter().flush();
			addSuspectInfor();
			return "null";
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

}
