package com.huzijun.yizunetwork.common;

/**
 * 业务相关异常
 * <p>
 * Writed by fengjy
 * Created on 3/8/17
 */
public class BusinessBaseException extends RuntimeException {

    private final static  long serialVersionUID = -1;

    private int status = 500;   //默认500

    public BusinessBaseException(String message) {
        super(message);
    }

    public BusinessBaseException(String message, int status) {
        super(message);
        this.status=status;
    }

    public BusinessBaseException(String message , Throwable casuse , int status) {
        super(message,casuse);
        this.status = status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static final BusinessBaseException fail(String message){
        return  new BusinessBaseException(message);
    }

    public static final BusinessBaseException fail(int status ,String message){
        BusinessBaseException exception = new BusinessBaseException(message);
        exception.setStatus(status);
        return exception;
    }
}
