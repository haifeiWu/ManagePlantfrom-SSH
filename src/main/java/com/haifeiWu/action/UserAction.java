package com.haifeiWu.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
			Cookie cookie = new Cookie("ip", request.getRemoteAddr());
			cookie.setMaxAge(24 * 60 * 60 * 7);// 七天
			response.addCookie(cookie);
			return "WEB-INF/jsp/home/main";
		} else {
			request.setAttribute("loginError", "用户名或密码不正确！");
			return "jsp/login";
		}
	}
}
