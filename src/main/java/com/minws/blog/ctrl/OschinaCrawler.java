package com.minws.blog.ctrl;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Page;

public class OschinaCrawler extends BreadthCrawler {
	@Override
	public void visit(Page page) {
		System.out.println("URL:" + page.getUrl());
		System.out.println("Content-Type:" + page.getResponse().getContentType());
		System.out.println("Code:" + page.getResponse().getContentType());
		System.out.println("-----------------------------");
	}

	public static void main(String[] args) throws Exception {
		OschinaCrawler oschinaCrawler = new OschinaCrawler();
		oschinaCrawler.addSeed("http://www.oschina.net/");
		oschinaCrawler.addRegex("http://www\\.oschina\\.net/code/list.*");
		oschinaCrawler.setThreads(30);
		oschinaCrawler.setResumable(true);
		oschinaCrawler.setUseragent("Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:26.0) Gecko/20100101 Firefox/26.0");
		oschinaCrawler.start(5);
	}

}
