package com.topweshare.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mongoding
 *
 */
public abstract class BaseConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseConsumer.class);

	public void handle(String message) {
		try {
			doHandle(message);
		} catch (Exception e) {
			LOGGER.error("Message: {} with Error: {}", message, e);
		}
	}

	protected abstract void doHandle(String message);

}
