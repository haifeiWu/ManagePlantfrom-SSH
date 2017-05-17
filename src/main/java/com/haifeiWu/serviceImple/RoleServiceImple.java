package com.haifeiWu.serviceImple;

import java.util.List;

import org.springframework.stereotype.Service;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.entity.PHCSMP_Role;
import com.haifeiWu.service.RoleService;

/**
 * 继承DaoSupportImpl的类在使用DaoSupport中的方法时会比较方便
 * 
 * @author WXY
 * 
 */
@Service("roleService")
public class RoleServiceImple extends DaoSupportImpl<PHCSMP_Role> implements
		RoleService {

	@Override
	public void deleteByRoleId(Integer roleID) {
		deleteByProperty("role_ID", roleID);

	}

	@Override
	public List<PHCSMP_Role> findAll() {

		return findAllInfor();
	}

	@Override
	public PHCSMP_Role findByRoleId(Integer roleId) {
		return findByPropertyName("role_ID", roleId);
	}

	@Override
	public Integer addRole(PHCSMP_Role role) {
		return save(role);
	}

	@Override
	public void updateRole(PHCSMP_Role role) {
		String hql = "update PHCSMP_Role s set s.role_Name=? , s.role_Description=? where s.role_ID=?";
		update(hql, role.getRole_Name(), role.getRole_Description(),
				role.getRole_ID());
	}

}
