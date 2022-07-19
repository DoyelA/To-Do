package com.todo.demo.exception;

import com.todo.demo.service.MessageService;
import com.todo.demo.utils.ResponseUtil;
import com.todo.demo.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageService messageService;
    @ExceptionHandler(value=SkillException.class )
    public ResponseEntity<ResponseDTO<Object>> handleSkillException(SkillException skillException){
        ResponseDTO<Object> responseDTO=new ResponseDTO<>();
        responseDTO.setData(skillException.getData());
        responseDTO.setHttpStatus(skillException.getStatus());
        responseDTO.setMessage(messageService.generateServiceMessage(skillException.getMessage(), skillException.getDynamicData()));
        responseDTO.setErrorCode(MessageService.generateErrorCode(skillException.getMessage()));
        responseDTO.setSuccess(false);
        return new ResponseUtil<Object>().generateControllerResponse(responseDTO);
    }
    @ExceptionHandler(value=UserException.class )
    public ResponseEntity<ResponseDTO<Object>> handleUserException(UserException userException){
        ResponseDTO<Object> responseDTO=new ResponseDTO<>();
        responseDTO.setData(userException.getData());
        responseDTO.setHttpStatus(userException.getStatus());
        responseDTO.setMessage(messageService.generateServiceMessage(userException.getMessage(), userException.getDynamicData()));
        responseDTO.setErrorCode(MessageService.generateErrorCode(userException.getMessage()));
        responseDTO.setSuccess(false);
        return new ResponseUtil<Object>().generateControllerResponse(responseDTO);
    }

    @ExceptionHandler(value=TaskException.class )
    public ResponseEntity handleUserException(TaskException taskException){
        ResponseDTO responseDTO=new ResponseDTO<>();
        responseDTO.setData(taskException.getData());
        responseDTO.setHttpStatus(taskException.getStatus());
        responseDTO.setMessage(messageService.generateServiceMessage(taskException.getMessage(), taskException.getDynamicData()));
        responseDTO.setErrorCode(MessageService.generateErrorCode(taskException.getMessage()));
        responseDTO.setSuccess(false);
        return new ResponseUtil<Object>().generateControllerResponse(responseDTO);
    }
}
