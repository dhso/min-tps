package com.minws.config;

import java.util.Properties;

import cn.dreampie.shiro.core.ShiroInterceptor;
import cn.dreampie.shiro.core.ShiroPlugin;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.handler.FakeStaticHandler;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import com.minws.frame.config.ConfigKit;
import com.minws.frame.config.ConfigPlugin;
import com.minws.frame.handler.SessionHandler;
import com.minws.frame.plugin.shiro.MyJdbcAuthzService;
import com.minws.frame.plugin.xss.XssHandler;
import com.minws.frame.route.AutoBindRoutes;

/**
 * API引导式配置
 */
public class AppConfig extends JFinalConfig {

	private Routes routes;

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		me.setEncoding("utf-8");
		me.setDevMode(true);
		me.setViewType(ViewType.FREE_MARKER); // 设置视图类型，默认为FreeMarker
		me.setErrorView(401, "/au/login.html");
		me.setErrorView(403, "/au/login.html");
		me.setError404View("/404.html");
		me.setError500View("/500.html");
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		this.routes = new AutoBindRoutes();
		me.add(routes);
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// config
		me.add(new ConfigPlugin("config.properties"));
		// shiro
		if (ConfigKit.getBoolean("tps.openShiro", true))
			me.add(new ShiroPlugin(routes, new MyJdbcAuthzService()));
		// c3p0 数据源插件
		C3p0Plugin cp = new C3p0Plugin(ConfigKit.getStr("tps.jdbcUrl"), ConfigKit.getStr("tps.user"), ConfigKit.getStr("tps.password"));
		me.add(cp);
		// ActiveRecrod 支持插件
		me.add(new ActiveRecordPlugin(cp));
		// add EhCache
		me.add(new EhCachePlugin());
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		// shiro
		if (ConfigKit.getBoolean("tps.openShiro", true))
			me.add(new ShiroInterceptor());
		// 让 模版 可以使用session
		me.add(new SessionInViewInterceptor());
	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		// 计算每个page 运行时间
		// me.add(new RenderingTimeHandler());
		// xss 过滤
		me.add(new XssHandler("s"));
		// 伪静态处理
		me.add(new FakeStaticHandler());
		// 去掉 jsessionid 防止找不到action
		me.add(new SessionHandler());
		me.add(new UrlSkipHandler(".*/static/.*", false));
		me.add(new ContextPathHandler("baseUrl"));
	}

	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目 运行此 main
	 * 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/", 5);
	}

	@Override
	public void afterJFinalStart() {
		// HttpUtils.setProxy(ProsMap.getStrPro("tps.local.proxy.http.host"),
		// ProsMap.getStrPro("tps.local.proxy.http.port"),
		// ProsMap.getStrPro("tps.local.proxy.auth.username"),
		// ProsMap.getStrPro("tps.local.proxy.auth.password"));
	}

}
