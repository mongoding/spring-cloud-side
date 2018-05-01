package com.topweshare.cache.jedis.shard;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * ShardedJedisPool factory bean
 * 
 * @author <a href="mailto:wangmongoding@jiuxian.com">mongoding Wang</a>
 *
 */
public class ShardedJedisPoolFactoryBean implements FactoryBean<ShardedJedisPool>, InitializingBean, Closeable {

	/**
	 * redis连接集群，格式：host1:port1,host2:port2
	 */
	private String address;

	private int timeout;

	private int poolMinSize;
	private int poolMaxSize;
	private long maxWaitMillis;

	private ShardedJedisPool object;

	private static final Logger LOGGER = LoggerFactory.getLogger(ShardedJedisPoolFactoryBean.class);

	public ShardedJedisPoolFactoryBean(String address, int timeout, int poolMinSize, int poolMaxSize) {
		super();
		this.address = Preconditions.checkNotNull(address);
		this.timeout = timeout;
		this.poolMinSize = poolMinSize;
		this.poolMaxSize = poolMaxSize;
	}

	public void setMaxWaitMillis(long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LOGGER.info("Initial pool for jedis: [Address: {}, timeout: {}, poolMinSize: {}, poolMaxSize: {}, maxWaitMillis: {}]", address, timeout,
				poolMinSize, poolMaxSize, maxWaitMillis);
		final GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMinIdle(poolMinSize);
		poolConfig.setMaxTotal(poolMaxSize);
		poolConfig.setMaxWaitMillis(maxWaitMillis);

		List<JedisShardInfo> shards = Lists.newArrayList();
		String[] hostPorts = StringUtils.split(address, ',');
		for (String hostPort : hostPorts) {
			String[] hostAndPort = StringUtils.split(hostPort, ':');
			Preconditions.checkArgument(hostAndPort.length == 2, "Error address format: {}", address);
			shards.add(new JedisShardInfo(hostAndPort[0], Integer.parseInt(hostAndPort[1]), timeout));
		}

		object = new ShardedJedisPool(poolConfig, shards);
	}

	@Override
	public ShardedJedisPool getObject() throws Exception {
		return object;
	}

	@Override
	public Class<?> getObjectType() {
		return ShardedJedisPool.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void close() throws IOException {
		if (object != null) {
			object.destroy();
		}
	}

}
