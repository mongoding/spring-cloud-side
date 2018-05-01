package com.topweshare;

import org.springframework.stereotype.Component;

/**
 * @author mongoding
 * @create 2017/6/24.
 * @blog https://mongoding.githu.io
 */
@Component
public class DcClientFallback implements DcClient {

    @Override
    public String consumer() {
        return "fallback";
    }
}
