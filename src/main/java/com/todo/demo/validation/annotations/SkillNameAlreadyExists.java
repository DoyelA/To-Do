package com.todo.demo.validation.annotations;

import com.todo.demo.constants.messages.ValidationMessages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = SkillNameAlreadyExistsImpl.class)
public @interface SkillNameAlreadyExists {
    String message() default ValidationMessages.SKILL_NAME_ALREADY_TAKEN;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
