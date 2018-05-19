package com.topweshare;

import org.springframework.stereotype.Component;

/**
 * @author mongoding
 * @create 2017/6/24.
 * @blog https://mongoding.github.io
 */
@Component
public class DcClientFallback implements DcClient {

    @Override
    public String consumer() {
        return "fallback-test";
    }

    @Override
    public String hello(String name) {
        return "fallback-test";
    }
}
