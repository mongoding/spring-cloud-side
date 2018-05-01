package com.topweshare.cache.guava;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * gauva cache factory
 *
 * @author <a href="mailto:mongoding@jiuxian.com">mongoding</a>
 */
public class GuavaCallableCacheFactoryBean extends GuavaCacheHolder implements FactoryBean<Cache<String, Optional<Object>>>, InitializingBean, Closeable {


    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaCallableCacheFactoryBean.class);


    public GuavaCallableCacheFactoryBean(String cacheInstanceName) {
        super(cacheInstanceName);

        //this.cacheInstanceName = cacheInstanceName;

    }
    public GuavaCallableCacheFactoryBean(int expire, String cacheInstanceName) {

        super( expire,cacheInstanceName);

    }
    public GuavaCallableCacheFactoryBean(int maximumSize, int expire, TimeUnit timeUnit, String cacheInstanceName) {

        super(maximumSize, expire, timeUnit, cacheInstanceName);

    }


    @Override
    public void afterPropertiesSet() throws Exception {

        cache = CacheBuilder.newBuilder().maximumSize(maximumSize)        //缓存数据的最大条目
                .expireAfterWrite(expire, timeUnit)    //记录被创建或值被更新多少时间后被移除
                .recordStats()
                .build();

    }

    @Override
    public Cache<String, Optional<Object>> getObject() throws Exception {
        return cache;
    }

    @Override
    public Class<?> getObjectType() {
        return Cache.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void close() throws IOException {
        //do nothing
    }
}
