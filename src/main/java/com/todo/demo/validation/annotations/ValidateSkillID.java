package com.todo.demo.validation.annotations;

import com.todo.demo.constants.messages.ValidationMessages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = ValidateSkillIDImpl.class)
public @interface ValidateSkillID {
    String message() default ValidationMessages.SKILL_ID_NOT_VALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
