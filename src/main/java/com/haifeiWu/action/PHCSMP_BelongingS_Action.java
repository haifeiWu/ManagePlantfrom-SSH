package com.haifeiWu.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_BelongingS;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.BelongingInforService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.CompleteCheck;

@Controller
@Scope("prototype")
public class PHCSMP_BelongingS_Action extends BaseAction<PHCSMP_BelongingS> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SuspectService suspectService;
	@Autowired
	private BelongingInforService belongingInforService;

	/**
	 * 添加用户随身物品检查记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addBelongingInfor() throws Exception {

		// 通过反射加载身物品检查记录的类
		Class<?> c = Class.forName(PHCSMP_BelongingS.class.getName());

		int count = CompleteCheck.IsEqualsNull(model, c);
		int fieldsNumber = CompleteCheck.getFieldsNumber(model, c);

		model.setFill_record(fieldsNumber - count - 3);// 设置已填写的字段数
		model.setTotal_record(fieldsNumber - 3);// 设置应填写的字段
		System.out.println("未填写的字段：" + count);
		System.out.println("总字段：" + fieldsNumber);

		// BelongingInforService service = new BelongingInforServiceImple();
		// 保存信息到数据库
		belongingInforService.saveBelongingInfor(model);

		System.out.println("addBelongingInfor:haifeisi");

		return "addBelongingInfor";
	}

	public String loadInfor() {
		PHCSMP_Suspect SuspectInfor = suspectService.findInfroByActiveCode(2);
		if (SuspectInfor == null) {
			return "NULL";
		}
		// System.out.println("name：" + SuspectInfor.getSuspect_Name());
		// 将信息从数据库查找到之后，存入session，更新session
		request.setAttribute("SuspectInfor", SuspectInfor);
		System.out.println("PHCSMP_BelongingS_Action：loadInfor");
		return "loadInfor";
	}
}
