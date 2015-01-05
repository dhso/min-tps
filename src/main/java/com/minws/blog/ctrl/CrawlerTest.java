package com.minws.blog.ctrl;

import java.util.regex.Pattern;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Page;

public class CrawlerTest {

	public static void main(String[] args) throws Exception {
		BreadthCrawler crawler = new BreadthCrawler() {
			@Override
			public void visit(Page page) {
				String question_regex = ".*/project/tag/.*";
				if (Pattern.matches(question_regex, page.getUrl())) {
					System.out.println("类型:" + page.getResponse().getContentType());
					System.out.println("响应:" + page.getResponse().getCode());
					System.out.println("网址:" + page.getUrl());
					System.out.println("标题:" + page.getDoc().title());
					System.out.println("-----------------------------");
				}
			}
		};

		crawler.addSeed("http://www.oschina.net/project");
		crawler.addRegex(".*/project/.*");
		crawler.setThreads(30);
		crawler.wait(2000);
		crawler.setResumable(true);
		crawler.setUseragent("Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:26.0) Gecko/20100101 Firefox/26.0");
		crawler.start(5);

	}

}
