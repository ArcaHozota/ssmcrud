package jp.co.toshiba.ppok.utils;

import org.springframework.lang.Nullable;

/**
 * 通用判斷工具類
 *
 * @author Administrator
 *
 */
public class StringUtils extends org.springframework.util.StringUtils {

	/**
	 * 判斷該字符串是否爲空
	 *
	 * @param str 目標字符串
	 * @return boolean
	 */
	public static boolean isEmpty(@Nullable final String str) {
		return (str == null || str.length() == 0 || str.trim().equals(""));
	}

	/**
	 * 判斷該字符串是否不為空
	 *
	 * @param str 目標字符串
	 * @return boolean
	 */
	public static boolean isNotEmpty(@Nullable final String str) {
		return !isEmpty(str);
	}

	/**
	 * 兩者相等
	 *
	 * @param str1 值
	 * @param str2 值
	 * @return 判斷結果
	 */
	public static boolean isEqual(@Nullable final String str1, @Nullable final String str2) {
		boolean isEqual = false;
		if (str1 == null && str2 == null) {
			return true;
		} else if (str1 == null || str2 == null) {
			return false;
		} else if (str1.length() != str2.length()) {
			return false;
		} else {
			isEqual = str1.trim().equals(str2.trim());
		}
		return isEqual;
	}

	/**
	 * 兩者不等
	 *
	 * @param str1 值
	 * @param str2 值
	 * @return 判斷結果
	 */
	public static boolean isNotEqual(@Nullable final String str1, @Nullable final String str2) {
		return !isEqual(str1, str2);
	}
}
