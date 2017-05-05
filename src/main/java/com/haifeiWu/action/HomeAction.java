package com.haifeiWu.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

	@RequestMapping(value = "/top")
	public String top(HttpServletRequest request)
			throws UnsupportedEncodingException {
		// 读取配置文件中的公安局名称
		String title = new String(PropertiesReadUtils.getTitleString("title")
				.getBytes("ISO-8859-1"), "utf-8");
		String name = new String(PropertiesReadUtils.getTitleString("name")
				.getBytes("ISO-8859-1"), "utf-8");
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
		// Properties prop = new Properties();
		// InputStream in = this.getClass().getResourceAsStream(
		// "/recordConf.properties");// path是录播设备的配置文件
		// try {
		// prop.load(in);
		// } catch (IOException e) {
		// throw new RuntimeException();
		// }
		// String webSocket = prop.getProperty("webSocket");
		String remoteServerIP = PropertiesReadUtils
				.getRecordConfString("remoteServerIP");
		String remoteServerPort = PropertiesReadUtils
				.getRecordConfString("remoteServerPort");
		System.out.println("-----------------" + remoteServerIP);
		System.out.println("-----------------" + remoteServerPort);
		// String webSocket =
		// PropertiesReadUtils.getRecordConfString("webSocket");

		String webSocket = PropertiesReadUtils.getRecordConfString("webSocket");
		request.setAttribute("webSocket", webSocket);
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
