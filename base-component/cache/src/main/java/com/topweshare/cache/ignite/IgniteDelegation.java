package com.topweshare.cache.ignite;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.topweshare.cache.CacheDelegation;
import com.topweshare.cache.util.Keys;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.configuration.CacheConfiguration;

import javax.cache.expiry.Duration;
import javax.cache.expiry.ModifiedExpiryPolicy;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class IgniteDelegation implements CacheDelegation {

	private Ignite ignite;

	public IgniteDelegation(Ignite ignite) {
		super();
		this.ignite = ignite;
	}

	@Override
	public <V> Optional<V> query(String prefix, Keys k) {
		IgniteCache<String, Optional<V>> cache = getIgniteCache(prefix);
		return cache.get(k.getJoinedKey());
	}

	@Override
	public <V> Map<Keys, Optional<V>> query(String prefix, List<Keys> ks) {
		IgniteCache<String, Optional<V>> cache = getIgniteCache(prefix);
		Map<String, Keys> keyMapping = Maps.newHashMap();
		for (Keys k : ks) {
			keyMapping.put(k.getJoinedKey(), k);
		}
		Map<String, Optional<V>> all = cache.getAll(keyMapping.keySet());
		Map<Keys, Optional<V>> result = Maps.newHashMap();
		for (Entry<String, Optional<V>> entry : all.entrySet()) {
			result.put(keyMapping.get(entry.getKey()), entry.getValue());
		}
		return result;
	}

	@Override
	public <V> void save(String prefix, Keys key, Optional<V> v) {
		IgniteCache<String, Optional<V>> cache = getIgniteCache(prefix);
		cache.put(key.getJoinedKey(), v);
	}

	@Override
	public <V> void save(String prefix, Map<Keys, Optional<V>> kvs) {
		IgniteCache<String, Optional<V>> cache = getIgniteCache(prefix);
		Map<String, Optional<V>> map = Maps.newHashMap();
		for (Entry<Keys, Optional<V>> entry : kvs.entrySet()) {
			map.put(entry.getKey().getJoinedKey(), entry.getValue());
		}
		cache.putAll(map);
	}

	@Override
	public <V> void save(String prefix, Keys k, Optional<V> v, int expires) {
		IgniteCache<String, Optional<V>> cache = getIgniteCache(prefix);
		cache.withExpiryPolicy(new ModifiedExpiryPolicy(new Duration(TimeUnit.SECONDS, expires))).put(k.getJoinedKey(), v);
	}

	@Override
	public <V> void save(String prefix, Map<Keys, Optional<V>> kvs, int expires) {
		IgniteCache<String, Optional<V>> cache = getIgniteCache(prefix);
		Map<String, Optional<V>> map = Maps.newHashMap();
		for (Entry<Keys, Optional<V>> entry : kvs.entrySet()) {
			map.put(entry.getKey().getJoinedKey(), entry.getValue());
		}
		cache.withExpiryPolicy(new ModifiedExpiryPolicy(new Duration(TimeUnit.SECONDS, expires))).putAll(map);
	}

	private <V> IgniteCache<String, Optional<V>> getIgniteCache(String name) {
		IgniteCache<String, Optional<V>> cache = ignite.cache(name);
		if (cache == null) {
			synchronized (this) {
				if (cache == null) {
					CacheConfiguration<String, Optional<V>> cacheConfiguration = new CacheConfiguration<>();
					cacheConfiguration.setName(name);
					cacheConfiguration.setBackups(1);
					cache = ignite.getOrCreateCache(cacheConfiguration);
				}
			}
		}

		return cache;
	}

}
