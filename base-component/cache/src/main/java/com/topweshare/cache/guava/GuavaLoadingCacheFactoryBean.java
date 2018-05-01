package com.topweshare.cache.guava;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
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
public class GuavaLoadingCacheFactoryBean extends GuavaCacheHolder implements FactoryBean<Cache<String, Optional<Object>>>, InitializingBean, Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaLoadingCacheFactoryBean.class);


    public GuavaLoadingCacheFactoryBean(String cacheInstanceName) {
        super(cacheInstanceName);

    }
    public GuavaLoadingCacheFactoryBean(int expire, String cacheInstanceName) {

        super( expire,cacheInstanceName);

    }

    public GuavaLoadingCacheFactoryBean(int maximumSize, int expire, TimeUnit timeUnit, String cacheInstanceName) {
        super(maximumSize, expire, timeUnit, cacheInstanceName);

    }


    @Override
    public void afterPropertiesSet() throws Exception {


        cache = CacheBuilder.newBuilder().maximumSize(maximumSize)        //缓存数据的最大条目，也可以使用.maximumWeight(weight)代替
                .expireAfterWrite(expire, timeUnit)    //数据被创建多久后被移除
                .recordStats()                                            //启用统计
                .build(new CacheLoader<String, Optional<Object>>() {
                    @Override
                    public Optional<Object> load(String key) throws Exception {
                        return Optional.fromNullable(null);
                    }
                });

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

