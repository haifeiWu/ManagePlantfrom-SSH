package com.haifeiWu.action;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.service.UserService;

/**
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
/**
 * Action应继承BaseAction.并指定泛型的类型，可继承request、response、application等属性
 * @author WXY
 *
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<PHCSMP_Staff> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1489295867312851923L;
	/**
	 * Logger的输出地是控制台，此对象只用来调试，具体的日志添加在日志过滤器中完成
	 */
	private Logger logger = Logger.getLogger(UserAction.class);

	@Autowired
	private UserService userService;

	/**
	 * 用户登录功能的实现
	 * 
	 * @return
	 */
	public String login() {
		PHCSMP_Staff user = userService.findUserByStaffNameAndPwd(
				model.getStaff_Name(), model.getPassWord());
		if (user != null) {
			request.getSession().setAttribute("user", user);
			//日志功能
			logger.info("用户 " + user.getStaff_Name() + " 登录系统，时间："
					+ new DateTime().toString("yyyy-MM-dd hh:mm a E"));
			return "login";
		} else {
			addFieldError("loginError", "用户名或密码不正确！");//向前台传值
			return "loginError";
		}
	}

	/**
	 * 用户安全退出
	 * 
	 * @return
	 */
	public String logout() {
		request.getSession().removeAttribute("users");
		//日志的调用
		logger.info("用户 " + " 注销系统，时间："
				+ new DateTime().toString("yyyy-MM-dd hh:mm a E"));
		return "logout";
	}
}
