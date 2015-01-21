package com.minws.tps.ctrl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by unlimited on 2014/4/9.
 */

@ControllerBind(controllerKey = "/security", viewPath = "/security")
public class SecurityController extends Controller {

	//默认登录页面
    public void index() {
        render("login.ftl");
    }

    //登录页面
    public void login() {
    	render("login.ftl");
    }

    //登录Action
    public void signin() {
		String username = getPara("username");
		String password = getPara("password");
		Boolean rememberMe = "on".equalsIgnoreCase(getPara("rememberMe","off"));
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		try {
			currentUser.login(token);
			redirect("/");
		} catch (Exception e) {
			// 登录失败
			String esn = e.getClass().getSimpleName();
			if("IncorrectCredentialsException".equalsIgnoreCase(esn)){
				setAttr("errorMsg", "用户名或者密码不正确！");
			}
			forwardAction("/security/login");
		}
	}

    //登出Action
	public void signout() {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			currentUser.logout();
		}
		forwardAction("/security/login");
	}

	public void err401() {
		setAttr("msg", "401 Unauthorized");
		setAttr("success", false);
		renderJson();
	}

	public void err403() {
		setAttr("msg", "403 Forbidden");
		setAttr("success", false);
		renderJson();
	}
	
    public void err404() {
        render("error/404.ftl");
    }

    public void err500() {
        render("error/500.ftl");
    }
}
