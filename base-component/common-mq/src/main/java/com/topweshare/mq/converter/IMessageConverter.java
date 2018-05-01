package com.topweshare.mq.converter;

/**
 * 消息转换服务
 * 
 * @author mongoding
 */
public interface IMessageConverter {

	String serialize(Object message);

	<T> T deserialize(String msg, Class<T> clazz);

}
