package com.minws.tps.ctrl;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

@ControllerBind(controllerKey = "/", viewPath = "/tps")
public class MainController extends Controller {

	public void index() {
		render("index.htm");
	}
	
	public void welcome() {
		render("pages/welcome.htm");
	}

	public void packages() {
		render("pages/packages.htm");
	}
}