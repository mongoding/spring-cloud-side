package com.topweshare.command;

import java.util.concurrent.Future;

import com.topweshare.mq.service.NetPagerLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by mongoding on 2017/4/19.
 */
@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final NetPagerLookupService gitHubLookupService;

    public AppRunner(NetPagerLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Start the clock
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("任务一");

        // Kick of multiple, asynchronous lookups
        Future<String> page1 = gitHubLookupService.findByUrl("http://www.baidu.com");
        stopWatch.stop();
        stopWatch.start("任务二");
        Future<String> page2 = gitHubLookupService.findByUrl("http://www.jiuxian.com");
        stopWatch.stop();
        stopWatch.start("任务三");
        Future<String> page3 = gitHubLookupService.findByUrl("http://www.jd.com");
        stopWatch.stop();

        stopWatch.start("任务1，获取结果");

        String s1 = page1.get();
        stopWatch.stop();
        stopWatch.start("任务2，获取结果");
        String s2 = page1.get();
        stopWatch.stop();
        stopWatch.start("任务3，获取结果");
        String s3 = page1.get();
        stopWatch.stop();


        logger.info(stopWatch.prettyPrint());

    }

}