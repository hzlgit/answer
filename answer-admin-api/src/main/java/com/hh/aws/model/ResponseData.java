package com.hh.aws.model;

public class ResponseData {
    private String code = "0000";
    private String msg;
    private Object data;
    public ResponseData() {
    }
    public ResponseData(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }
    public ResponseData(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }
    public ResponseData(String msg) {
        this.msg = msg;
    }
    public ResponseData(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
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
