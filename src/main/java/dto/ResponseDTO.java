package dto;
//used to pass data with multiple attributes in one shot from client
//to server, to avoid multiple calls to a remote server
public class ResponseDTO<T> {
    private T data;
    private boolean success;
    private int httpStatus;
    private String message;
    private String errorCode;
    private Long timeStamp=System.currentTimeMillis();
}
