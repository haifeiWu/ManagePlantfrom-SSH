package com.haifeiWu.service;

import java.util.List;

import com.haifeiWu.entity.PHCSMP_RoleFunction;

public interface RoleFunctionService {
	List<PHCSMP_RoleFunction> findByRoleId(Integer roleId);

	void updateRoleFuction(String funcId, Integer roleId);

	void deleteByRoleId(Integer roleID);

}
