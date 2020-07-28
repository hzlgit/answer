package com.hh.aws.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	
	/**
	 * 将对象转换成JSON字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<Object>()).disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(object);
	}
	
	/**
	 * 将对象转换成JSON字符串，同时格式化时间
	 * 
	 * @param object
	 * @param dateFormat
	 * @return
	 */
	public static String toJSONString(Object object, String dateFormat) {
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<Object>()).disableHtmlEscaping().serializeNulls().setDateFormat(dateFormat).create();
		return gson.toJson(object);
	}

	/**
	 * 将对象转换成JSON字符串，同时过滤不需要的属性
	 * 
	 * @param object
	 * @param filterFields
	 * @return
	 */
	public static String toJSONString(Object object, String[] filterFields) {
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<Object>()).disableHtmlEscaping().serializeNulls().setExclusionStrategies(new GsonKit(filterFields)).create();
		return gson.toJson(object);
	}
	
	/**
	 * 将对象转换成JSON字符串，同时格式化时间和过滤不需要的属性
	 * 
	 * @param object
	 * @param dateFormat
	 * @param filterFields
	 * @return
	 */
	public static String toJSONString(Object object, String dateFormat, String[] filterFields) {
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<Object>()).disableHtmlEscaping().serializeNulls().setDateFormat(dateFormat).setExclusionStrategies(new GsonKit(filterFields)).create();
		return gson.toJson(object);
	}

}
