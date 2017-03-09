package com.haifeiWu.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.entity.PHCSMP_Room;
import com.haifeiWu.service.RoomService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 房间管理Action
 * 
 * @author litt
 * @d2017年3月6日
 */
@Controller
@Scope("prototype")
public class RoomManageAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	private static final long serialVersionUID = 4291137271901380604L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;

	@Autowired
	private RoomService roomService; // 房间信息

	/**
	 * 查询所有的房间信息
	 */
	public String loadInfor() {
		System.out.println("查找所有的房间信息");
		// 获取所有房间的信息
		List<PHCSMP_Room> roomCheckInfo = roomService.findAllRoom();
		if (roomCheckInfo == null) {
			return "NULL";// 处理空的情况
		} else {
			request.setAttribute("roomCheckInfo", roomCheckInfo);
			for (PHCSMP_Room phcsmp_Room : roomCheckInfo) {
				System.out.println(phcsmp_Room.toString());
			}

			return "loadInfor";
		}

		// return "loadInfor";

	}

	@Override
	public void setServletContext(ServletContext application) {
		this.application = application;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
