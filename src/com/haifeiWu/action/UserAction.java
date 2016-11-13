package com.haifeiWu.action;

import com.haifeiWu.base.BaseAction;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.service.UserService;
import com.haifeiWu.serviceImple.UserServiceImple;

/**
 * 
 * @author wuhaifei
 *
 * @date 2016年8月9日
 */
public class UserAction extends BaseAction<PHCSMP_Staff> {
	
	/**
	 * 用户登录功能的实现
	 * @return
	 */
	public String login(){
		UserService userService = new UserServiceImple();
		PHCSMP_Staff user = userService.findUserByStaffNameAndPwd(model.getStaff_Name(),model.getPassWord());
		
		if(user != null){
			request.getSession().setAttribute("user", user);
			System.out.println("用户登录……\n\n");
			return "login";
		}else{
			addFieldError("loginError", "用户名或密码不正确！");
			return "loginError";
		}
	}
	
	/**
	 * 用户安全退出
	 * @return
	 */
	public String logout(){
		request.getSession().removeAttribute("users");
		System.out.println("tuichu");
		return "logout";
	}

}	
