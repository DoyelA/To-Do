package com.todo.demo.service.impl;

import com.todo.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    @Qualifier("serviceMessage")
    private MessageSource serviceMessage;

    @Autowired
    @Qualifier("validationMessage")
    private MessageSource validationMessage;

    @Autowired
    @Qualifier("errorCodeMessage")
    private MessageSource errorMessage;

    @Autowired
    @Qualifier("exceptionMessage")
    private MessageSource exceptionMessage;
    @Override
    public String generateServiceMessage(String messageCode, Object... objects) {
        return serviceMessage.getMessage(messageCode, objects, LocaleContextHolder.getLocale());
    }

    public String generateValidationMessage(String messageCode, Object... objects) {
        return validationMessage.getMessage(messageCode, objects, LocaleContextHolder.getLocale());
    }

    public String generateErrorCode(String errorCode) {
        return this.errorMessage.getMessage(errorCode, null, LocaleContextHolder.getLocale());
    }
    public String generateExceptionMessage(String exceptionMessage, Object...objects){
    return this.exceptionMessage.getMessage(exceptionMessage, objects, LocaleContextHolder.getLocale());
    }
}

