package com.haifeiWu.serviceImple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.entity.PHCSMP_RoleFunction;
import com.haifeiWu.service.RoleFunctionService;

@Service("roleFunctionService")
public class RoleFunctionServiceImple extends
		DaoSupportImpl<PHCSMP_RoleFunction> implements RoleFunctionService {

	@Override
	public void deleteByRoleId(Integer roleID) {
		deleteByProperty("roleId", roleID);

	}

	@Override
	public List<PHCSMP_RoleFunction> findByRoleId(Integer roleId) {
		return findListByPropertyName("roleId", roleId);
	}

	@Override
	public void updateRoleFuction(String funcId, Integer roleId) {
		// 删除旧的
		deleteByRoleId(roleId);
		// 保存新的
		List<PHCSMP_RoleFunction> list = new ArrayList<PHCSMP_RoleFunction>();
		String[] s = funcId.split(",");
		for (String f : s) {
			PHCSMP_RoleFunction model = new PHCSMP_RoleFunction();
			model.setFunctionId(Integer.valueOf(f));
			model.setRoleId(roleId);
			list.add(model);
		}
		saveBatch(list);
	}

}
