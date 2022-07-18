package com.todo.demo.validation.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import com.todo.demo.repository.SkillRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class ValidateSkillIDImpl implements ConstraintValidator<ValidateSkillID, Set<Long>> {
   @Autowired
    private SkillRepository skillRepository;

    @Override
    public boolean isValid(Set<Long> skillIds, ConstraintValidatorContext constraintValidatorContext) {
        Set<Long> nonExistingIds=new HashSet<>(skillIds.size());
        skillIds.forEach(skillId->{
            boolean SkillIDExists=skillRepository.existsById(skillId);
            if(!SkillIDExists){
                nonExistingIds.add(skillId);
            }
        });
        return nonExistingIds.isEmpty();         //if empty, will return true, else false
    }
}
