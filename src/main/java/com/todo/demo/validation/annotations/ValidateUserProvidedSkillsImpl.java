package com.todo.demo.validation.annotations;

import com.todo.demo.constants.messages.ValidationMessages;
import com.todo.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import com.todo.demo.repository.SkillRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class ValidateUserProvidedSkillsImpl implements ConstraintValidator<ValidateUserProvidedSkills, Set<Long>> {
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private MessageService messageService;

    @Override
    public boolean isValid(Set<Long> skillIds, ConstraintValidatorContext constraintValidatorContext) {
        Set<Long> nonExistingIds=new HashSet<>(skillIds.size());
        skillIds.forEach(skillID->{
            boolean SkillIdExists= skillRepository.existsById(skillID);
            if(!SkillIdExists){
                nonExistingIds.add(skillID);
                constraintValidatorContext.disableDefaultConstraintViolation();
            }
        });
        constraintValidatorContext.buildConstraintViolationWithTemplate(
                MessageService.generateValidationMessage(ValidationMessages.SKILLS_NOT_VALID, nonExistingIds)
        ).addConstraintViolation();
        return nonExistingIds.isEmpty();
    }
}
