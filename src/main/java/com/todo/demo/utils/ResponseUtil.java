package com.todo.demo.utils;

import com.todo.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.todo.demo.dto.ResponseDTO;
import org.springframework.validation.FieldError;

@Component
@Scope(scopeName = "PROTOTYPE")
public class ResponseUtil<T> {
@Autowired
    private MessageService messageService;
    //3 methods to handle controller response, service response and validation response

    public ResponseEntity<ResponseDTO<T>> generateControllerResponse(ResponseDTO<T> responseDTO){
        return new ResponseEntity<ResponseDTO<T>>(responseDTO, HttpStatus.valueOf(responseDTO.getHttpStatus()));
    }

    public ResponseEntity<ResponseDTO<T>> generateValidationResponse(FieldError fieldError, boolean b, int value, String message){
        ResponseDTO<T> responseDTO=new ResponseDTO<>();
        responseDTO.setData(null);
        responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        responseDTO.setSuccess(false);
        responseDTO.setMessage(MessageService.generateValidationMessage(message));
        responseDTO.setErrorCode(MessageService.generateErrorCode(message));
        return new ResponseEntity<ResponseDTO<T>>(responseDTO, HttpStatus.BAD_REQUEST);    //http status for a validation response will always be bad message
    }

    public ResponseDTO<T> generateServiceResponse(String message, boolean success, T data, int httpStatus){
        ResponseDTO<T> responseDTO=new ResponseDTO<>();
        responseDTO.setData(data);
        responseDTO.setHttpStatus(httpStatus);
        responseDTO.setSuccess(success);
        responseDTO.setMessage(MessageService.generateValidationMessage(message));
        responseDTO.setErrorCode(MessageService.generateErrorCode(message));
        return responseDTO;
    }
}
