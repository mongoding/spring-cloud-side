package com.topweshare.cache.guava;


import com.topweshare.cache.util.Keys;

/**
 * gauva cache
 *
 * @author <a href="mailto:dingzhenhao@jiuxian.com">dingzhenhao</a>
 */
public interface IFetchData {
    /**
     * 从缓存获取数据
     *
     * @param key
     * @return
     */
    <V> V getCacheData(final Keys key);


    /**
     * 从数据库或其他数据源中获取数据、或计算数据
     *
     * @param key
     * @return
     */
    <V> V fetchOrCalculateData(Keys key);



}
