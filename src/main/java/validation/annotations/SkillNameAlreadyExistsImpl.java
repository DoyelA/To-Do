package validation.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.SkillRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class SkillNameAlreadyExistsImpl implements ConstraintValidator<SkillNameAlreadyExists, String> {
    @Autowired
    private SkillRepository skillRepository;
    @Override
    public boolean isValid(String skillName, ConstraintValidatorContext constraintValidatorContext) {
        return !skillRepository.existsByName(skillName);              //if name exists(true), method should not be valid
    }
}
