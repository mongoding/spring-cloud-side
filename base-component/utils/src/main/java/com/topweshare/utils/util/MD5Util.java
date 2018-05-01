package com.topweshare.utils.util;

import java.io.UnsupportedEncodingException;

import com.google.common.hash.Hashing;

public final class MD5Util {

	/**
	 * 生成字符串的md5校验值
	 * 
	 * @param s 待散列的原字段串
	 * @return　md5散列值
	 */
	public static String getMD5String(String s) {
		return getMD5String(s.getBytes());
	}

	/**
	 * 判断字符串的md5校验码是否与一个已知的md5码相匹配
	 * 
	 * @param password　要校验的字符串
	 * @param md5PwdStr　已知的md5校验码
	 * @return　是否匹配
	 */
	public static boolean checkPassword(String password, String md5PwdStr) {
		String s = getMD5String(password);
		return s.equals(md5PwdStr);
	}

	public static String getMD5String(byte[] bytes) {
		return Hashing.md5().hashBytes(bytes).toString();
	}

	public final static String getMd5AndCharset(String s, String charset) {
		try {
			return getMD5String(s.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}
