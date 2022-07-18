package com.todo.demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value= JsonInclude.Include.NON_NULL)

public class TaskException extends BaseException{
    private static final long SerialVersionUID=1L;

    public TaskException(String message, int status, Object data, Object[] dynamicData) {
        super(message, status, data, dynamicData);
    }

    public TaskException(String message, int status) {
        super(message, status);
    }

    public TaskException(String message, int status, Object data) {
        super(message, status, data);
    }
}
