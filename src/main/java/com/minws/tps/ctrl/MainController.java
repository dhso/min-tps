package com.minws.tps.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;
import com.minws.tps.model.Article;

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

	@Before(CacheInterceptor.class)
	@CacheName("articleList")
	public void articleList() {
		Integer categoryId = getParaToInt("categoryId", 1);
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		setAttr("articlePage", Article.dao.findAllArticles(categoryId, pageNumber, pageSize));
		setAttr("categoryId", categoryId);
		render("pages/articleList.htm");
	}

	public void pictureList() {
		String category = getPara("category", "");
		String pageNum = getPara("pageNum", "1");
		String everyNum = getPara("everyNum", "10");
		render("pages/pictureList.htm");
	}
}