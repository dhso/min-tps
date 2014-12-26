package com.minws.wish.frame.interceptor.syslog;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 系统日志
 */
public class LogConfig {
    /** 日志描述*/
    String title;

    /**
     * key 请求参数 value 参数描述
     */
    Map<String, String> params = Maps.newHashMap();
    
    public LogConfig(String title) {
        this.title = title;
    }

    public LogConfig addPara(String key, String value) {
        params.put(key, value);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public LogConfig setTitle(String title) {
        this.title = title;
        return this;
    }
}
