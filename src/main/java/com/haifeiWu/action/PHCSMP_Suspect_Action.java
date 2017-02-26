package com.haifeiWu.action;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_Band;
import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.entity.PHCSMP_Dic_IdentifyCard_Type;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.CompleteCheck;
import com.haifeiWu.utils.CopyFile;

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
	/**
	 * 将图片拷贝到服务器下
	 * @param path
	 * @param fileName
	 */
	private void copyFile(String path,String fileName){
		// 格式化获取的路径
		StringBuilder sb = new StringBuilder(path);
		path = sb.insert(2, "\\").toString();
		// 获取web根目录
		String temp = application.getRealPath("/");
		// 使用身份证的身份证号码作为身份证照片的文件名fileName
		// 得到身份证照片的相对目录
		String picPath = "images/" + fileName + ".bmp";
		// 设置身份证图片的相对目录，数据库中保存的是图片的web目录下的相对目录
		model.setIdentityCard_Photo(picPath);
		// 将该图片拷贝到服务器目录下
		CopyFile.copyFile(path, temp + "/" + picPath);
	}
	/**
	 * 添加嫌疑人信息，并设置相关字段
	 * 
	 * @return addSuspectInfor
	 * @throws Exception
	 */
	public String addSuspectInfor() throws Exception {
		//将图片拷贝到服务器目录下
		copyFile(model.getIdentityCard_Photo(),model.getIdentifyCard_Number());
		
		//完整性检查
		Class<?> c = Class.forName(PHCSMP_Suspect.class.getName());
		int count = CompleteCheck.IsEqualsNull(model, c);//获取model对象不为空的字段的个数
		int fieldsNumber = CompleteCheck.getFieldsNumber(model, c);//返回实体类中总字段数
		model.setFill_record(fieldsNumber - count - 3);// 设置已填写的字段数，，，3应该是除去主键、FillRecord、TotalRecord
		model.setTotal_record(fieldsNumber - 3);// 设置应填写的字段
		System.out.println("未填写的字段：" + count);
		System.out.println("总字段：" + fieldsNumber);
		
		suspectService.saveSuspectInfor(model);//保存嫌疑人信息，
		return "addSuspectInfor";
	}

	// 加载数据库的信息
	public String loadInfor() {
		PHCSMP_Staff user = (PHCSMP_Staff) request.getSession().getAttribute(
				"user");

		String entryTime = new DateTime().toString("yyy-mm-dd HH:mm");// 入区时间

		String nEntryTime = new DateTime().toString("yyyy年MM月dd日");// 入区时间

		if (user == null) {// 在未登录状态下
			return "unLoginState";
		} else {
			//登录状态下，
			List<PHCSMP_Band> list = suspectService.findAllBundInfor();
			List<PHCSMP_Dic_IdentifyCard_Type> identifyCardType = suspectService
					.findAllIdentifyCardType();
			List<PHCSMP_Dic_Action_Cause> actionCause = suspectService
					.findAllSuspectCause();

			request.setAttribute("nEntryTime", nEntryTime);
			request.setAttribute("bundList", list);
			request.setAttribute("identifyCardType", identifyCardType);
			request.setAttribute("entryTime", entryTime);
			request.setAttribute("actionCause", actionCause);
			return "loadInfor";
		}
	}

	public String unlogin_load() {

		List<PHCSMP_Band> list = suspectService.findAllBundInfor();
		List<PHCSMP_Dic_IdentifyCard_Type> identifyCardType = suspectService
				.findAllIdentifyCardType();
		List<PHCSMP_Dic_Action_Cause> actionCause = suspectService
				.findAllSuspectCause();

		request.setAttribute("bundList", list);
		request.setAttribute("identifyCardType", identifyCardType);
		request.setAttribute("actionCause", actionCause);
		return "unlogin_load";
	}

	public String updateInfor() {
		System.out.println("档案编号：" + request.getParameter("Suspect_ID"));
		System.out.println("updateInfor：修改嫌疑人信息！");
		return "updateInfor";
	}
}
