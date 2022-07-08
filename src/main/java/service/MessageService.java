package service;

public interface MessageService {
//    3 diff methods:
//    i) handling messages for service layer
//    ii) handling the validation layer
//    iii) handling the error code
    public String generateServiceMessage(String messageCode, Object...objects);   //object array
    public String generateValidationMessage(String messageCode, Object...objects);
    public String generateErrorCode(String errorCode);
}
