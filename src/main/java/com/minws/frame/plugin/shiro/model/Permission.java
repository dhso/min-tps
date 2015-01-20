package com.minws.frame.plugin.shiro.model;

import cn.dreampie.sqlinxml.SqlKit;
import cn.dreampie.tablebind.TableBind;
import cn.dreampie.tree.TreeNode;
import cn.dreampie.web.model.Model;

import java.util.List;

/**
 * Created by wangrenhui on 14-1-3.
 */
@TableBind(tableName = "sec_permission")
public class Permission extends Model<Permission> implements TreeNode<Permission> {
  public static Permission dao = new Permission();

  public long getId() {
//        return this.id;
    return this.getLong("id");
  }

  public long getParentId() {
//        return this.parentId;
    return this.getLong("pid");
  }

  public List<Permission> getChildren() {
    return this.get("children");
  }

  public void setChildren(List<Permission> children) {
    this.put("children", children);
  }

  public List<Permission> findByRole(String where, Object... paras) {

    return find("select * from `sec_permission` left join `sec_role_permission` on `sec_role_permission`.permission_id = `sec_permission`.id " + getWhere(where), paras);
  }
}
