package com.minws.frame.plugin.shiro.model;

import cn.dreampie.ValidateKit;
import cn.dreampie.sqlinxml.SqlKit;
import cn.dreampie.tablebind.TableBind;
import cn.dreampie.web.model.Model;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by wangrenhui on 14-1-3.
 */
@TableBind(tableName = "sec_user")
public class User extends Model<User> {
  public static User dao = new User();

  public User addUserInfo(UserInfo userInfo) {
    if (ValidateKit.isNullOrEmpty(userInfo)) {
      userInfo = new UserInfo();
      userInfo.set("user_id", this.get("id"));
    }
    userInfo.set("created_at", new Date());
    userInfo.save();
    return this;
  }

  public User addRole(Role role) {
    if (ValidateKit.isNullOrEmpty(role)) {
      role = Role.dao.findFirstBy("`role`.value='R_USER'");
      if (ValidateKit.isNullOrEmpty(role)) {
        throw new NullPointerException("角色不存在");
      }
    }
    UserRole userRole = new UserRole();
    userRole.set("user_id", this.get("id"));
    userRole.set("role_id", role.get("id"));
    userRole.save();
    return this;
  }


  public Role getRole() {
    return Role.dao.findById(getRoleId());
  }

  public Long getRoleId() {
    return UserRole.dao.findFirstBy("`userRole`.user_id=?", this.get("id")).get("role_id");
  }

  public List<Role> getRoleChildren() {
    if (this.get("roleChildren") == null) {
      //查询当前用户的角色
      UserRole userRole = UserRole.dao.findFirstBy("`userRole`.user_id=" + this.get("id"));
      //当前用户的子集角色
      List<Role> roles = Role.dao.findChildrenById("`role`.deleted_at is null", userRole.get("role_id"));
      this.put("roleChildren", roles);
    }
    return this.get("roleChildren");
  }

  public long[] getRoleChildrenIds() {
    if (this.get("roleChildrenIds") == null) {
      List<Role> roles = getRoleChildren();
      long[] roleIds = new long[roles.size()];
      if (roles != null) {
        int i = 0;
        for (Role role : roles) {
          roleIds[i] = role.get("id");
          i++;
        }
      }
      this.put("roleChildrenIds", roleIds);
    }
    return (long[]) this.get("roleChildrenIds");
  }

  public String getRoleChildrenIdsStr() {
    if (this.get("roleChildrenIdsStr") == null) {
      long[] ids = getRoleChildrenIds();
      ArrayUtils.toString(ids, ",");
      String idsStr = "";
      int i = 0;
      int size = ids.length;
      for (long id : ids) {
        idsStr += id + "";
        if (i < size - 1) {
          idsStr += ",";
        }
        i++;
      }
      this.put("roleChildrenIdsStr", idsStr);
    }
    return this.get("roleChildrenIdsStr");
  }

  public Page<User> paginateInfoBy(int pageNumber, int pageSize, String where, Object... paras) {
    return dao.paginate(pageNumber, pageSize, SqlKit.sql("user.findInfoBySelect"), SqlKit.sql("user.findInfoByFrom") + getWhere(where), paras);
  }

  public User findFirstInfoBy(String where, Object... paras) {
    return dao.findFirst(SqlKit.sql("user.findInfoBySelect") + SqlKit.sql("user.findInfoByFrom") + getWhere(where), paras);
  }

}
