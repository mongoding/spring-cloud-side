package com.topweshare.api.impl;

import com.topweshare.api.HelloService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController implements HelloService {

    public String hello(String name) {

        return "hello " + name;
    }
}
