package com.todo.demo.validation.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import com.todo.demo.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

@Component
public class UsernameAlreadyExistsImpl implements ConstraintValidator<UsernameAlreadyExists, String> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return(httpServletRequest.getMethod().equals(HttpMethod.POST.toString()))
                ?(!userRepository.existsByUsername(username))
                :validateUsernameAlreadyExistsWithSameId(username);
    }
    private boolean validateUsernameAlreadyExistsWithSameId(String username){
        @SuppressWarnings("unchecked")
        Map<String, String> pathVariables= (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String id=pathVariables.get("id");
        return !userRepository.existsByUsernameAndIdIsNot(username, Long.valueOf(id));
    }
}
