package com.minws.tps.shiro.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * Created by unlimited on 2014/4/9.
 */

@TableBind(tableName = "accounts", pkName = "id")
public class Account extends Model<Account> {
    public static Account dao = new Account();

    public Account findByName(String name) {
        return findFirst(
                "select * from accounts where username = ?", name);
    }

    public List<Role> getRoles() {
        return Role.dao.find(
                "select * from roles r, account_role ar " +
                        "where ar.account=? and ar.role=r.id",
                get("id")
        );
    }

    /**
     * 获取用户专属的权限
     * @return
     */
    public List<Permission> getSpecificPermissions() {
        return Permission.dao.find(
                "select * from permissions p, account_permission ap " +
                        "where ap.account=? and ap.permission=p.id",
                get("id"));
    }
}