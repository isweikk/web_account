package com.mvc.pojo;

import org.apache.ibatis.executor.ErrorContext;

public class ApiException extends RuntimeException{
    /** 错误码 */
    private int code;
    private String description;
    /** 错误上下文 */
    private ErrorContext errorContext;


    public ApiException(int errorCode, String errorDesc){
        super(errorDesc);
        this.code = errorCode;
    }
    public ApiException(ExceptionEnum error){
        super(error.getDesc());
        this.code = error.getCode();
    }

    public ApiException(int errorCode, String errorDesc, Throwable throwable){
        super(errorDesc,throwable);
        this.code = errorCode;
    }
    public ApiException(ExceptionEnum error, Throwable throwable){
        super(error.getDesc(), throwable);
        this.code = error.getCode();
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

    /**
     * Getter method for property <tt>errorContext</tt>.
     *
     * @return property value of errorContext
     */
    public ErrorContext getErrorContext() {
        return errorContext;
    }

    /**
     * Setter method for property <tt>errorContext</tt>.
     *
     * @param errorContext value to be assigned to property errorContext
     */
    public void setErrorContext(ErrorContext errorContext) {
        this.errorContext = errorContext;
    }
}
