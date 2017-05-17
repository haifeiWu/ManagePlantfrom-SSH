package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_Role;

public interface RoleService {
	List<PHCSMP_Role> findAll();

	void deleteByRoleId(Integer roleId);

	PHCSMP_Role findByRoleId(Integer roleId);

	Integer addRole(PHCSMP_Role role);

	void updateRole(PHCSMP_Role role);
}
