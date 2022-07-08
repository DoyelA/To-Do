package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import service.MessageService;

import java.util.Locale;

@Configuration
public class MessageConfig {
    @Bean(value="serviceMessage")
    public MessageSource serviceMessage(){
        ResourceBundleMessageSource serviceMessageSource= new ResourceBundleMessageSource();
        serviceMessageSource.setAlwaysUseMessageFormat(true);
        serviceMessageSource.setCacheMillis(3600);
        serviceMessageSource.setDefaultEncoding("UTF-8");
        serviceMessageSource.setDefaultLocale(Locale.ENGLISH);
        serviceMessageSource.setUseCodeAsDefaultMessage(true);
        serviceMessageSource.setBasename("locale/servicemessage");
        return serviceMessageSource;
    }
}
