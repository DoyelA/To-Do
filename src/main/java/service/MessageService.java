package service;

public interface MessageService {
//    3 diff methods:
//    i) handling messages for service layer
//    ii) handling the validation layer
//    iii) handling the error code
    public String generateServiceMessage(String messageCode, Object...objects);   //object array

    public static String generateValidationMessage(String messageCode, Object... objects) {
        return null;
    }

    public static String generateErrorCode(String errorCode) {
        return null;
    }

    public static String generateExceptionMessage(String exceptionMessage, Object...objects){
        return null;
    }

}
