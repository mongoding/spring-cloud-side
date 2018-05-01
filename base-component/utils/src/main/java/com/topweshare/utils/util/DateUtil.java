package com.topweshare.utils.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;

/**
 * 类 名: DateUtil 结合使用JDK , commons-lang joda-Time <br>
 * 
 * 描 述: 日期时间工具类
 * 
 * @version 1.0
 * @author Spring
 */
public class DateUtil {
	public static final String DATE_FORMAT = "yyyy-MM-dd";// 转换日期格式
	
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";// 转换日期时间格式
	
	public static final String TIME_FORMAT = "HH:ss:mm";// 转换时间格式

	public static Date formatStrToDate(String datestr, String formatStr) throws ParseException {
		return FastDateFormat.getInstance(formatStr, Locale.CHINESE).parse(datestr);
	}

	/**
	 * 将时间转换为指定格式的字符串
	 * 
	 * @param date　日期{@link Date}对象
	 * @param formatStr　转换格式
	 * @return　转换后的日期字符串
	 */
	public static String formatDateToStr(Date date, String formatStr) {
		return FastDateFormat.getInstance(formatStr, Locale.CHINESE).format(date);
	}

	/**
	 * 获取日期 之前或之后天数 的日期，同时时分秒清0
	 * 
	 * @since 2015-7-23 上午10:44:18
	 * @param d　日期{@link Date}对象
	 * @param day　天数
	 * @return　指定日期之前或之后天数的日期对象
	 */
	public static Date getDateFirst(Date d, int day) {
		DateTime dt = new DateTime(d).plusDays(day);
		return DateUtils.truncate(dt.toDate(), Calendar.DAY_OF_MONTH);
	}

	/**
	 * 日期Str的加减天 返回Str 
	 *
	 * @author spring
	 * @since 2015-7-23 上午10:57:36
	 * @param dateStr 原日期字段串
	 * @param day　增加天数
	 * @param formatStr　日期格式
	 * @return　增加或减少日期后的时间字符串
	 * @throws ParseException 解析异常
	 */
	public static String getStrByDay(String dateStr, int day, String formatStr) throws ParseException {
		return getStrByDay(formatStrToDate(dateStr, formatStr), day, formatStr);
	}

	/**
	 * 日期Date 的加减天 返回Str
	 * 
	 * @author spring
	 * @since 2015-7-23 上午10:57:36
	 * @param date　指定日期
	 * @param day 增加天数
	 * @param formatStr 日期格式
	 * @return 指定日期增加或减少天数后的日期字符串
	 */
	public static String getStrByDay(Date date, int day, String formatStr) {
		DateTime dt = new DateTime(date);
		return formatDateToStr(dt.plusDays(day).toDate(), formatStr);
	}

	/**
	 * 日期Str的加减月 返回Str
	 * 
	 * @author spring
	 * @since 2015-7-23 上午10:57:36
	 * @param dateStr　日期字符串
	 * @param day 加减的月
	 * @param formatStr 日期格式
	 * @return 日期Str的加减月 返回Str
	 * @throws ParseException 解析异常
	 */
	public static String getStrByMonth(String dateStr, int day, String formatStr) throws ParseException {
		return getStrByMonth(formatStrToDate(dateStr, formatStr), day, formatStr);
	}

	/**
	 * 日期Date 的加减月 返回Str
	 * 
	 * @author spring
	 * @since 2015-7-23 上午10:57:36
	 * @param date　指定日期
	 * @param day 加减的月
	 * @param formatStr 日期格式
	 * @return 日期Date 的加减月 返回Str
	 */
	public static String getStrByMonth(Date date, int day, String formatStr) {
		DateTime dt = new DateTime(date);
		return formatDateToStr(dt.plusMonths(day).toDate(), formatStr);
	}

	/**
	 * 比较两个时间大小
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return date1大于date2 返回1，date1等于date2 返回0, date1少于date2 返回-1
	 */
	public static int compare(Date date1, Date date2) {
		return date1.compareTo(date2);
	}
	
	/**
	 * 获取当前季度
	 * 
	 * @author lizhangzhi@jiuxain.com
	 * @return 当前季度
	 */
	public static Integer getCurrentQuarter() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		// 第一季度
		if (1 <= currentMonth && currentMonth <= 3) {
			return 1;
		}
		// 第二季度
		if (4 <= currentMonth && currentMonth <= 6) {
			return 2;

		}
		// 第三季度
		if (7 <= currentMonth && currentMonth <= 9) {
			return 3;
		}
		// 第四季度
		if (10 <= currentMonth && currentMonth <= 12) {
			return 4;
		}
		return null;

	}
	
}
