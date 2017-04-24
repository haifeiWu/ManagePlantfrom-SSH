package com.haifeiWu.action;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haifeiWu.utils.PropertiesReadUtils;

/**
 * 主页面 的设计
 * 
 * @author wuhaifei
 * 
 * @date 2016年8月9日
 */
@Controller
@RequestMapping("/home")
@Scope("prototype")
public class HomeAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2941802033175754434L;

	@RequestMapping(value = "/top")
	public String top(HttpServletRequest request) {
		// 读取配置文件中的公安局名称
		String title = PropertiesReadUtils.getTitleString("title");
		String name = PropertiesReadUtils.getTitleString("name");
		request.setAttribute("title", title);
		request.setAttribute("name", name);
		return "WEB-INF/jsp/home/top";
	}

	@RequestMapping(value = "/left")
	public String left(HttpServletRequest request) {
		return "WEB-INF/jsp/home/left";
	}

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println("--------进到欢迎页面后台action-----");
		// 向客户端输出cookie
		Cookie cookie = new Cookie("ip", request.getRemoteAddr());
		cookie.setMaxAge(24 * 60 * 60 * 7);// 七天
		response.addCookie(cookie);
		// 读取websocket的路径
		request.setAttribute("webSocket",
				PropertiesReadUtils.getRecordConfString("webSocket"));
		return "jsp/index";
	}

	@RequestMapping(value = "/main")
	public String main(HttpServletRequest request) {
		// 读取配置文件中的公安局名称
		String title = PropertiesReadUtils.getTitleString("title");
		request.setAttribute("title", title);
		return "WEB-INF/jsp/home/main";
	}
}
