package com.topweshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * https://spring.io/guides/gs/async-method/
 */
@SpringBootApplication
@EnableAsync
public class SpringbootAsyncMethodsApplication  {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAsyncMethodsApplication.class, args);
	}


}
