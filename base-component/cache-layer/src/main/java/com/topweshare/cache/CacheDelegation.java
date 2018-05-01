package com.topweshare.cache;

import com.google.common.base.Optional;
import com.topweshare.cache.util.Keys;

import java.util.List;
import java.util.Map;


/**
 * 缓存代理接口
 * 
 * @author <a href="mailto:wangmongoding@jiuxian.com">mongoding Wang</a>
 *
 * @param <V>
 */
public interface CacheDelegation {

	/**
	 * 查询
	 * 
	 * @param prefix
	 * @param k
	 * @return
	 */
	<V> Optional<V> query(String prefix, Keys k);

	/**
	 * 批量查询
	 * 
	 * @param prefix
	 * @param ks
	 * @return
	 */
	<V> Map<Keys, Optional<V>> query(String prefix, List<Keys> ks);

	/**
	 * 保存
	 * 
	 * @param prefix
	 * @param key
	 * @param v
	 */
	<V> void save(String prefix, Keys key, Optional<V> v);

	/**
	 * 批量保存
	 * 
	 * @param prefix
	 * @param kvs
	 */
	<V> void save(String prefix, Map<Keys, Optional<V>> kvs);

	/**
	 * 保存
	 * 
	 * @param prefix
	 * @param k
	 * @param v
	 * @param expires
	 *            过期时间，In seconds
	 */
	<V> void save(String prefix, Keys k, Optional<V> v, int expires);

	/**
	 * 批量保存
	 * 
	 * @param prefix
	 * @param kvs
	 * @param expires
	 *            过期时间，In seconds
	 */
	<V> void save(String prefix, Map<Keys, Optional<V>> kvs, int expires);
}
