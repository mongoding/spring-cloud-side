package com.topweshare.api.impl;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaFeignClientProviderApplication {


	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaFeignClientProviderApplication.class).web(true).run(args);
	}

}
