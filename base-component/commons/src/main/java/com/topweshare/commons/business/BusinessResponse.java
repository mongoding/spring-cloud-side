package com.topweshare.commons.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务层返回数据模型
 * 
 * @author mongoding
 *
 * @param <B>
 *            业务返回数据
 * @param <E>
 *            业务异常
 */
public class BusinessResponse<B, E extends Enum<E>, T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 调用是否包含业务异常
	 */
	private boolean hasError;

	/**
	 * 业务返回数据
	 */
	private B body;

	/**
	 * 业务异常
	 */
	private List<com.topweshare.commons.business.ErrorItem<E, T>> errors;

	/**
	 * 
	 */
	public BusinessResponse() {
		super();
	}

	public BusinessResponse(B body, List<com.topweshare.commons.business.ErrorItem<E, T>> errors) {
		this();
		this.body = body;
		this.errors = errors;
		hasError = errors != null && errors.size() > 0;
	}

	public B getBody() {
		return body;
	}

	public void setBody(B body) {
		this.body = body;
	}

	public List<com.topweshare.commons.business.ErrorItem<E, T>> getErrors() {
		return errors;
	}

	public void appendError(com.topweshare.commons.business.ErrorItem<E, T> error) {
		if (errors == null) {
			errors = new ArrayList<>();
		}
		errors.add(error);
		hasError = true;
	}

	public boolean isHasError() {
		return hasError;
	}

}
