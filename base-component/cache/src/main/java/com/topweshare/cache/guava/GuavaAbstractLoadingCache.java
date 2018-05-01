package com.topweshare.cache.guava;


import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.topweshare.cache.util.Keys;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 抽象Guava缓存类、缓存模板。
 * fetchOrCalculateData(key)，从数据库或其他数据源（如Redis）中获取数据。
 * 子类调用getValue(key)方法，从缓存中获取数据，并处理不同的异常，比如value为null时的InvalidCacheLoadException异常。
 *
 * @author mongoding
 * @Date 2017-08-23
 */
public abstract class GuavaAbstractLoadingCache extends GuavaCacheHolder implements IFetchData {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GuavaAbstractLoadingCache(String prefix) {
        super(prefix);
        this.initLoadingCache();
    }

    public GuavaAbstractLoadingCache(int maximumSize, int expire, TimeUnit timeUnit, String prefix) {
        super(maximumSize, expire, timeUnit, prefix);
        this.initLoadingCache();
    }


    /**
     * 通过调用getCache().get(key)来获取数据
     *
     * @return cache
     */
    private void initLoadingCache() {
        if (cache == null) {    //使用双重校验锁保证只有一个cache实例
            synchronized (this) {
                if (cache == null) {
                    cache = CacheBuilder.newBuilder().maximumSize(maximumSize)        //缓存数据的最大条目，也可以使用.maximumWeight(weight)代替
                            .expireAfterWrite(expire, timeUnit)    //数据被创建多久后被移除
                            .recordStats()                                            //启用统计
                            .build(new CacheLoader<String, Optional<Object>>() {
                                @Override
                                public Optional<Object> load(String key) throws Exception {
                                    logger.debug("回源取数据Keys:{}", key);
                                    Object value = fetchOrCalculateData(getKeysFromStringKey(key));
                                    return Optional.fromNullable(value);
                                }
                            });

                    logger.debug("本地缓存{}初始化成功", this.getClass().getSimpleName());
                }
            }
        }

    }

    private Keys getKeysFromStringKey(String key) {
        if (this.getPrefix() != null) {
            String result = key.replace(this.getPrefix() + "-", "");
            if (NumberUtils.isNumber(result)) {
                return Keys.of(Integer.parseInt(result));
            } else {
                return Keys.of(result);
            }

        }

        if (NumberUtils.isNumber(key)) {
            return Keys.of(Integer.parseInt(key));
        } else {
            return Keys.of(key);
        }
    }


    /**
     * 从缓存中获取数据（第一次自动调用fetchData从外部获取数据），并处理异常
     *
     * @param key
     * @return Value
     * @throws ExecutionException
     */
    public <V> V getCacheData(final Keys key) {
        try {
            String relKey = this.generateKey(key);
            Optional<Object> result = ((LoadingCache<String, Optional<Object>>) getCache()).get(relKey);
            //Optional<V> a=(Optional<V>)result;

            logger.debug("guava 获取缓存,key:{},value:{}", relKey, result);
            return result == null ? null : (V) result.orNull();
        } catch (ExecutionException e) {
            logger.error("guava 获取缓存异常：{}", e);
            //e.printStackTrace();
            return null;
        }

    }


}
