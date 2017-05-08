package com.haifeiWu.action;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haifeiWu.entity.PHCSMP_Role;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.service.UserService;

/**
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */

@Controller
@RequestMapping("/user")
@Scope("prototype")
public class UserAction {
	private static final long serialVersionUID = 1489295867312851923L;
	/**
	 * Logger的输出地是控制台，此对象只用来调试，具体的日志添加在日志过滤器中完成
	 */
	private Logger logger = Logger.getLogger(UserAction.class);

	@Autowired
	private UserService userService;

	/*
	 * protected HttpServletRequest request; protected HttpServletResponse
	 * response; protected ServletContext application;
	 */

	/**
	 * 用户登录功能的实现
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(PHCSMP_Staff staff, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("----------" + staff.toString());
		PHCSMP_Staff user = userService.findUserByStaffNameAndPwd(
				staff.getStaff_Name(), staff.getPassWord());
		if (user != null) {
			// // 日志功能
			// logger.info("用户 " + user.getStaff_Name() + " 登录系统，时间："
			// + new DateTime().toString("yyyy-MM-dd hh:mm a E"));
			// 向客户端输出cookie
			System.out.println(request.getRemoteAddr()+"-----------------------------------------------");
			Cookie cookie = new Cookie("ip", request.getRemoteAddr());
			cookie.setMaxAge(24 * 60 * 60 * 7);// 七天
			response.addCookie(cookie);
			request.getSession().setAttribute("staffname",
					staff.getStaff_Name());
			request.getSession().setAttribute("staff_role",
					staff.getDuties_Name());
			System.out.println("我是user，刚查出来的-------" + user.toString());
			request.setAttribute("user", user);
			System.out.println(staff.getDuties_Name());
			return "WEB-INF/jsp/home/main";
		} else {
			request.setAttribute("loginError", "用户名或密码不正确！");
			return "jsp/login";
		}
	}

	/**
	 * 查询所有的用户信息
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public String loadInfor(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("查找所有的用户信息");
		// 获取所有用户的信息
		List<PHCSMP_Staff> userCheckInfo = userService.findAllStaffs();
		for (PHCSMP_Staff phcsmp_Staff : userCheckInfo) {
			System.out.println(phcsmp_Staff.toString());
		}
		if (userCheckInfo == null) {
			System.out.println("没有值");
			return "NULL";// 处理空的情况
		} else {

			request.setAttribute("userCheckInfo", userCheckInfo);

			return "WEB-INF/jsp/rolemanage/policeList";
		}

	}

	/**
	 * 跳转到添加用户信息页面
	 */
	@RequestMapping(value = "/adduser")
	public String addUser(HttpServletRequest request) {
		List<PHCSMP_Staff> staff = null;
		request.setAttribute("staff", staff);
		findAllRole(request, null);
		return "WEB-INF/jsp/rolemanage/RoleEdit";
	}

	/**
	 * 添加用户信息
	 * */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveUser(PHCSMP_Staff model, HttpServletRequest request) {
		System.out.println("添加用户信息");

		model.setRegistedDate(new DateTime().toString("yyyy-MM-dd HH:mm"));
		model.setIs_FirstLogin("是");
		userService.saveStaff(model);
		return "redirect:/user/load";

	}

	/**
	 * 修改用户信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/updateuser")
	public String updateUser(PHCSMP_Staff model, HttpServletRequest request) {
		System.out.println("修改用户信息-------------------");

		Integer id = Integer.valueOf(request.getParameter("staff_ID"));
		System.out.println(id + "-------------");

		/*
		 * Integer id = model.getStaff_ID(); System.out.println(id +
		 * "-------------------------");
		 */
		userService.deleteByStaffId(id);
		System.out.println(model.getStaff_ID() + model.getStaff_Name()
				+ model.getDuties_Name() + model.getMobile());
		/* userService.updateStaff(model); */
		userService.saveStaff(model);
		return "redirect:/user/load";

	}

	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request) {

		// Integer id=Integer.valueOf(request.getParameter("staff_ID"));
		String name = request.getParameter("staff_Name");
		System.out.println("准备修改---------------" + name);
		List<PHCSMP_Staff> staff = userService.findStaff("Staff_Name", name);
		request.setAttribute("staff", staff);
		for (PHCSMP_Staff phcsmp_Staff : staff) {
			System.out.println(phcsmp_Staff.toString());
		}
		return "WEB-INF/jsp/rolemanage/RoleEdit";

	}

	/*
	 * public String AddStaff() throws IOException { try { String StaffName =
	 * model.getStaff_Name(); // 首次登陆时间 if (model.getRegistedDate().equals(""))
	 * { model.setRegistedDate(new DateTime() .toString("yyyy-MM-dd HH:mm")); }
	 * // 是否第一次登陆 if (model.getIs_FirstLogin().equals("1")) {
	 * model.setIs_FirstLogin("2"); } else { model.setIs_FirstLogin("1"); } //
	 * 更新 if (model != null) { PHCSMP_Staff old =
	 * userService.findByStaffName(StaffName); if (old != null) {// 删除
	 * staffService.deleteStaff(old); ; } // 插入 staffService.saveStaff(model); }
	 * response.getWriter().write("<script>alert('提交成功');</script>");
	 * response.getWriter().flush(); return null; } catch (Exception e) {
	 * response.getWriter().write("<script>alert('提交失败');</script>");
	 * response.getWriter().flush(); return "AddStaffInfor"; } }
	 */

	/**
	 * 删除用户信息
	 * 
	 * @param staff
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteUser(HttpServletRequest request) {
		String staffId = request.getParameter("id");
		System.out.println("staffId" + staffId);

		int id = Integer.parseInt(staffId);
		userService.deleteByStaffId(id);
		System.out.println("删除成功");
		return "WEB-INF/jsp/rolemanage/policeList";
	}

	/**
	 * 根据姓名、工号查询干警信息
	 * 
	 * @param key
	 * @param val
	 */
	@RequestMapping(value = "/searchStaff", method = RequestMethod.POST)
	public String findStaff(HttpServletRequest request) {
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		System.out.println("key=" + key + " " + "val=" + val);
		List<PHCSMP_Staff> list = userService.findStaff(key, val);
		System.out.println("hql语句正确");

		request.setAttribute("userCheckInfo", list);
		return "WEB-INF/jsp/rolemanage/policeList";
	}

	/**
	 * 查询所有职务
	 */
	public List<PHCSMP_Role> findAllRole(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("查找所有的职务");
		// 获取所有职务
		List<PHCSMP_Role> role = userService.findAllRole();
		for (PHCSMP_Role phcsmp_Role : role) {
			System.out.println(phcsmp_Role.toString());
		}
		request.setAttribute("role", role);
		return role;

	}

	@RequestMapping(value = ("/title"))
	public void updateTitle(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Properties pop = new Properties();
		pop.load(this.getClass().getClassLoader()
				.getResourceAsStream("title.properties"));
		String title1 = pop.getProperty("title");
		String title2 = pop.getProperty("name");
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"name\"").append(":").append("\"" + title2 + "\"")
				.append(",").append("\"title\"").append(":")
				.append("\"" + title1 + "\"").append("}");
		resp.getWriter().print(sb);
	}

}
