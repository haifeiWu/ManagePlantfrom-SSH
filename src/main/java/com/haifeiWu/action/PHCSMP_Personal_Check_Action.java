package com.haifeiWu.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_BelongingS;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.BelongingInforService;
import com.haifeiWu.service.PersonalCheckService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.CompleteCheck;

/**
 * 人身检查记录信息
 * 
 * @author wuhaifei
 * @d2016年8月14日
 */
@Controller
@Scope("prototype")
public class PHCSMP_Personal_Check_Action extends
		BaseAction<PHCSMP_Personal_Check> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用于多条记录的提取，一条人身检查记录对应多个随身物品登记记录
	private List<PHCSMP_BelongingS> belong = new ArrayList<PHCSMP_BelongingS>();
	private Logger logger = Logger
			.getLogger(PHCSMP_Personal_Check_Action.class);
	// 人身检查的service
	@Autowired
	private PersonalCheckService personalCheckService;
	// 随身物品登记
	@Autowired
	private BelongingInforService belongingInforService;
	// 嫌疑人基本信息
	@Autowired
	SuspectService suspectService;

	/**
	 * 添加用户人身检查信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addCheckPersonInfor() throws Exception {

		// System.out.println("单条信息：" + model.toString());

		model.setEnd_time(new DateTime().toString("yyyy-MM-dd HH:mm"));// 设置人身检查的结束时间
		model.setStart_time(model.getCheck_Date());// 设置人参检查的开始时间
		List<PHCSMP_BelongingS> belongs = this.getBelong();
		for (PHCSMP_BelongingS belong : belongs) {
			belong.setSuspect_ID(model.getSuspect_ID());// 设置档案编号
			belong.setRoom_ID(model.getRoom_ID());// 设置登记信息的房间编号
		}
		personalCheckService.saveCheckPersonInfor(model);// 保存人身检查记录
		belongingInforService.saveBelongInforList(belongs);// 批量保存随身物品信息
		/*
		 * 统计系统中填写与未填写的字段
		 */
		Class<?> c = Class.forName(PHCSMP_Personal_Check.class.getName());// 通过反射找到该类的字段

		int count = CompleteCheck.IsEqualsNull(model, c);
		int fieldsNumber = CompleteCheck.getFieldsNumber(model, c);

		model.setFill_record(fieldsNumber - count - 3);// 设置已填写的字段数
		model.setTotal_record(fieldsNumber - 3);// 设置应填写的字段
		System.out.println("未填写的字段：" + count);
		System.out.println("总字段：" + fieldsNumber);

		logger.info("用户 " + " 提交用户嫌疑人人身检查信息，时间："
				+ new DateTime().toString("yyyy-MM-dd hh:mm a E"));
		return "addCheckPersonInfor";
	}

	/**
	 * 加载上一个房间的嫌疑人的信息
	 * 
	 * @return
	 */
	public String loadInfor() {
		System.out.println("loadInfor");
		PHCSMP_Staff user = (PHCSMP_Staff) request.getSession().getAttribute(
				"user");

		int roomId = 1;
		PHCSMP_Suspect SuspectInfor = suspectService
				.findInfroByActiveCode(roomId);
		// 将信息从数据库查找到之后，存入session
		request.setAttribute("SuspectInfor", SuspectInfor);

		if (user == null) {
			logger.info("用户 " + " 使用嫌疑人人身检查字段，时间："
					+ new DateTime().toString("yyyy-MM-dd hh:mm a E"));
			return "unLoginState";
		} else {
			logger.info("用户 " + user.getStaff_Name() + " 使用嫌疑人人身检查字段，时间："
					+ new DateTime().toString("yyyy-MM-dd hh:mm a E"));
			return "loadInfor";
		}
	}

	public String unlogin_load() {

		return "unlogin_load";
	}

	// 返回修改人身检查信息
	public String updateInfor() {
		System.out.println("档案编号：" + request.getParameter("Suspect_ID"));
		System.out.println("updateInfor：修改人身检查信息！");
		return "updateInfor";
	}

	public List<PHCSMP_BelongingS> getBelong() {
		return belong;
	}

	public void setBelong(List<PHCSMP_BelongingS> belongs) {
		this.belong = belongs;
	}
}
