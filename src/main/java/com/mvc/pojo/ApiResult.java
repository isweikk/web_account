package com.mvc.pojo;

import com.fasterxml.jackson.core.JsonParseException;
import net.sf.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

public class ApiResult {
    private int code;
    private String description;

    public static final int JSON_ERROR = 10001;
    public static final int NETWORK_ERROR = 10002;

    public ApiResult(int errorCode, String errorDesc){
        this.description = errorDesc;
        this.code = errorCode;
    }
    public ApiResult(ExceptionEnum error){
        this.description = error.getDesc();
        this.code = error.getCode();
    }

    public ApiResult(Throwable error){
        this.description = error.getMessage();
        if (error instanceof JsonParseException
            || error instanceof JSONException
            || error instanceof ParseException) {
            this.code = JSON_ERROR;
        } else if (error instanceof UnknownHostException
                || error instanceof ConnectException
                || error instanceof SocketTimeoutException) {
            this.code = NETWORK_ERROR;
        } else {
            this.description = ExceptionEnum.UNKNOWN.getDesc();
            this.code = ExceptionEnum.UNKNOWN.getCode();
        }
    }

    /**
     * Getter method for property <tt>errDesc</tt>.
     *
     * @return property value of errDesc
     */
    public String getErrorDesc() {
        return description;
    }

    /**
     * Setter method for property <tt>errDesc</tt>.
     *
     * @param errDesc value to be assigned to property errDesc
     */
    public void setErrorDesc(String errDesc) {
        this.description = errDesc;
    }

    public int getErrorCode() {
        return code;
    }

    public void setErrorCode(int errCode) {
        this.code = errCode;
    }
}
