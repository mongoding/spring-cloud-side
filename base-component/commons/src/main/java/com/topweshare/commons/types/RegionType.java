package com.topweshare.commons.types;

/**
 * 区域类型
 * 
 * @author mongoding
 *
 */
public enum RegionType {
	
	/**
	 * 国家
	 */
	NATION(0),
	
	/**
	 * 省（一级地址）
	 */
	PROVINCE(1),
	
	/**
	 * 市（二级地址）
	 */
	CITY(2),
	
	/**
	 * 县/区（三级地址）
	 */
	COUNTY(3);

	private RegionType(int code) {
		this.code = code;
	}

	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public static RegionType fromCode(int code) {
		for (RegionType type : RegionType.values()) {
			if (type.getCode() == code) {
				return type;
			}
		}
		return null;
	}
}
