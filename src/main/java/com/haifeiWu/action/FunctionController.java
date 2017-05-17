package com.haifeiWu.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haifeiWu.dto.FunctionGroup;
import com.haifeiWu.entity.PHCSMP_Function;
import com.haifeiWu.entity.PHCSMP_RoleFunction;
import com.haifeiWu.service.FunctionService;
import com.haifeiWu.service.RoleFunctionService;

@Controller
@Scope("prototype")
@RequestMapping("/func")
public class FunctionController {
	@Autowired
	private FunctionService functionService;
	@Autowired
	RoleFunctionService roleFunctionService;
	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/editFunc", method = RequestMethod.GET)
	public String editFuc(HttpServletRequest request) {
		// 为那个角色创建权限
		Integer roleId = Integer.valueOf(request.getParameter("roleId"));
		request.setAttribute("roleId", roleId);
		// 创建多个功能组
		List<PHCSMP_Function> funcs = functionService.findParentFunctions();
		List<FunctionGroup> groups = new ArrayList<FunctionGroup>();
		for (PHCSMP_Function function : funcs) {
			// 使用dto传输数据
			FunctionGroup functionGroup = new FunctionGroup();
			functionGroup.setTitleFunction(function);
			functionGroup.setChildFunctions(functionService
					.findChildFunctions(function.getFunction_id()));
			groups.add(functionGroup);
		}
		request.setAttribute("groups", groups);
		request.setAttribute("parent", funcs);
		// 更新,加载之前的权限信息
		List<PHCSMP_RoleFunction> oldFuc = roleFunctionService
				.findByRoleId(roleId);
		if (oldFuc != null) {
			request.setAttribute("oldFuc", oldFuc);
		}
		// logger.debug("------------------AlterFuc------------------" + roleId
		// + " " + oldFuc);
		return "WEB-INF/jsp/rolemanage/editFunc";
	}
}
