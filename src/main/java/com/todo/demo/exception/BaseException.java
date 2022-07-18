package com.todo.demo.exception;

import lombok.Getter;
@Getter
public class BaseException extends java.lang.RuntimeException {

    private static final long SerialVersionUID=1L;
    private final String message;
    private final int status;
    private final Long timeStamp=System.currentTimeMillis();
    private final Object data;
    private final Object[] dynamicData;

    public BaseException(String message, int status, Object data, Object[] dynamicData) {
        super();
        this.message = message;
        this.status = status;
        this.data = data;
        this.dynamicData = dynamicData;
    }
    public BaseException(String message, int status) {
        super(message);
        this.message = message;
        this.status = status;
        this.data=null;
        this.dynamicData=null;
    }

    public BaseException(String message, int status, Object data) {
        super();
        this.message=message;
        this.status=status;
        this.data=data;
        this.dynamicData=null;
    }
}
