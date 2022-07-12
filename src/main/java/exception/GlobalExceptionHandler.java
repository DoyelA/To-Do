package exception;

import dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import service.MessageService;
import utils.ResponseUtil;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageService messageService;
    @ExceptionHandler(value=SkillException.class )
    public ResponseEntity<ResponseDTO<?>> handleSkillException(SkillException skillException){
        ResponseDTO responseDTO=new ResponseDTO<>();
        responseDTO.setData(skillException.getData());
        responseDTO.setHttpStatus(skillException.getStatus());
        responseDTO.setMessage(messageService.generateServiceMessage(skillException.getMessage(), skillException.getDynamicData()));
        responseDTO.setErrorCode(MessageService.generateErrorCode(skillException.getMessage()));
        responseDTO.setSuccess(false);
        return new ResponseUtil<ResponseDTO<Object>>().generateControllerResponse(responseDTO);
    }
}
