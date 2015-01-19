package com.minws.frame.plugin.shiro;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class Role extends Model<Role> {
	private static final long serialVersionUID = -6684935887199807412L;
	public static Role dao = new Role();

	public List<Role> findAll() {
		List<Role> roles = Role.dao.find("select * from sys_role");
		return roles;
	}

	public List<Role> findUserBy(Long id) {
		String sql = "select * from sys_role sr left join sys_user_role sur on sur.role_id = sr.id and sur.user_id = ? where sr.deleted_at is null";
		List<Role> roles = Role.dao.find(sql, id);
		return roles;
	}

}
