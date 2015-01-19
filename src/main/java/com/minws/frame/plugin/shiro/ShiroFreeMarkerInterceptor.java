package com.minws.frame.plugin.shiro;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class ShiroFreeMarkerInterceptor implements Interceptor {
	public void intercept(ActionInvocation ai) {
		Controller c = ai.getController();
		c.setAttr("hasRole", new HasRoleFreeMarkerMethod());
		c.setAttr("hasAnyRoles", new HasAnyRolesFreeMarkerMethod());
		c.setAttr("hasPermission", new HasPermissionFreeMarkerMethod());
		c.setAttr("isAuthenticated", new AuthenticatedFreeMarkerMethod());
		// 执行正常逻辑
		ai.invoke();
	}
}
