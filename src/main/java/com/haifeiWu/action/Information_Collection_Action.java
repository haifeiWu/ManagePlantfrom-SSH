package com.haifeiWu.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haifeiWu.entity.PHCSMP_Dic_Collection_Item;
import com.haifeiWu.entity.PHCSMP_Information_Collection;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.InformationCollectionService;
import com.haifeiWu.service.LeaveRecodService;
import com.haifeiWu.service.PersonalCheckService;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.service.UserService;
import com.haifeiWu.utils.CompleteCheck;

/**
 * 信息采集的action
 * 
 * @author wuhaifei
 * @d2016年8月14日
 */
@Controller
@RequestMapping("/collect")
@Scope("prototype")
public class Information_Collection_Action {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	@Autowired
	private InformationCollectionService informationCollectionService;
	@Autowired
	private SuspectService suspectService;
	@Autowired
	private ActivityRecordService activityRecordService;
	@SuppressWarnings("unused")
	@Autowired
	private LeaveRecodService leaveRecodService;
	@Autowired
	private RoomService roomService;
	// 嫌疑人的人身检查信息
	@Autowired
	private PersonalCheckService personalCheckService;

	// 保存信息
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addInformationCollection(PHCSMP_Information_Collection model,
			HttpServletRequest request) throws IOException {
		try {
			// 维护进出门的标志位
			int roomId = roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID();
			String staff_ID = request.getParameter("staff_ID");
			String suspectId = model.getSuspect_ID();
			if (model != null) {
				model.setIc_EndTime(new DateTime().toString("yyyy-mm-dd HH:mm"));
				model.setRoom_ID(roomId);
				model.setStaff_ID(staff_ID);
				System.out.println(staff_ID+"999999999999999999999999999999999999999999999999999999999");
				request.setAttribute("staff_ID", staff_ID);
				fullCheck(model);
				PHCSMP_Information_Collection old = informationCollectionService
						.findInforBySuspetcId(suspectId);
				if (old != null) {// 删除
					informationCollectionService.deleteInforCollect(old);
				}
				// 插入
				informationCollectionService.saveCollectionInfor(model);
			}
			return "redirect:/home/index";
		} catch (Exception e) {
			// response.getWriter().write("<script>alert('提交失败，请重新提交');</script>");
			// response.getWriter().flush();
			request.setAttribute("error", "error");
			request.setAttribute("informatCollect", model);
			return "redirect:/load";
		}

	}

	// 加载信息，
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public String IM_loadInfor(@RequestParam("suspectID") String suspectId,
			HttpServletRequest request) throws IOException {// 注意处理房间号找不到异常，或者嫌疑人房间号为空的异常
		// 维护进出门的标志位
		try {
			int roomId = roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID();
			request.setAttribute("roomId", roomId);
			PHCSMP_Suspect SuspectInfor = suspectService
					.findBySuspetcId(suspectId);
			List<PHCSMP_Dic_Collection_Item> collectionItem = informationCollectionService
					.findAllCollectionItem();
			List<PHCSMP_Staff> staff = userService.findAllStaffs();
			request.setAttribute("staff", staff);
			// 如果再次进入该房间，显示之前填写的信息
			PHCSMP_Information_Collection collectInfor = informationCollectionService
					.findInforBySuspetcId(suspectId);
			if (collectInfor != null)
				request.setAttribute("informatCollect", collectInfor);
			// 如果之前填写过，将之前填写的显示在页面上
			PHCSMP_Information_Collection informatCollect = (PHCSMP_Information_Collection) request
					.getAttribute("informatCollect");
			if (informatCollect != null)
				request.setAttribute("informatCollect", informatCollect);

			request.setAttribute("start_Time",
					new DateTime().toString("yyyy-MM-dd HH:mm"));
			request.setAttribute("SuspectInfor", SuspectInfor);
			// 人身安全检查
			request.setAttribute("collectionItem", collectionItem);
			suspectService.updateSwitch(1, suspectId);
			// 判断进度条
			if (personalCheckService.findInforBySuspetcId(suspectId) != null) {
				request.setAttribute("personalCheck", 1);
			}
			if (activityRecordService.selectActivityRecordInfor(suspectId)
					.size() != 0) {
				request.setAttribute("activityRecord", 1);
			}
			return "WEB-INF/jsp/recordInfor/collect";
		} catch (Exception e) {
			// 提示可能是房间、读卡器等设备配置错误
			// response.getWriter()
			// .write("<script type='text/javascript'>alert('加载失败，可能是房间或读卡设备配置错误，修改配置后刷新页面');</script>");
			// response.getWriter().flush();
			// 转到
			request.setAttribute("error", "error");
			return "redirect:/home/index";
		}

	}

	private void fullCheck(PHCSMP_Information_Collection model)
			throws ClassNotFoundException {
		Class<?> c = Class.forName(PHCSMP_Information_Collection.class
				.getName());

		int count = CompleteCheck.IsEqualsNull(model, c);
		int fieldsNumber = CompleteCheck.getFieldsNumber(model, c);
		model.setFill_record(fieldsNumber - count - 2 - 1);// 设置已填写的字段数
		model.setTotal_record(fieldsNumber - 3);// 设置应填写的字段
		System.out.println("未填写的字段：" + count);
		System.out.println("总字段：" + (fieldsNumber - 3));
		System.out.println("房间号：" + model.getRoom_ID());
	}

}
