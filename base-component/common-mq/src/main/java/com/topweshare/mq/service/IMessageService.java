package com.topweshare.mq.service;

import com.topweshare.mq.message.BasicMessage;

/**
 * 消息服务接口
 * 
 * @author mongoding
 *
 */
public interface IMessageService {
	/**
	 * 发送消息
	 *
	 * @author mongoding
	 * @since 2016年1月5日
	 * @param msg
	 */
	void send(BasicMessage msg);
}
