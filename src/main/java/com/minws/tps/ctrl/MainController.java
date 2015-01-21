package com.minws.tps.ctrl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

@ControllerBind(controllerKey = "/", viewPath = "/")
public class MainController extends Controller {

	@RequiresAuthentication
	public void index() {
		render("index.jsp");
	}

	public void login() {
		render("login.html");
	}


	public void head() {
		render("head.html");
	}

	public void west() {
		render("west.html");
	}

	public void center() {
		render("center.html");
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
		render("/auth/error/404.html");
	}

	public void err500() {
		render("/auth/error/500.html");
	}
}