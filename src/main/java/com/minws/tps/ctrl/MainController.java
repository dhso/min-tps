package com.minws.tps.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", "第一篇文章");
		map.put("text", "第一篇文章内容");
		list.add(map);
		setAttr("articleLists", list);
		render("pages/articleList.htm");
	}

	public void pictureList() {
		String category = getPara("category", "");
		String pageNum = getPara("pageNum", "1");
		String everyNum = getPara("everyNum", "10");
		render("pages/pictureList.htm");
	}
}