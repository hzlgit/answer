package com.hh.aws.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {

	/**
	 * 替换为NULL的字符串为“”
	 * @param value
	 * @return
	 */
	public static String replaceNull(String value){
		if(null == value)
			return "";
		return value;
	}

	/**
	 * 格式化字符串，如果为NULL返回空字符串，去除首尾空字符
	 *
	 * @param value
	 * @return
	 */
	public static String formatString(String value) {
		if(null == value)
			return "";
		return value.trim();
	}

	/**
	 * 检查指定的字符串是否为空。
	 * <ul>
	 * <li>StringUtil.isEmpty(null) = true</li>
	 * <li>StringUtil.isEmpty("") = true</li>
	 * <li>StringUtil.isEmpty("   ") = true</li>
	 * <li>StringUtil.isEmpty("abc") = false</li>
	 * </ul>
	 *
	 * @param value
	 *            待检查的字符串
	 * @return true/false
	 */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查指定的字符串是否不为空。
	 * <ul>
	 * <li>StringUtil.isNotEmpty(null) = false</li>
	 * <li>StringUtil.isNotEmpty("") = false</li>
	 * <li>StringUtil.isNotEmpty("   ") = false</li>
	 * <li>StringUtil.isNotEmpty("abc") = true</li>
	 * </ul>
	 *
	 * @param value
	 *            待检查的字符串
	 * @return true/false
	 */
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	/**
	 * 检查指定的字符串列表是否不为空。
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	/**
	 * 把通用字符编码的字符串转化为汉字编码。
	 */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}


	/**
	 * 隐藏手机号码中间4位
	 *
	 * @param mobile
	 * @return
	 */
	public static String hideMobileMiddle(String mobile){
		if(isNotEmpty(mobile)){
			return mobile.substring(0,3)+"****"+mobile.substring(7);
		}
		return "";
	}

	/**
	 * 隐藏银行卡中间位数，保留前面4为和后面4位
	 *
	 * @param cardNum
	 * @return
	 */
	public static String hideBankCardNum(String cardNum){
		if(isNotEmpty(cardNum)){
			return cardNum.substring(0, 4)+"****"+cardNum.substring(cardNum.length()-4,cardNum.length());
		}
		return "";
	}

	/**
	 * 隐藏姓名中的姓氏
	 *
	 * @param name
	 * @return
	 */
	public static String hideSurname(String name){
		if(isNotEmpty(name)){
			int length = name.length();
			if(length == 2 || length == 3){
				return "*"+name.substring(1, length);
			}else if(length == 4){
				return "*"+name.substring(2, length);
			}else{
				return "*"+name.substring(length-2, length);
			}
		}
		return "";
	}

	/**
	 * 生成随机数字和字母组合
	 *
	 * @param length[生成随机数的长度]
	 * @return
	 */
	public static String getCharAndNumr(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				// 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}


	/**
	 * 判断值是否为TRUE
	 *
	 * @param value
	 * @return
	 */
	public static boolean isTure(String value){
		return value != null && ArrayUtils.indexOf(new String[]{"1", "y","yes","true"}, value.toLowerCase()) != -1;
	}

	/**
	 * 判断是否为数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern
				.compile("^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$");
		java.util.regex.Matcher match = pattern.matcher(str);
		if (match.matches() == false) {
			return false;
		} else {
			return true;
		}
	}

	public static String join(Object[] array, String flag) {
		StringBuffer str_buff = new StringBuffer();

		for (int i = 0, len = array.length; i < len; i++) {
			str_buff.append(String.valueOf(array[i]));
			if (i < len - 1)
				str_buff.append(flag);
		}

		return str_buff.toString();
	}

	public static String join(List<String> list, String flag) {
		StringBuffer str_buff = new StringBuffer();

		for (int i = 0, len = list.size(); i < len; i++) {
			str_buff.append(String.valueOf(list.get(i)));
			if (i < len - 1)
				str_buff.append(flag);
		}

		return str_buff.toString();
	}

	public static String filterEmoji(String source, String replace) {
		if (source != null) {
			Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher emojiMatcher = emoji.matcher(source);
			if (emojiMatcher.find()) {
				source = emojiMatcher.replaceAll(replace);
				return source;
			}
			return source;
		}
		return source;
	}

	/**
	 * 截取字符串
	 *
	 * @param source
	 * @param size
	 * @return
	 */
	public static String substring(String source, int size){
		if(null != source){
			if(source.length() > size)
				return source.substring(0, size);
		}
		return source;
	}

	public static void main(String[] args) {
		//System.out.println(StringUtil.isNumber("-1212120.073"));

		System.out.println(StringUtil.isEmpty(null));
		System.out.println(StringUtil.isEmpty(""));
		System.out.println(StringUtil.isEmpty(" "));
		System.out.println(StringUtil.isEmpty("aa"));
		System.out.println("===================");
		System.out.println(StringUtil.isNotEmpty(null));
		System.out.println(StringUtil.isNotEmpty(""));
		System.out.println(StringUtil.isNotEmpty(" "));
		System.out.println(StringUtil.isNotEmpty("aa"));

		String result = StringUtil.filterEmoji("😄😃😀😊☺😉😍😘😚😗😙🎨👂", "*");
		System.out.println(result);

		String result2 = StringUtil.substring("asdfghj", 8);
		System.out.println(result2);

	}

}
