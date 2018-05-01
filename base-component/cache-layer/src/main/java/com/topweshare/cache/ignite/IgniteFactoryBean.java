package com.topweshare.cache.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.logger.slf4j.Slf4jLogger;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.zk.TcpDiscoveryZookeeperIpFinder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.Closeable;
import java.io.IOException;

public class IgniteFactoryBean implements FactoryBean<Ignite>, InitializingBean, Closeable {

	private Ignite object;

	private String name;
	/**
	 * 节点发现的zk地址
	 */
	private String discoveryZk;

	public IgniteFactoryBean(String name, String discoveryZk) {
		super();
		this.name = name;
		this.discoveryZk = discoveryZk;
	}

	@Override
	public void close() throws IOException {
		if (object != null)
			object.close();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		IgniteConfiguration cfg = new IgniteConfiguration();
		cfg.setIgniteInstanceName(name);

		cfg.setGridLogger(new Slf4jLogger());

		TcpDiscoverySpi discoSpi = new TcpDiscoverySpi();
		TcpDiscoveryZookeeperIpFinder ipFinder = new TcpDiscoveryZookeeperIpFinder();
		ipFinder.setServiceName(name);
		ipFinder.setZkConnectionString(discoveryZk);
		discoSpi.setIpFinder(ipFinder);
		cfg.setDiscoverySpi(discoSpi);
		object = Ignition.start(cfg);
	}

	@Override
	public Ignite getObject() throws Exception {
		return object;
	}

	@Override
	public Class<?> getObjectType() {
		return Ignite.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
