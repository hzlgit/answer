package com.hh.aws.model;

import com.hh.aws.model.enums.ResultCode;
import java.io.Serializable;

public class ResponseData<T> implements Serializable {
    private String code = "0000";
    private String msg;
    private T data;
    public ResponseData() {
    }
    public ResponseData(ResultCode resultCode) {
        this.msg = resultCode.getMsg();
        this.code = resultCode.getCode();
    }
    public ResponseData(String resultCode) {
        this.code = resultCode;
    }
    public ResponseData(String msg,String code) {
        this.msg = msg;
        this.code = code;
    }
    public ResponseData(ResultCode resultCode, T data) {
        this.msg = resultCode.getMsg();
        this.code = resultCode.getCode();
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
