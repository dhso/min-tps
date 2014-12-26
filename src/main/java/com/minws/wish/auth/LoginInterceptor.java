package com.minws.wish.auth;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

/**
 * LoginInterceptor
 */
public class LoginInterceptor implements Interceptor {

	public void intercept(ActionInvocation ai) {
		Controller controller = ai.getController();
		String sso_token = controller.getCookie("sso_access_token", "");
		HttpServletRequest request = controller.getRequest();
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
		String para = request.getQueryString();
		url += (null == para) ? "" : "?" + para;
		if (sso_token.isEmpty()) {
			controller.redirect("login?goto=" + url);
		} else {
			ai.invoke();
		}
	}
}
