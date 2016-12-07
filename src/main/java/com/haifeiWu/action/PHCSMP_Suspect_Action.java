package com.haifeiWu.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_Band;
import com.haifeiWu.entity.PHCSMP_Dic_Action_Cause;
import com.haifeiWu.entity.PHCSMP_Dic_IdentifyCard_Type;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.entity.PHCSMP_Suspect;
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
	 * 添加嫌疑人信息，并设置相关字段
	 * 
	 * @return addSuspectInfor
	 * @throws Exception
	 */
	public String addSuspectInfor() throws Exception {

		/*
		 * 设置出生日期格式
		 */
		StringBuilder sb = new StringBuilder();
		String[] arr = model.getBirthday().split(", ");
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i] + "-");
		}
		String date = sb.toString().substring(0, sb.toString().length() - 1);
		model.setBirthday(date);
		System.out.println(date);

		Class<?> c = Class.forName(PHCSMP_Suspect.class.getName());

		int count = CompleteCheck.IsEqualsNull(model, c);
		int fieldsNumber = CompleteCheck.getFieldsNumber(model, c);

		model.setFill_record(fieldsNumber - count - 3);// 设置已填写的字段数
		model.setTotal_record(fieldsNumber - 3);// 设置应填写的字段
		System.out.println("未填写的字段：" + count);
		System.out.println("总字段：" + fieldsNumber);
		/* 第一个添加嫌疑人的信息直接设置已填写的字段即可 */
		suspectService.saveSuspectInfor(model);
		return "addSuspectInfor";
	}

	// 加载数据库的手环id信息
	public String loadInfor() {
		PHCSMP_Staff user = (PHCSMP_Staff) request.getSession().getAttribute(
				"user");

		if (user == null) {// 在未登录状态下
			return "unLoginState";
		} else {
			List<PHCSMP_Band> list = suspectService.findAllBundInfor();
			List<PHCSMP_Dic_IdentifyCard_Type> identifyCardType = suspectService
					.findAllIdentifyCardType();
			List<PHCSMP_Dic_Action_Cause> actionCause = suspectService
					.findAllSuspectCause();

			request.setAttribute("bundList", list);
			request.setAttribute("identifyCardType", identifyCardType);
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
