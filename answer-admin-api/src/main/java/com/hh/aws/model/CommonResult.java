package com.hh.aws.model;

import java.io.Serializable;

public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = -6306124701034297093L;

    private String code;// 错误代码
    private String msg;// 错误信息
    private T data;

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
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

}