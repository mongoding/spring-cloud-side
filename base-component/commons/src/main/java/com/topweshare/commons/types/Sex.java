package com.topweshare.commons.types;

/**
 * 性别
 * 
 * @author mongoding
 *
 */
public enum Sex {
	
	/**
	 * 保密
	 */
	SECRECY(0, "保密"),
	
	/**
	 * 男
	 */
	MAN(1, "男"),
	
	/**
	 * 女
	 */
	WOMAN(2, "女");
	
	private Sex(int code, String value) {
		this.code = code;
		this.value = value;
	}

	private int code;
	private String value;

	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	public static Sex fromCode(int code) {
		for (Sex sex : Sex.values()) {
			if (sex.getCode() == code) {
				return sex;
			}
		}
		return null;
	}
}
