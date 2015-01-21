package com.minws.tps.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import com.minws.tps.shiro.model.Account;
import com.minws.tps.shiro.model.Permission;
import com.minws.tps.shiro.model.Role;

import java.util.List;
import java.util.Set;

/**
 * 自实现用户与权限查询. 演示关系，密码用明文存储，因此使用默认 的SimpleCredentialsMatcher.
 */
public class MyShiroRealm extends AuthorizingRealm {

    /**
     * 认证回调函数, 登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        String password = String.valueOf(token.getPassword());

        // 调用操作数据库的方法查询user信息
        Account user = Account.dao.findByName(token.getUsername());
        if (user != null) {
            if (password.equals(user.getStr("password"))) {
                Session session = SecurityUtils.getSubject().getSession();
                session.setAttribute("username", user.getStr("username"));
                return new SimpleAuthenticationInfo(user.getInt("id"),
                        user.getStr("password"), getName());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        int userId = (Integer) principals.fromRealm(getName()).iterator().next();
        Account user = Account.dao.findById(userId);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<Role> roles = user.getRoles();
            for (Role role : roles) {
                info.addRole(role.getStr("name"));

                List<Permission> permissions =  role.getPermissions();
                for (Permission permission : permissions) {
                    info.addStringPermission(permission.getStr("url"));
                }
            }
            List<Permission> permissions =  user.getSpecificPermissions();
            for (Permission permission : permissions) {
                info.addStringPermission(permission.getStr("url"));
            }

            Set<String> strings = info.getStringPermissions();
            for (String string : strings) {
                System.out.println(string);
            }


            return info;
        } else {
            return null;
        }
    }

}
