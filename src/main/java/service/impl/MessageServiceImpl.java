package service.impl;

import org.springframework.stereotype.Service;
import service.MessageService;
@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public String generateServiceMessage(String messageCode, Object... objects) {
        return null;
    }

    @Override
    public String generateValidationMessage(String messageCode, Object... objects) {
        return null;
    }

    @Override
    public String generateErrorCode(String errorCode) {
        return null;
    }
}
