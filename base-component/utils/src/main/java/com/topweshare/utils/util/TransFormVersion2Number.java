package com.topweshare.utils.util;

public class TransFormVersion2Number {
	/**
	 * 转换版本号为数值
	 * 
	 * @author: chengbinbin@jiuxain.com
	 * @since: 2015-10-21 上午11:26:56
	 * @param version 版本号
	 * @return 版本号数值
	 */
	public static long transformVersion2Number(String version) {
		String[] versionArr = version.split("\\.");
		long number = 0;
		for (int i = 0; i < 3; i++) {
			if (i == 0) {
				number += Long.parseLong(versionArr[i]) * 10000;
			} else if (i == 1) {
				number += Long.parseLong(versionArr[i]) * 100;
			} else {
				number += Long.parseLong(versionArr[i]);
			}
		}
		return number;
	}
}
