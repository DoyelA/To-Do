package com.todo.demo.validation.annotations;

import com.todo.demo.constants.messages.ValidationMessages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidateUserProvidedSkillsImpl.class)
public @interface ValidateUserProvidedSkills {
    String message() default ValidationMessages.SKILLS_NOT_VALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
