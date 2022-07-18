package com.todo.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
//the beans will handle the messages from the property file
@Configuration
public class MessageConfig {
    @Bean(value="serviceMessage")
    public MessageSource serviceMessage(){

        //To use ResourceBundleMessageSource, we have to create a bean for it in @Configuration class.
        ResourceBundleMessageSource serviceMessageSource = new ResourceBundleMessageSource();
        serviceMessageSource.setAlwaysUseMessageFormat(true);
        serviceMessageSource.setCacheMillis(3600);
        serviceMessageSource.setDefaultEncoding("UTF-8");
        serviceMessageSource.setDefaultLocale(Locale.ENGLISH);
        serviceMessageSource.setUseCodeAsDefaultMessage(true);
        serviceMessageSource.setBasename("locale/servicemessage");  //we do not include _en
        return serviceMessageSource;
    }

    @Bean(value="validationMessage")
    public MessageSource validationMessage(){
        ResourceBundleMessageSource validationMessageSource = new ResourceBundleMessageSource();
        validationMessageSource.setAlwaysUseMessageFormat(true);
        validationMessageSource.setCacheMillis(3600);
        validationMessageSource.setDefaultEncoding("UTF-8");
        validationMessageSource.setDefaultLocale(Locale.ENGLISH);
        validationMessageSource.setUseCodeAsDefaultMessage(true);
        validationMessageSource.setBasename("locale/validationmessage");  //we do not include _en
        return validationMessageSource;
    }

    @Bean(value="exceptionMessage")
    public MessageSource exceptionMessage(){
        ResourceBundleMessageSource exceptionMessageSource = new ResourceBundleMessageSource();
        exceptionMessageSource.setAlwaysUseMessageFormat(true);
        exceptionMessageSource.setCacheMillis(3600);
        exceptionMessageSource.setDefaultEncoding("UTF-8");
        exceptionMessageSource.setDefaultLocale(Locale.ENGLISH);
        exceptionMessageSource.setUseCodeAsDefaultMessage(true);
        exceptionMessageSource.setBasename("locale/exceptionmessage");  //we do not include _en
        return exceptionMessageSource;
    }

    @Bean(value="errorCodeMessage")
    public MessageSource errorMessage(){
        ResourceBundleMessageSource errorMessageSource= new ResourceBundleMessageSource();
        errorMessageSource.setAlwaysUseMessageFormat(true);
        errorMessageSource.setCacheMillis(3600);
        errorMessageSource.setDefaultEncoding("UTF-8");
        errorMessageSource.setDefaultLocale(Locale.ENGLISH);
        errorMessageSource.setUseCodeAsDefaultMessage(true);
        errorMessageSource.setBasename("locale/errorCode");  //we do not include _en
        return errorMessageSource;
    }
    @Bean
    public SessionLocaleResolver localeResolver(){                                 //keeps running through the session, supports locale change
        SessionLocaleResolver sessionLocaleResolver=new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return sessionLocaleResolver;
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){                                        //can change language during runtime
        LocaleChangeInterceptor localeChangeInterceptor=new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
}
