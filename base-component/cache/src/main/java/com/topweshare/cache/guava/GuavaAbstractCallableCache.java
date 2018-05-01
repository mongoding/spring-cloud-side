package com.topweshare.cache.guava;


import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.topweshare.cache.util.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 抽象缓存类、缓存模板。
 * 子类需要实现fetchOrCalculateData从数据库或其他数据源中获取数据。
 *
 * @author mongoding
 */
public abstract class GuavaAbstractCallableCache extends GuavaCacheHolder implements IFetchData {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GuavaAbstractCallableCache(String prefix) {
        super(prefix);

        cache = CacheBuilder.newBuilder().maximumSize(maximumSize)        //缓存数据的最大条目
                .expireAfterWrite(expire, timeUnit)  //记录被创建或值被更新多少时间后被移除
                .recordStats()
                .build();

    }

    public GuavaAbstractCallableCache(int maximumSize, int expire, TimeUnit timeUnit, String prefix) {
        super(maximumSize, expire, timeUnit, prefix);
        cache = CacheBuilder.newBuilder().maximumSize(maximumSize)        //缓存数据的最大条目
                .expireAfterWrite(expire, timeUnit)    //记录被创建或值被更新多少时间后被移除
                .recordStats()
                .build();
    }


    public <V> V getCacheData(final Keys key) {
        try {
            Optional<Object> result = cache.get(this.generateKey(key), new Callable<Optional<Object>>() {

                public Optional<Object> call() throws Exception {
                    //执行缓存数据方法
                    logger.debug("回源取数据Keys:{}", key);
                    return Optional.fromNullable(fetchOrCalculateData(key));
                }
            });
            return result == null ? null : (V) result.orNull();
        } catch (ExecutionException e) {
            logger.error("guava 获取缓存异常：{}", e);
            //e.printStackTrace();
            return null;
        }
    }


}
