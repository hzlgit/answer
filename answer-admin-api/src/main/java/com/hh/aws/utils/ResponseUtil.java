package com.hh.aws.utils;

import com.hh.aws.utils.gson.GsonUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseUtil {
	
	/**
	 * 返回字符串
	 * 
	 * @param content
	 * @return
	 */
	public static ResponseEntity<String> responseEntity(String content){
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/plain;charset=utf-8");
        return new ResponseEntity<String>(content, headers, HttpStatus.OK);
	}
	
	/**
	 * 返回JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static ResponseEntity<String> responseEntity(Object obj){
		String content = GsonUtil.toJSONString(obj, "yyyy-MM-dd HH:mm:ss");
		
		return responseEntity(content);
	}
	
	/**
	 * 返回JSON字符串，格式化时间
	 * 
	 * @param obj
	 * @param dateFormat 日期格式
	 * @return
	 */
	public static ResponseEntity<String> responseEntity(Object obj, String dateFormat){
		String content = GsonUtil.toJSONString(obj, dateFormat);
		
		return responseEntity(content);
	}
	
	/**
	 * 返回JSON字符串
	 * 
	 * @param obj
	 * @param keys 要排除的对象属性
	 * @return
	 */
	public static ResponseEntity<String> responseEntity(Object obj, String[] keys){
		String content = GsonUtil.toJSONString(obj, keys);
		
		return responseEntity(content);
	}
	
	
	/**
	 * 返回JSON字符串
	 * 
	 * @param obj
	 * @param dateFormat 日期格式
	 * @param keys 要排除的对象属性
	 * @return
	 */
	public static ResponseEntity<String> responseEntity(Object obj, String dateFormat, String[] keys){
		String content = GsonUtil.toJSONString(obj, dateFormat, keys);
		
		return responseEntity(content);
	}
	
}
