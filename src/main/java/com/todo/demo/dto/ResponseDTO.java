package com.todo.demo.dto;

import lombok.Data;

//used to pass data with multiple attributes in one shot from client
//to server, to avoid multiple calls to a remote server

//when we pass it to the service class, we want to return a response type
//we cannot pass an entire entity class as a response type
@Data
public class ResponseDTO<T> {
    private T data;
    private boolean success;
    private int httpStatus;
    private String message;
    private String errorCode;
    private Long timeStamp=System.currentTimeMillis();
}
