package com.topweshare.utils.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author mongoding
 *
 */
public final class PasswordUtils {

	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	/**
	 * Encode the plain password
	 * 
	 * @param plain　原文
	 * @return 密文
	 */
	public static String digest(String plain) {
		return PASSWORD_ENCODER.encode(plain);
	}

	/**
	 * Check if plain password and encoded password matches
	 * 
	 * @param plain　原文
	 * @param encoded　密文
	 * @return　是否匹配
	 */
	public static boolean check(String plain, String encoded) {
		return PASSWORD_ENCODER.matches(plain, encoded);
	}
	
}
