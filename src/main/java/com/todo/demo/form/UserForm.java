package com.todo.demo.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.todo.demo.constants.messages.ValidationMessages;
import com.todo.demo.validation.annotations.UsernameAlreadyExists;
import com.todo.demo.validation.annotations.ValidateSkillID;
import com.todo.demo.validation.annotations.groups.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserForm {
    @NotNull(message= ValidationMessages.FIRST_NAME_NULL, groups= NotNullGroup.class)
    @NotEmpty(message=ValidationMessages.FIRST_NAME_BLANK, groups= NotEmptyGroup.class)
    @NotBlank(message=ValidationMessages.FIRST_NAME_EMPTY, groups= NotBlankGroup.class)
    @Length(min=0, max=50, message = ValidationMessages.LENGTH_EXCEEDED,groups= LengthGroup.class)
    private String firstName;
    @Length(min=0,max=100, message = ValidationMessages.LENGTH_EXCEEDED,groups= LengthGroup.class)
    private String lastName;
    @NotNull(message=ValidationMessages.USERNAME_NULL, groups=NotNullGroup.class)
    @NotEmpty(message=ValidationMessages.USERNAME_EMPTY, groups=NotEmptyGroup.class)
    @NotBlank(message=ValidationMessages.USERNAME_BLANK, groups=NotBlankGroup.class)
    @UsernameAlreadyExists(groups= DBConstraints.class)
    private String username;
    @NotNull(message=ValidationMessages.PASSWORD_NULL, groups=NotNullGroup.class)
    @NotEmpty(message=ValidationMessages.PASSWORD_EMPTY, groups=NotEmptyGroup.class)
    @NotBlank(message=ValidationMessages.PASSWORD_BLANK, groups=NotBlankGroup .class)
    @Length(max=200, message = ValidationMessages.LENGTH_EXCEEDED, groups= LengthGroup.class)
    private String password;
    @NotEmpty(message=ValidationMessages.SKILLS_EMPTY, groups=NotEmptyGroup.class)
    @ValidateSkillID(groups= DBConstraints.class)
    private Set<Long> skillIds=new HashSet<>();
}
