package com.topweshare.cache.guava;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.topweshare.cache.util.KeyGenerator;
import com.topweshare.cache.util.Keys;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * gauva cache holder
 *
 * @author <a href="mailto:dingzhenhao@jiuxian.com">dingzhenhao</a>
 */
public class GuavaCacheHolder {
    public Cache<String, Optional<Object>> cache;

    //public static Map<String, GuavaCacheDelegation<V>> allGuavaCache = new HashMap<>();
    public static Map<String, Cache<String, Optional<Object>>> guavaCacheContext = new HashMap<>();


    int maximumSize = 5000;                    //最大缓存条数
    int expire = 30;        //刷新数据时间
    TimeUnit timeUnit = TimeUnit.SECONDS;    //缺省时间单位（秒）


    //可做适当监控统计
    //private Date resetTime;        //Cache初始化或被重置的时间
    //private long highestSize = 0;    //历史最高记录数
    //private Date highestTime;    //创造历史记录的时间

    private String prefix;        //缓存前缀

    public GuavaCacheHolder(String prefix) {
        this.prefix = prefix;
        if (prefix != null) {
            guavaCacheContext.put(prefix, this.getCache());
        }

    }


    public GuavaCacheHolder(int expire,  String prefix) {
        this.expire = expire;
        this.prefix = prefix;

        if (prefix != null) {
            guavaCacheContext.put(prefix, this.getCache());
        }

    }
    public GuavaCacheHolder(int maximumSize, int expire, TimeUnit timeUnit, String prefix) {
        this.maximumSize = maximumSize;
        this.expire = expire;
        this.timeUnit = timeUnit;
        this.prefix = prefix;

        if (prefix != null) {
            guavaCacheContext.put(prefix, this.getCache());
        }

    }

    public Cache<String, Optional<Object>> getCache() {
        return cache;
    }

    public void setCache(Cache<String, Optional<Object>> cache) {
        this.cache = cache;
    }

    public static Map<String, Cache<String, Optional<Object>>> getGuavaCacheContext() {
        return guavaCacheContext;
    }

    public static void setGuavaCacheContext(Map<String, Cache<String, Optional<Object>>> guavaCacheContext) {
        GuavaCacheHolder.guavaCacheContext = guavaCacheContext;
    }

    public int getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(int maximumSize) {
        this.maximumSize = maximumSize;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }



    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String generateKey(Keys key) {
        return KeyGenerator.generateStringKey(key.getJoinedKey(), this.getPrefix());
    }


}
