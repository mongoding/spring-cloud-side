package com.topweshare;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

/**
 * @author mongoding
 * @create 2016/11/8.
 * @blog mongoding.github.io
 */
//@EnableBinding(value = {Processor.class})
public class SinkSender3 {

    private static Logger logger = LoggerFactory.getLogger(StreamProducerApplication.class);

    @Bean
    @InboundChannelAdapter(value = Processor.OUTPUT, poller = @Poller(fixedDelay = "2000"))
    public MessageSource<String> timerMessageSource() {
//        Map<String, Object> headers = new HashMap<>();
//        headers.put("content-type", "application/user");
        return () -> new GenericMessage<>("{\"name\":\"didi\", \"age\":30}");
    }

}
