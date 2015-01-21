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

    public void index() {
        render("login.ftl");
    }

    public void login() {
    	render("login.ftl");
    }

    public void signin() {
		String username = getPara("username");
		String password = getPara("password");
		String rememberMe = getPara("rememberMe");
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		try {
			currentUser.login(token);
			redirect("/");
		} catch (Exception e) {
			// 登录失败
			e.printStackTrace();
			forwardAction("/login");
		}
	}

	public void signout() {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			currentUser.logout();
		}
		forwardAction("/security/login");
	}

    public void err404() {
        render("error/404.ftl");
    }

    public void err500() {
        render("error/500.ftl");
    }
}
