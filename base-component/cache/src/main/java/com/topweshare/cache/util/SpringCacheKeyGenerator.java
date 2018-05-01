/**
 * 
 */
package com.topweshare.cache.util;

import com.alibaba.fastjson.JSON;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

/**
 * @comment
 *
 * @author marke
 *
 * @time 2016年3月28日 下午7:00:56
 *
 */
public class SpringCacheKeyGenerator implements KeyGenerator {
	private String cachePrefix;

	public String getCachePrefix() {
		return cachePrefix;
	}

	public void setCachePrefix(String cachePrefix) {
		this.cachePrefix = cachePrefix;
	}

	SpringCacheKeyGenerator() {

	}

	SpringCacheKeyGenerator(String cachePrefix) {
		this.cachePrefix = cachePrefix;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object generate(Object target, Method method, Object... params) {
		// 如果没有设置缓存前缀，自动加入【springcache:】前缀
		if (StringUtils.isBlank(cachePrefix)) {
			cachePrefix = "springcache:";
		}
		StringBuffer sb = new StringBuffer(cachePrefix + target.getClass().getName());
		sb.append(method.getName());

		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				Object obj = params[i];
				if (obj instanceof Map) {
					params[i] = new TreeMap(((Map) obj));
				}
			}
			// 参数生成MD5作为key,避免key过于冗长
			sb.append(Hashing.md5().hashBytes(JSON.toJSONString(params).getBytes()).toString());
		}
		return sb.toString();
	}

}
