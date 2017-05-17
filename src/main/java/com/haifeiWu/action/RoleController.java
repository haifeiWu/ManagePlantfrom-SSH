package com.haifeiWu.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.haifeiWu.entity.PHCSMP_Role;
import com.haifeiWu.service.RoleFunctionService;
import com.haifeiWu.service.RoleService;

@Controller
@Scope("prototype")
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleFunctionService roleFunctionService;
	@Autowired
	private RoleService roleService;

	/**
	 * 角色管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loadRole")
	public String loadRole(HttpServletRequest request) {
		request.setAttribute("role", roleService.findAll());
		return "WEB-INF/jsp/rolemanage/roleManage";
	}

	/**
	 * 跳转到添加角色页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toEdit")
	public String editRole() {
		return "WEB-INF/jsp/rolemanage/editRole";
	}

	/**
	 * 添加角色或更新角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/updateRole")
	public String updateRole(PHCSMP_Role role) {
		if (role.getRole_ID() == null) {
			// 添加
			Integer roleId = roleService.addRole(role);
			return "redirect:/func/editFunc?roleId=" + roleId;
		} else {
			// 更新角色，将角色信息放在前台
			roleService.updateRole(role);
			return "redirect:/role/loadRole";
		}
	}

	/**
	 * 对某个角色的操作
	 * 
	 * @param request
	 * @param role_ID
	 * @param operator
	 *            1修改 2删除 3查看权限
	 * @return
	 */
	@RequestMapping(value = "/operator")
	public String operator(HttpServletRequest request,
			@RequestParam("roleId") Integer roleId,
			@RequestParam("operator") Integer operator) {

		switch (operator) {
		case 1:
			// 更新角色，将角色信息放在前台
			request.setAttribute("role", roleService.findByRoleId(roleId));
			return "WEB-INF/jsp/rolemanage/editRole";
		case 2:
			// 删除角色
			roleService.deleteByRoleId(roleId);
			// 删除该角色相关的func
			roleFunctionService.deleteByRoleId(roleId);
			return "redirect:/role/loadRole";
		case 3:
			// 修改权限
			roleFunctionService.findByRoleId(roleId);
			return "redirect:/func/editFunc?roleId=" + roleId;
		}
		return "";
	}

}
