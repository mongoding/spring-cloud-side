package com.topweshare;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigServerEurekaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigServerEurekaApplication.class).web(true).run(args);
	}

}
