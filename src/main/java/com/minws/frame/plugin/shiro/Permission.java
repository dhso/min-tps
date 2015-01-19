package com.minws.frame.plugin.shiro;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class Permission extends Model<Permission> {

	private static final long serialVersionUID = 7007361390704117124L;
	public static Permission dao = new Permission();

	public List<Permission> findByRole(Object role_id) {
		String sql = "select sp.deleted_at as deleted_at,sp.url as url,sp.value as value from sys_role_permission srp left join sys_permission sp on sp.id = srp.permission_id where srp.role_id = ?";
		List<Permission> list = Permission.dao.find(sql, role_id);
		return list;

	}
}
