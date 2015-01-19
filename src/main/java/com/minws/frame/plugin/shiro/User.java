package com.minws.frame.plugin.shiro;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User> {

	private static final long serialVersionUID = 7007361390704117124L;
	public static User dao = new User();

	public User findFirstBy(String whereSql, String userName) {
		User user = User.dao.findFirst("select * from sys_user where " + whereSql, userName);
		return user;
	}

}
