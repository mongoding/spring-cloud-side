package com.topweshare.commons.types;

/**
 * Main Platform
 * 
 * @author mongoding
 *
 */
public enum MainPlatform {

	/**
	 * 官网站点
	 */
	pc(1),

	/**
	 * 移动站点
	 */
	mobile(2);

	private int code;

	public int getCode() {
		return code;
	}

	private MainPlatform(int code) {
		this.code = code;
	}

	public static MainPlatform fromCode(int code) {
		for (MainPlatform platform : MainPlatform.values()) {
			if (code == platform.getCode()) {
				return platform;
			}
		}
		return null;
	}

}
