package com.todo.demo.validation.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import com.todo.demo.repository.SkillRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

@Component
public class SkillNameAlreadyExistsImpl implements ConstraintValidator<SkillNameAlreadyExists, String> {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @SuppressWarnings("unlikely-arg-type")
    @Override
    public boolean isValid(String skillName, ConstraintValidatorContext constraintValidatorContext) {
        return (httpServletRequest.getMethod().equals(HttpMethod.POST))?(!skillRepository.existsByName(skillName)):validateSkillNameAlreadyExistsWithSameID(skillName); //if name exists(true), method should not be valid
    }

    private boolean validateSkillNameAlreadyExistsWithSameID(String skillName) {
        Map<String, String> pathVariables=(Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String id=pathVariables.get("id");
        return !skillRepository.existsByNameAndIdIsNot(skillName, Long.valueOf(id));
    }
}
