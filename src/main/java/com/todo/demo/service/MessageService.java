package com.todo.demo.service;

public interface MessageService {
//    3 diff methods:
//    i) handling messages for service layer
//    ii) handling the validation layer
//    iii) handling the error code
String generateServiceMessage(String messageCode, Object...objects);   //object array

    static String generateValidationMessage(String messageCode, Object... objects) {
        return null;
    }

    static String generateErrorCode(String errorCode) {
        return null;
    }

    static String generateExceptionMessage(String exceptionMessage, Object...objects){
        return null;
    }

}
