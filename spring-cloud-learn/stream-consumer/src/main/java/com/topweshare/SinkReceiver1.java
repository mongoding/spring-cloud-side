package com.topweshare;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * 快速入门
 *
 * @author mongoding
 * @create 2016/11/8.
 * @blog mongoding.github.io
 */
//@EnableBinding(value = {Sink.class})
public class SinkReceiver1 {

    private static Logger logger = LoggerFactory.getLogger(StreamConsumerApplication.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {
        logger.info("Received: " + payload);
    }

}
