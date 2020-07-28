package com.hh.aws.bean;

import java.io.Serializable;
import java.util.Map;

public class RequestCheckResult implements Serializable {

	private static final long serialVersionUID = -7055211330652418185L;

	private boolean success;
	private String code;// 错误代码
	private String msg;// 错误信息
	
	private Map<String, String> requestParams;


	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Map<String, String> getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(Map<String, String> requestParams) {
		this.requestParams = requestParams;
	}


}
