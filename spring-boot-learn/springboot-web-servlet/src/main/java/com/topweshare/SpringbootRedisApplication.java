package com.topweshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class SpringbootRedisApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootRedisApplication.class);


	public static void main(String[] args) throws Exception{
		ApplicationContext ctx =  SpringApplication.run(SpringbootRedisApplication.class, args);


	}


}
