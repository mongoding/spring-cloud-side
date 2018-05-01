package com.topweshare.cache;

import com.topweshare.cache.util.Keys;

import java.util.List;
import java.util.Map;

/**
 * 数据源查询代理接口
 * 
 * @author <a href="mailto:wangmongoding@jiuxian.com">mongoding Wang</a>
 *
 * @param <V>
 */
public interface DataSourceDelegation<V> {

	V query(Keys k);

	Map<Keys, V> query(List<Keys> ks);

}
