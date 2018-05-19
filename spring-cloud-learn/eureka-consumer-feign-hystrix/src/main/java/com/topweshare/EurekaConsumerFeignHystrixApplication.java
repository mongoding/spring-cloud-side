package com.topweshare;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
public class EurekaConsumerFeignHystrixApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaConsumerFeignHystrixApplication.class).web(true).run(args);
	}

}
