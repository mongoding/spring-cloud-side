package com.topweshare.utils.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 过滤字符串中的特殊字符
 * @author mongoding
 * @since 2016-4-27 16:05:00
 */
public class Filterkeys {
	/**
	 * 过滤字符串中的特殊字符
     * @author: mongoding
     * @since 2016-4-27 16:05:00
	 * @param str 需要过滤的字符串
	 * @return　过滤后的字段串
	 */
	public static String StringFilterkeys(String str) {
		String mString = "";
		if (null != str) {
			//去空格
			str = str.replace(" ", "");
			// 清除掉所有特殊字符
			String regEx="[`~!@#$%^&={}':;',\"\\[\\]<>/?~！@#￥%……&——{}【】‘；：”“’。，、？]";  
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			mString = m.replaceAll("").trim();
			mString = mString.substring(0,mString.length() > 50 ? 50 : mString.length());
		}
		return mString;
	}
}
