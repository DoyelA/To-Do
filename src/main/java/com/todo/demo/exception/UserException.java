package com.todo.demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class UserException extends BaseException {

    private static final long serialVersionUID=1L;
    public UserException(String message, int status, Object data, Object[] dynamicData) {
        super(message, status, data, dynamicData);
    }
    public UserException(String message, int status, Object data) {
        super(message, status, data);
    }
    public UserException(String message, int status) {
        super(message, status);
    }
}
