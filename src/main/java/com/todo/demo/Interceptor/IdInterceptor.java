package com.todo.demo.Interceptor;

import com.todo.demo.constants.url.ApiUrl;
import com.todo.demo.exception.SkillException;
import com.todo.demo.exception.UserException;
import com.todo.demo.repository.UserRepository;
import com.todo.demo.constants.messages.ExceptionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.todo.demo.repository.SkillRepository;
import com.todo.demo.repository.TaskRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class IdInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if("PUT".equals(request.getMethod())||"GET".equals(request.getMethod())||"DELETE".equals(request.getMethod())){
            @SuppressWarnings("unchecked")
            Map<String, String> pathVariablesMap = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            if(pathVariablesMap.isEmpty()){
                return true;
            }
            String id = pathVariablesMap.get("id");

            if (request.getRequestURI().contains(ApiUrl.SKILL_URL)) {
                boolean skillAlreadyExists = skillRepository.existsById(Long.valueOf(id));
                if (skillAlreadyExists) {
                    return true;
                }
                throw new SkillException(ExceptionMessage.SKILL_NOT_FOUND, HttpStatus.NOT_FOUND.value());
            }
            if (request.getRequestURI().contains(ApiUrl.USER_URL)) {
                boolean userAlreadyExists=userRepository.existsById(Long.valueOf(id));
                if (userAlreadyExists) {
                    return true;
                }
                throw new UserException(ExceptionMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.value());
            }
        }
        return true;
    }
}
