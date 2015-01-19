package com.minws.frame.plugin.shiro;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.dreampie.shiro.core.JdbcAuthzService;
import cn.dreampie.shiro.core.handler.AuthzHandler;
import cn.dreampie.shiro.core.handler.JdbcPermissionAuthzHandler;

public class MyJdbcAuthzService implements JdbcAuthzService {
	@Override
	public Map<String, AuthzHandler> getJdbcAuthz() {
		// 加载数据库的url配置
		// 按长度倒序排列url
		Map<String, AuthzHandler> authzJdbcMaps = Collections.synchronizedMap(new TreeMap<String, AuthzHandler>(new Comparator<String>() {
			public int compare(String k1, String k2) {
				int result = k2.length() - k1.length();
				if (result == 0) {
					return k1.compareTo(k2);
				}
				return result;
			}
		}));
		// 遍历角色
		List<Role> roles = Role.dao.findAll();
		List<Permission> permissions = null;
		for (Role role : roles) {
			// 角色可用
			if (role.getDate("daleted_at") == null) {
				permissions = Permission.dao.findByRole(role.get("id"));
				// 遍历权限
				for (Permission permission : permissions) {
					// 权限可用
					if (permission.getDate("deleted_at") == null) {
						if (permission.getStr("url") != null && !permission.getStr("url").isEmpty()) {
							authzJdbcMaps.put(permission.getStr("url"), new JdbcPermissionAuthzHandler(permission.getStr("value")));
						}
					}
				}
			}
		}
		return authzJdbcMaps;
	}
}