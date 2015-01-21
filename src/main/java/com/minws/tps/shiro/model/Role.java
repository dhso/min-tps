package com.minws.tps.shiro.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * Created by unlimited on 2014/4/9.
 */

@TableBind(tableName = "roles", pkName = "id")
public class Role extends Model<Role> {
    public static Role dao = new Role();

    public List<Permission> getPermissions() {
        return Permission.dao.find(
                "select * from permissions p, role_permission rp " +
                        "where rp.role=? and rp.permission=p.id",
                get("id"));
    }

    public List<Role> findAll() {
        return find("select * from roles");
    }
}