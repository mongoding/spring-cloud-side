package com.topweshare.mq.converter;

import com.google.gson.Gson;

/**
 * 消息转换实现
 * 
 * @author mongoding
 *
 */
public class GsonGeneralMessageConverter implements com.topweshare.mq.converter.IMessageConverter {

	private Gson gson;
	
	public GsonGeneralMessageConverter(Gson gson) {
		this.gson = gson;
	}
	
	@Override
	public String serialize(Object message) {
		return gson.toJson(message);
	}

	@Override
	public <T> T deserialize(String msg, Class<T> clazz) {
		return gson.fromJson(msg, clazz);
	}
	
}
