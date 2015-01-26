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
	
	public void articleList() {
		String category = getPara("category", "");
		String pageNum = getPara("pageNum", "1");
		String everyNum = getPara("everyNum", "10");
		render("pages/articleList.htm");
	}
	
	public void pictureList() {
		String category = getPara("category", "");
		String pageNum = getPara("pageNum", "1");
		String everyNum = getPara("everyNum", "10");
		render("pages/pictureList.htm");
	}
}