package com.hh.aws.model.enums;

public enum ResultCode {

    SUCCESS("0000", "操作成功"),
    FAILED("0009","操作失败"),
    ParamError("0100","参数错误");

    private String code;
    private String msg;

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

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
