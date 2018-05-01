package com.topweshare.commons.business;

import java.io.Serializable;

/**
 * 错误项
 * 
 * @author mongoding
 *
 * @param <E>
 *            错误类型
 * @param <T>
 *            附件类型
 */
public class ErrorItem<E extends Enum<E>, T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误类型
	 */
	private E errorType;
	/**
	 * 错误消息
	 */
	private String message;

	/**
	 * 错误附带信息
	 */
	private T attachment;

	public ErrorItem(E errorType, String message) {
		super();
		this.errorType = errorType;
		this.message = message;
	}

	public ErrorItem(E errorType, String message, T attachment) {
		super();
		this.errorType = errorType;
		this.message = message;
		this.attachment = attachment;
	}

	public T getAttachment() {
		return attachment;
	}

	public void setAttachment(T attachment) {
		this.attachment = attachment;
	}

	public E getErrorType() {
		return errorType;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ErrorItem [errorType=" + errorType + ", message=" + message + ", body=" + attachment + "]";
	}

}
