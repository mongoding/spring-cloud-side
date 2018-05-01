package com.topweshare.mq.service;


import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
/**
 * Created by mongoding on 2017/4/19.
 */
@Service
public class NetPagerLookupService {

    private static final Logger logger = LoggerFactory.getLogger(NetPagerLookupService.class);

    private final RestTemplate restTemplate;

    public NetPagerLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

   @Async
    public Future<String> findByUrl(String url) throws InterruptedException {
        logger.info("Looking up " + url);
        String results = restTemplate.getForObject(url, String.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
       logger.info("当前线程{}",Thread.currentThread().getName());
        return new AsyncResult<>(results);
    }
    @Async
    public void findDefaulByUrl() throws InterruptedException{
        findByUrl("http://www.baidu.com");

    }
    @Async
    public void findByUrlUnreturn(String url)throws InterruptedException {
        findByUrl(url);
    }

}