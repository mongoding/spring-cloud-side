package com.topweshare.cache.util;

import com.google.common.base.Preconditions;

/**
 * 缓存key生成服务
 * 
 * @author <a href="mailto:wangmongoding@jiuxian.com">mongoding Wang</a>
 *
 */
public final class KeyGenerator {

	private static final char KEY_PARTS_JOINER = '-';

	/**
	 * 生成缓存key
	 * 
	 * @param key
	 *            标识
	 * @param prefix
	 *            前缀
	 * @param postfix
	 *            后缀
	 * @return
	 */
	public static byte[] generateKey(Object key, Object prefix) {
		return generateStringKey(key, prefix).getBytes();
	}

	/**
	 * 生成缓存key
	 * 
	 * @param key
	 *            标识
	 * @param prefix
	 *            前缀
	 * @param postfix
	 *            后缀
	 * @return
	 */
	public static String generateStringKey(Object key, Object prefix) {
		Preconditions.checkNotNull(key);

		final StringBuilder builder = new StringBuilder();
		if (prefix != null) {
			builder.append(prefix);
			builder.append(KEY_PARTS_JOINER);
		}
		builder.append(key);
		return builder.toString();
	}

	/**
	 * 可变多参数 ,聚合为一个Key 注意不要勿用到需要细化参数粒度增加命中率的方法上
	 * 
	 * @param args
	 * @author spring
	 * @return
	 */
	public static String polymerizeKey(Object... args) {

		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			Object object = args[i];
			builder.append(object);
			if (i < args.length - 1) {
				builder.append(KEY_PARTS_JOINER);
			}
		}
		return builder.toString();
	}

}
