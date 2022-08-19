package com.icode.common.exception;

/**
 * deposit 业务异常类
 * @Author: tiantianlikeU。
 * @Date: 2022/8/18 09:55
 */
public class BusinessException extends RuntimeException {


    public BusinessException(String errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
    }


    private String errCode;


    public String getErrCode() {
        return errCode;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
