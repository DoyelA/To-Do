package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import service.MessageService;
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
    @Override
    public String generateServiceMessage(String messageCode, Object... objects) {
        return serviceMessage.getMessage(messageCode, objects, LocaleContextHolder.getLocale());
    }

    @Override
    public String generateValidationMessage(String messageCode, Object... objects) {
        return validationMessage.getMessage(messageCode, objects, LocaleContextHolder.getLocale());
    }

    @Override
    public String generateErrorCode(String errorCode) {
        return this.errorMessage.getMessage(errorCode, null, LocaleContextHolder.getLocale());
    }
}
