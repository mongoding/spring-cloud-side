package com.topweshare.mq.message;

import com.topweshare.mq.type.MessageEventType;

/**
 * 只包含消息类型与对象id的基本消息实体
 * 
 * @author mongoding
 *
 */
public class BasicMessage {
	
	/**
	 *  消息类型
	 */
	private MessageEventType eventType;
	
	/**
	 *  对象id
	 */
	private int objectId;

	public BasicMessage() {
		super();
	}

	public BasicMessage(MessageEventType eventType, int objectId) {
		super();
		this.eventType = eventType;
		this.objectId = objectId;
	}

	public MessageEventType getEventType() {
		return eventType;
	}

	public void setEventType(MessageEventType eventType) {
		this.eventType = eventType;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	@Override
	public String toString() {
		return "BasicMessage [eventType=" + eventType + ", objectId=" + objectId + "]";
	}
}
