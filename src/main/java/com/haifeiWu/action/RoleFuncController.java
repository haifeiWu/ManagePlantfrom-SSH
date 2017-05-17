package com.haifeiWu.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.haifeiWu.service.RoleFunctionService;

@Controller
@Scope("prototype")
@RequestMapping("/roleFunc")
public class RoleFuncController {
	@Autowired
	private RoleFunctionService roleFunctionService;
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 保存页面填写的权限
	 * 
	 * @return
	 */
	@RequestMapping("/commitFuc")
	// public String commitFuc(HttpServletRequest request) {
	public String commitFuc(@RequestParam("functionId") String funcs,
			@RequestParam("roleId") Integer roleId) {
		roleFunctionService.updateRoleFuction(funcs, roleId);
		return "redirect:/role/loadRole";
	}

}
