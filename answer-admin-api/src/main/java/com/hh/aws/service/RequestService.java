package com.hh.aws.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.hh.aws.bean.RequestCheckResult;
import com.hh.aws.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service("requestService")
public class RequestService {
	
	final Logger logger = LoggerFactory.getLogger(RequestService.class);
	
	@SuppressWarnings("rawtypes")
	public RequestCheckResult checkRequest(HttpServletRequest request, String... checkParams) {
		RequestCheckResult result = new RequestCheckResult();
		
		//获取所有的请求参数
		Map<String, String> params = new TreeMap<String, String>();
		Map reqMap = request.getParameterMap();
		for (Object key : reqMap.keySet()) {
			params.put(key.toString(), ((String[]) reqMap.get(key))[0]);
		}
		
		logger.info("请求参数："+JSON.toJSONString(params));
		
		//将公共参数添加至需要检测的List
		List<String> checkParamsList = new ArrayList<String>();

		if(null != checkParams && checkParams.length > 0) {
			checkParamsList.addAll(Arrays.asList(checkParams));
		}
		
		//检测不允许为空的参数
		boolean paramsCheck = true;
		List<String> errorParams = new ArrayList<String>();;
		for (String checkParam : checkParamsList) {
			if(!params.containsKey(checkParam) || StringUtil.isEmpty(params.get(checkParam))) {
				errorParams.add(checkParam);
				paramsCheck = false;
			}
		}
		if(!paramsCheck){
			result.setSuccess(false);
			result.setCode("0004");
			result.setMsg("参数["+StringUtil.join(errorParams, ",")+"]为空");
			return result;
		}
		return null;

	}
	
}
