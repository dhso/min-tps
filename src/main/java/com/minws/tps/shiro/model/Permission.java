package com.minws.tps.shiro.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by unlimited on 2014/4/10.
 */

@TableBind(tableName = "roles", pkName = "id")
public class Permission extends Model<Permission> {
    public static Permission dao = new Permission();

}
