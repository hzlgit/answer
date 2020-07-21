package com.hh.aws.model;

import com.hh.aws.model.enums.ResultCode;

public class ResponseData {
    private String code = "0000";
    private String msg;
    private Object data;
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
    public ResponseData(ResultCode resultCode, Object data) {
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

    public void setData(Object data) {
        this.data = data;
    }
}
