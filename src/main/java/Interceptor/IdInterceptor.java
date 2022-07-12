package Interceptor;

import constants.messages.ExceptionMessage;
import constants.url.ApiUrl;
import exception.SkillException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import repository.SkillRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class IdInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        @SuppressWarnings("unchecked")
        Map<String, String> pathVariablesMap = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String id = pathVariablesMap.get("id");
        boolean skillAlreadyExists = skillRepository.existsById(Long.valueOf(id));
        if (request.getRequestURI().contains(ApiUrl.SKILL_URL)) {
            if (skillAlreadyExists) {
                return true;
            }
            throw new SkillException(ExceptionMessage.SKILL_NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }
        return true;
    }
}
