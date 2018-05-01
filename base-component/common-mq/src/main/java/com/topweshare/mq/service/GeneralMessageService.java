package com.topweshare.mq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.google.common.base.Preconditions;
import com.topweshare.mq.converter.IMessageConverter;
import com.topweshare.mq.message.BasicMessage;

/**
 * 通用消息服务
 * 
 * @author mongoding
 *
 */
public class GeneralMessageService implements com.topweshare.mq.service.IMessageService {

	private static final String MESSAGE_CANNOT_BE_NULL = "Message cannot be null";
	
	private RabbitTemplate rabbitTemplate;
	
	private IMessageConverter gsonGeneralMessageConverter;
	
	public GeneralMessageService(RabbitTemplate rabbitTemplate, IMessageConverter gsonGeneralMessageConverter) {
		super();
		this.rabbitTemplate = rabbitTemplate;
		this.gsonGeneralMessageConverter = gsonGeneralMessageConverter;
	}

	@Override
	public void send(BasicMessage msg) {		
		this.rabbitTemplate.convertAndSend(gsonGeneralMessageConverter.serialize(Preconditions.checkNotNull(msg, MESSAGE_CANNOT_BE_NULL)));
	}
}
