package com.topweshare.cache.jedis.shard;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.topweshare.cache.CacheDelegation;
import com.topweshare.cache.serial.ISerialization;
import com.topweshare.cache.util.KeyGenerator;
import com.topweshare.cache.util.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Jedis Delegation
 * 
 * @author <a href="mailto:wangmongoding@jiuxian.com">mongoding Wang</a>
 *
 */
public class JedisDelegation implements CacheDelegation {

	private ShardedJedisPool pool;

	private ISerialization serialization;

	private static final Logger LOGGER = LoggerFactory.getLogger(JedisDelegation.class);

	public JedisDelegation(ShardedJedisPool pool, ISerialization serialization) {
		super();
		this.pool = Preconditions.checkNotNull(pool);
		this.serialization = Preconditions.checkNotNull(serialization);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> Optional<V> query(String prefix, Keys k) {
		Optional<V> result = null;
		byte[] bs = null;
		try (ShardedJedis jedis = pool.getResource()) {
			bs = jedis.get(KeyGenerator.generateKey(k.getJoinedKey(), prefix));
		} catch (Exception e) {
			LOGGER.error("Load cache error.", e);
		}
		if (bs != null) {
			try {
				result = (Optional<V>) serialization.deserialize(bs);
			} catch (IOException e) {
				throw Throwables.propagate(e);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> Map<Keys, Optional<V>> query(String prefix, List<Keys> ks) {
		Map<Keys, Optional<V>> results = Maps.newHashMap();
		if (ks != null && !ks.isEmpty()) {
			Map<Keys, byte[]> resultsData = Maps.newHashMap();
			try (ShardedJedis jedis = pool.getResource()) {
				for (Keys key : ks) {
					byte[] bs = jedis.get(KeyGenerator.generateKey(key.getJoinedKey(), prefix));
					if (bs != null) {
						resultsData.put(key, bs);
					}
				}
			} catch (Exception e) {
				LOGGER.error("MLoad cache error.", e);
			}

			try {
				for (Entry<Keys, byte[]> resultsDataEntry : resultsData.entrySet()) {
					Optional<V> result;
					result = (Optional<V>) serialization.deserialize(resultsDataEntry.getValue());
					if (result != null) {
						results.put(resultsDataEntry.getKey(), result);
					}
				}
			} catch (IOException e) {
				throw Throwables.propagate(e);
			}
		}
		return results;
	}

	@Override
	public <V> void save(String prefix, Keys k, Optional<V> v) {
		save(prefix, k, v, 0);
	}

	@Override
	public <V> void save(String prefix, Map<Keys, Optional<V>> kvs) {
		save(prefix, kvs, 0);
	}

	@Override
	public <V> void save(String prefix, Keys k, Optional<V> v, int expires) {
		try {
			byte[] data = serialization.serialize(v);
			if (data != null) {
				try (ShardedJedis jedis = pool.getResource()) {
					if (expires > 0) {
						jedis.setex(KeyGenerator.generateKey(k.getJoinedKey(), prefix), expires, data);
					} else {
						jedis.set(KeyGenerator.generateKey(k.getJoinedKey(), prefix), data);
					}
				} catch (Exception e) {
					LOGGER.error("Save cache error.", e);
				}
			}
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}

	@Override
	public <V> void save(String prefix, Map<Keys, Optional<V>> kvs, int expires) {
		if (kvs != null) {
			for (Entry<Keys, Optional<V>> entry : kvs.entrySet()) {
				save(prefix, entry.getKey(), entry.getValue(), expires);
			}
		}
	}

}
