package com.minws.tps.ctrl;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

@ControllerBind(controllerKey = "/", viewPath = "/tps")
public class MainController extends Controller {

	@RequiresAuthentication
	public void index() {
		render("index.ftl");
	}

}