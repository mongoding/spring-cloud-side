package com.topweshare.utils.util;

/**
 * 比较工具类
 * 
 * @author mongoding
 *
 */
public final class CompareUtils {

	/**
	 * 查找两个元素中大一些的元素
	 * 
	 * @param <T> 对象类型
	 * @param obj1 参于比较的对象
	 * @param obj2　参于比较的对象
	 * @return 两者中较大的元素，如若相等，返回第一个元素
	 */
	public static <T extends Comparable<T>> T biggerOne(T obj1, T obj2) {
		return obj1.compareTo(obj2) >= 0 ? obj1 : obj2;
	}

	/**
	 * 查找两个元素中小一些的元素
	 * 
	 * @param <T> 对象类型
	 * @param obj1　参于查找的元素
	 * @param obj2　参于查找的元素
	 * @return 两者中较小的元素，如若相等，返回第一个元素
	 */
	public static <T extends Comparable<T>> T smallerOne(T obj1, T obj2) {
		return obj1.compareTo(obj2) <= 0 ? obj1 : obj2;
	}

	/**
	 * 判断第一个元素是否比第二个元素大
	 * 
	 * @param <T> 对象类型
	 * @param obj1　对象1
	 * @param obj2  对象2
	 * @return obj1大于obj2，返回true，否则false
	 */
	public static <T extends Comparable<T>> boolean biggerThan(T obj1, T obj2) {
		return obj1.compareTo(obj2) > 0;
	}

	/**
	 * 判断第一个元素是否比第二个元素小
	 * 
	 * @param <T> 对象类型
	 * @param obj1　对象1
	 * @param obj2 　对象2
	 * @return obj1小于obj2，返回true，否则false
	 */
	public static <T extends Comparable<T>> boolean smallerThan(T obj1, T obj2) {
		return obj1.compareTo(obj2) < 0;
	}

	/**
	 * 判断两个元素的比较值是否相等
	 * 
	 * @param <T> 对象类型
	 * @param obj1　对象1
	 * @param obj2 对象2
	 * @return obj1==obj2，返回true，否则false
	 */
	public static <T extends Comparable<T>> boolean equals(T obj1, T obj2) {
		return obj1.compareTo(obj2) == 0;
	}

}
