package com.topweshare.cache;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import com.topweshare.cache.util.Keys;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author <a href="mailto:wangmongoding@jiuxian.com">mongoding Wang</a>
 *
 * @param <V>
 */
public class SimpleCache<V> implements Cache<V> {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SimpleCache.class);


	private CacheDelegation level1Cache;
	/**
	 * 超时时间，小于等于0代表不超时
	 */
	private Integer expireInSeconds;

	private String namespace;
	public SimpleCache(CacheDelegation level1Cache, String namespace, Integer expireInSeconds){
		super();
		this.level1Cache = level1Cache;
		this.namespace = namespace;
		this.expireInSeconds = expireInSeconds;

	}

	@Override
	public V query(Keys k, Function<Keys, V> function) {
		Preconditions.checkArgument(k != null, "Query key must not be null.");
		Preconditions.checkNotNull(function);

		Optional<V> cachedData = null;
		try {
			cachedData = level1Cache.query(namespace,k);
		} catch (Exception e) {
			LOGGER.error("从缓存查询数据，key：{},命名空间：{}",k,namespace);
			LOGGER.error("Query cache error.", e);
		}

		if (ifGetFromSource(cachedData)) {
			LOGGER.debug("从缓存未获取到数据，从Callable 函数那数据");
			cachedData = Optional.fromNullable(function.apply(k));
			try {
				if (expireInSeconds == null) {
					level1Cache.save(namespace, k, cachedData);
				} else {
					level1Cache.save(namespace, k, cachedData,expireInSeconds);
				}
			} catch (Exception e) {
				LOGGER.error("需要保存的数据：{},命名空间：{},缓存时间",cachedData,namespace,expireInSeconds);
				LOGGER.error("Save cache error.", e);
			}
		}else {
			LOGGER.info("命中缓存");
		}

		return cachedData.orNull();
	}

	@Override
	public Map<Keys, V> query(List<Keys> ks, Function<List<Keys>, Map<Keys, V>> function) {
		Preconditions.checkArgument(ks != null && !ks.isEmpty(), "Query keys must not be empty.");

		Map<Keys, V> result = Maps.newHashMap();
		List<Keys> keysMissed = Lists.newArrayList(Sets.newHashSet(ks));

		// Fetch data from cache
		Map<Keys, Optional<V>> cachedDatas = null;
		try {
			cachedDatas = level1Cache.query(namespace,keysMissed);

		} catch (Exception e) {
			LOGGER.error("从缓存查询数据，key：{},命名空间：{}",keysMissed,namespace);
			LOGGER.error("Query cache error.", e);
		}
		if (cachedDatas != null) {
			for (Map.Entry<Keys, Optional<V>> entry : cachedDatas.entrySet()) {
				Keys key = entry.getKey();
				Optional<V> cachedItem = entry.getValue();

				if (cachedItem.isPresent()) {
					keysMissed.remove(key);
					result.put(key, cachedItem.get());
				}
			}
			LOGGER.info("命中缓存个数：{}",result.size());
		}

		// Fetch the left from real data source
		if (!keysMissed.isEmpty()) {
			LOGGER.debug("从缓存未查询到有效数据，从函数拿数据,缺失key个数：{},",keysMissed.size());
			Map<Keys, V> dataFromSource = function.apply(keysMissed);
			Map<Keys, Optional<V>> dataToCache = Maps.newHashMap();
			for (Keys key : keysMissed) {
				V value = dataFromSource == null ? null : dataFromSource.get(key);
				if (value != null) {
					result.put(key, value);
				}
				dataToCache.put(key, Optional.fromNullable(value));
			}

			// Save missed data to cache
			try {
				if (expireInSeconds == null) {
					level1Cache.save(namespace,dataToCache);
				} else {
					level1Cache.save(namespace,dataToCache,expireInSeconds);
				}

			} catch (Exception e) {
				LOGGER.error("Save cache error.", e);
				LOGGER.error("需要保存的数据：{},命名空间：{},缓存时间",dataToCache,namespace,expireInSeconds);
			}
		}
		LOGGER.debug("从缓存获取到的结果：{}",result);
		return result;
	}



}
