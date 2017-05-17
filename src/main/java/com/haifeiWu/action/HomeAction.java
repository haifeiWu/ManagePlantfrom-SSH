package com.haifeiWu.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haifeiWu.dto.FunctionGroup;
import com.haifeiWu.entity.PHCSMP_Function;
import com.haifeiWu.entity.PHCSMP_RoleFunction;
import com.haifeiWu.entity.PHCSMP_Staff;
import com.haifeiWu.service.FunctionService;
import com.haifeiWu.service.RoleFunctionService;
import com.haifeiWu.utils.PropertiesReadUtils;
import com.haifeiWu.utils.PropertiesWriteUtils;

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
	private Logger logger = Logger.getLogger(HomeAction.class);
	@Autowired
	private RoleFunctionService roleFunctionService;
	@Autowired
	private FunctionService functionService;

	@RequestMapping(value = "/top")
	public String top(HttpServletRequest request)
			throws UnsupportedEncodingException {
		// 读取配置文件中的公安局名称
		String title = PropertiesReadUtils.getTitleString("title");
		String name = PropertiesReadUtils.getTitleString("name");
		String flag = title.substring(0, 1);
		if (flag != null && flag.equals("\\")) {
			title = PropertiesWriteUtils.ascii2Native(title);
			name = PropertiesWriteUtils.ascii2Native(name);
			System.out.println(title + "...." + name);
		}
		request.setAttribute("title", title);
		request.setAttribute("name", name);
		return "WEB-INF/jsp/home/top";
	}

	/**
	 * 通过left入口控制权限
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/left")
	public String left(HttpServletRequest request) {
		// 维护user数据
		PHCSMP_Staff user = (PHCSMP_Staff) request.getAttribute("user");
		if (user == null) {
			user = (PHCSMP_Staff) request.getSession().getAttribute("user");
		}
		request.setAttribute("user", user);
		// 通过用户查找角色，并将功能封装在功能组dto中,传输
		List<PHCSMP_RoleFunction> roleFuncs = roleFunctionService
				.findByRoleId(user.getRole_Id());
		// 通过这些功能查到父菜单
		List<FunctionGroup> groups = new ArrayList<FunctionGroup>();
		Set<Integer> fatherMenu = new HashSet<Integer>();// 保存父菜单的节点
		for (PHCSMP_RoleFunction roleFunc : roleFuncs) {
			PHCSMP_Function func = functionService.findByFuncId(roleFunc
					.getFunctionId());
			// 将子和父分别存到groups中
			Integer fatherId = func.getFunction_parentId();
			if (fatherMenu.add(fatherId)) {// 是一个新的父级菜单
				PHCSMP_Function fatherFuc = functionService
						.findByFuncId(fatherId);
				FunctionGroup group = new FunctionGroup();
				group.setTitleFunction(fatherFuc);
				ArrayList<PHCSMP_Function> childs = new ArrayList<PHCSMP_Function>();
				childs.add(func);
				group.setChildFunctions(childs);
				groups.add(group);
			} else {// 父级菜单已存在，更新即可
				// 迭代父级菜单，找到当前子菜单的父菜单group对象
				for (FunctionGroup group : groups) {
					if (group.getTitleFunction().getFunction_id() == fatherId) {
						group.getChildFunctions().add(func);
						break;
					}
				}
			}
		}
		logger.debug("----------------------------" + groups);
		request.setAttribute("groups", groups);
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

		return "WEB-INF/jsp/home/index";

	}

	@RequestMapping(value = "/main")
	public String main(HttpServletRequest request) {
		// 读取配置文件中的公安局名称
		String title = PropertiesReadUtils.getTitleString("title");
		request.setAttribute("title", title);
		return "WEB-INF/jsp/home/main";
	}
}
