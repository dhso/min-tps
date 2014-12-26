/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.wish.frame.sdk.ace;

import java.io.Serializable;
import java.util.Map;

import com.alibaba.appengine.api.cache.CacheService;
import com.alibaba.appengine.api.cache.CacheServiceFactory;
import com.alibaba.appengine.api.fetchurl.FetchUrlService;
import com.alibaba.appengine.api.fetchurl.FetchUrlServiceFactory;

public class AceKit {

	/**
	 * HTTPGet方法
	 * 
	 * @param url
	 * @return
	 */
	public static String httpGet(String url) {
		FetchUrlService fetchUrlService = FetchUrlServiceFactory.getFetchUrlService();
		return fetchUrlService.get(url);
	}

	/**
	 * HTTPPost方法
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String httpPost(String url, Map parameters) {
		FetchUrlService fetchUrlService = FetchUrlServiceFactory.getFetchUrlService();
		return fetchUrlService.post(url, parameters, "utf-8");
	}

	/**
	 * CachePut方法
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean cachePut(String key, Serializable value, Integer expire) {
		CacheService cacheService = CacheServiceFactory.getCacheService();
		return cacheService.put(key, value, expire);
	}

	/**
	 * CacheGut方法
	 * 
	 * @param key
	 * @return
	 */
	public static Serializable cacheGet(String key) {
		CacheService cacheService = CacheServiceFactory.getCacheService();
		return cacheService.get(key);
	}
}