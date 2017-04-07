package com.haifeiWu.action;

import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_Activity_Record;
import com.haifeiWu.entity.PHCSMP_Dic_Collection_Item;
import com.haifeiWu.entity.PHCSMP_Information_Collection;
import com.haifeiWu.entity.PHCSMP_Leave_Record;
import com.haifeiWu.entity.PHCSMP_Personal_Check;
import com.haifeiWu.entity.PHCSMP_Suspect;
import com.haifeiWu.service.ActivityRecordService;
import com.haifeiWu.service.InformationCollectionService;
import com.haifeiWu.service.LeaveRecodService;
import com.haifeiWu.service.PersonalCheckService;
import com.haifeiWu.service.RoomService;
import com.haifeiWu.service.SuspectService;
import com.haifeiWu.utils.CompleteCheck;

/**
 * 信息采集的action
 * 
 * @author wuhaifei
 * @d2016年8月14日
 */
@Controller
@Scope("prototype")
public class Information_Collection_Action extends
		BaseAction<PHCSMP_Information_Collection> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private InformationCollectionService informationCollectionService;
	@Autowired
	private SuspectService suspectService;
	@Autowired
	private ActivityRecordService activityRecordService;
	@Autowired
	private LeaveRecodService leaveRecodService;
	@Autowired
	private RoomService roomService;
	// 嫌疑人的人身检查信息
	@Autowired
	private PersonalCheckService personalCheckService;

	// 保存信息
	public String addInformationCollection() throws IOException {
		try {
			// 维护进出门的标志位
			int roomId = roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID();
			String suspectId = suspectService.findByRoomID(roomId)
					.getSuspect_ID();
			if (model != null) {
				model.setIc_EndTime(new DateTime().toString("yyyy-mm-dd HH:mm"));
				model.setRoom_ID(roomId);
				fullCheck();
				PHCSMP_Information_Collection old = informationCollectionService
						.findInforBySuspetcId(suspectId);
				if (old != null) {// 删除
					informationCollectionService.deleteInforCollect(old);
				}
				// 插入
				informationCollectionService.saveCollectionInfor(model);
			}
			// 提示成功
			// response.getWriter().write("<script>alert('后台提交成功');</script>");
			// response.getWriter().flush();
			// return "success";
			// 主动失败
			// throw new Exception();
		} catch (Exception e) {
			response.getWriter().write("<script>alert('提交失败，请重新提交');</script>");
			response.getWriter().flush();
			request.setAttribute("informatCollect", model);
			return "chainLoadInfor";
		}
		return "success";
	}

	// 加载信息，
	public String loadInfor() throws IOException {// 注意处理房间号找不到异常，或者嫌疑人房间号为空的异常
		// 维护进出门的标志位
		try {

			int roomId = roomService.findbyIp(request.getRemoteAddr())
					.getRoom_ID();
			request.setAttribute("roomId", roomId);

			// 嫌疑人信息
			// PHCSMP_Suspect SuspectInfor =
			// suspectService.findByRoomID(roomId);
			// String suspectId = SuspectInfor.getSuspect_ID();
			String suspectId = (String) request.getAttribute("suspectID");
			PHCSMP_Suspect SuspectInfor = suspectService
					.findBySuspetcId(suspectId);
			List<PHCSMP_Dic_Collection_Item> collectionItem = informationCollectionService
					.findAllCollectionItem();
			// 信息采集
			PHCSMP_Information_Collection informatCollect = (PHCSMP_Information_Collection) request
					.getAttribute("informatCollect");
			if (informatCollect != null)
				request.setAttribute("informatCollect", informatCollect);
			request.setAttribute("start_Time",
					new DateTime().toString("yyyy-MM-dd HH:mm"));
			request.setAttribute("SuspectInfor", SuspectInfor);
			// 人身安全检查
			PHCSMP_Personal_Check personal_Check = personalCheckService
					.findInforBySuspetcId(SuspectInfor.getSuspect_ID());
			if (personal_Check != null) {
				request.setAttribute("personal_Check", personal_Check);
			}
			request.setAttribute("collectionItem", collectionItem);
			suspectService.updateSwitch(1, SuspectInfor.getSuspect_ID());

			// 判断进度条
			PHCSMP_Suspect suspect = suspectService.findBySuspetcId(suspectId);
			PHCSMP_Personal_Check personalCheck = personalCheckService
					.findInforBySuspetcId(suspectId);
			PHCSMP_Information_Collection informationCollection = informationCollectionService
					.findInforBySuspetcId(suspectId);
			List<PHCSMP_Activity_Record> activityRecordlist = activityRecordService
					.selectActivityRecordInfor(suspectId);
			PHCSMP_Leave_Record leaveRecord = leaveRecodService
					.findInforBySuspetcId(suspectId);
			request.setAttribute("suspect", suspect);
			request.setAttribute("personalCheck", personalCheck);
			request.setAttribute("informationCollection", informationCollection);
			request.setAttribute("activityRecord", activityRecordlist);
			request.setAttribute("leaveRecord", leaveRecord);

		} catch (Exception e) {
			// 提示可能是房间、读卡器等设备配置错误
			response.getWriter()
					.write("<script type='text/javascript'>alert('加载失败，可能是房间或读卡设备配置错误，修改配置后刷新页面');</script>");
			response.getWriter().flush();
			// 转到
			return "success";
		}
		return "loadInfor";
	}

	// public String unlogin_load() {
	//
	// return "unlogin_load";
	// }

	// 返回修改信息采集信息
	public String updateInfor() {
		System.out.println("档案编号：" + request.getParameter("Suspect_ID"));
		System.out.println("updateInfor：修改信息采集信息！");
		return "updateInfor";
	}

	private void fullCheck() throws ClassNotFoundException {
		Class<?> c = Class.forName(PHCSMP_Information_Collection.class
				.getName());

		int count = CompleteCheck.IsEqualsNull(model, c);
		int fieldsNumber = CompleteCheck.getFieldsNumber(model, c);
		model.setFill_record(fieldsNumber - count - 3);// 设置已填写的字段数
		model.setTotal_record(fieldsNumber - 3);// 设置应填写的字段
		System.out.println("未填写的字段：" + count);
		System.out.println("总字段：" + (fieldsNumber - 3));
		System.out.println("房间号：" + model.getRoom_ID());
	}

}
