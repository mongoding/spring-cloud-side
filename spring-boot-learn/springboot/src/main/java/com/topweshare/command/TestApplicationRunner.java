package com.topweshare.command;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class TestApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("TestApplicationRunner.run  running ");
    }
}
