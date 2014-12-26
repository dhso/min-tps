package com.minws.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.render.ViewType;
import com.minws.blog.BlogController;
import com.minws.frame.kit.HttpUtils;
import com.minws.frame.kit.properties.ProsMap;

/**
 * API引导式配置
 */
public class AppConfig extends JFinalConfig {

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		me.setDevMode(ProsMap.getBooPro("blog.devMode"));
		me.setViewType(ViewType.FREE_MARKER); // 设置视图类型，默认为FreeMarker
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", BlogController.class, "/blog");
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {

	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {

	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
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
		HttpUtils.setProxy(ProsMap.getStrPro("blog.ace.proxy.http.host"), ProsMap.getStrPro("blog.ace.proxy.http.port"), ProsMap.getStrPro("blog.ace.proxy.auth.username"), ProsMap.getStrPro("blog.ace.proxy.auth.password"));
	}

}
