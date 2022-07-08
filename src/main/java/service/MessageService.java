package service;

public interface MessageService {
//    3 diff methods:
//    i) messages for service layer
//    ii) messages for validation layer
//    iii) to handle the error code
    public String generateServiceMessage(String messageCode, Object...objects);
    public String generateValidationMessage(String messageCode, Object...objects);
    public String generateErrorCode(String errorCode);
}
