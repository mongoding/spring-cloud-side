package com.topweshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootHttpApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootHttpApplication.class);



	public static void main(String[] args) throws Exception{
		ApplicationContext ctx =  SpringApplication.run(SpringbootHttpApplication.class, args);


	}



}
