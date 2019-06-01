package com.mvc.pojo;

public enum ExceptionEnum {
    NOERROR(0,"noError"),
    UNKNOWN(1,"unknown"),
    SUCCESS(200, "success");

    private int code;
    private String desc;

    ExceptionEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDesc(String errDesc) {
        this.desc = desc;
    }
}
