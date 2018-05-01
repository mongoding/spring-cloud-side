package com.topweshare.cache;



import com.google.common.base.Optional;
import com.topweshare.cache.util.Keys;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author <a href="mailto:mongoding@qq.com">zhenhao ding</a>
 *
 * @param <V>
 */
public interface Cache<V> {


	V query(Keys k, Function<Keys, V> function);

	Map<Keys, V> query(List<Keys> ks, Function<List<Keys>, Map<Keys, V>> function);

	/**
	 * 是否要穿透缓存，回源取数据
	 * @param data
	 * @return
	 */
	default public boolean ifGetFromSource(Optional<V> data){

		if (data == null) {
			return true;
		}

		return false;
	}
	
}
