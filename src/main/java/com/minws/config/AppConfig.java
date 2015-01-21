package com.minws.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.ext.plugin.sqlinxml.SqlInXmlPlugin;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.IErrorRenderFactory;
import com.jfinal.render.RedirectRender;
import com.jfinal.render.Render;
import com.jfinal.render.ViewType;

/**
 * API引导式配置
 */
public class AppConfig extends JFinalConfig {

	private Routes routes;

	/**
	 * 配置常量
	 */
	@Override
	public void configConstant(Constants me) {
		loadPropertyFile("config.txt");
		me.setEncoding(getProperty("tps.encode"));
		me.setDevMode(getPropertyToBoolean("tps.devMode"));
		me.setViewType(ViewType.JSP); // 设置视图类型，默认为FreeMarker
		me.setBaseViewPath("/pages/");
		// me.setErrorView(401, "/WEB-INF/pages/login.html");
		// me.setErrorView(403, "/WEB-INF/pages/login.html");
		me.setError401View("/err401");
		me.setError403View("/err403");
		me.setError404View("/err404");
		me.setError500View("/err500");
		me.setErrorRenderFactory(new IErrorRenderFactory() {
			@Override
			public Render getRender(int errorCode, String view) {
				return new RedirectRender(view);
			}
		});
	}

	/**
	 * 配置路由
	 */
	@Override
	public void configRoute(Routes me) {
		this.routes = me;
		me.add(new AutoBindRoutes());
	}

	/**
	 * 配置插件
	 */
	@Override
	public void configPlugin(Plugins me) {
		if (getPropertyToBoolean("tps.openShiro", true))
			me.add(new ShiroPlugin(routes));
		me.add(new EhCachePlugin());
		// 配置数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(getProperty("tps.jdbcUrl"), getProperty("tps.jdbcUser"), getProperty("tps.jdbcPassword"), getProperty("tps.jdbcDriver"));

		druidPlugin.addFilter(new StatFilter());
		me.add(druidPlugin);

		// 添加自动绑定model与表插件
		AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(druidPlugin, SimpleNameStyles.LOWER_UNDERLINE);
		autoTableBindPlugin.setShowSql(true);
		autoTableBindPlugin.setContainerFactory(new CaseInsensitiveContainerFactory());
		// autoTableBindPlugin.setAutoScan(false);
		me.add(autoTableBindPlugin);
		if (getPropertyToBoolean("tps.devMode", false))
			me.add(new SqlInXmlPlugin());
	}

	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		// shiro
		if (getPropertyToBoolean("tps.openShiro", true))
			me.add(new ShiroInterceptor());

		// 让 模版 可以使用session
		me.add(new SessionInViewInterceptor());
	}

	/**
	 * 配置处理器
	 */
	@Override
	public void configHandler(Handlers me) {
		// 计算每个page 运行时间
		// me.add(new RenderingTimeHandler());
		// 伪静态处理
		// me.add(new FakeStaticHandler());
		// 去掉 jsessionid 防止找不到action
		// me.add(new SessionHandler());
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
		super.afterJFinalStart();
		// HttpUtils.setProxy(ProsMap.getStrPro("tps.local.proxy.http.host"),
		// ProsMap.getStrPro("tps.local.proxy.http.port"),
		// ProsMap.getStrPro("tps.local.proxy.auth.username"),
		// ProsMap.getStrPro("tps.local.proxy.auth.password"));
	}

}
