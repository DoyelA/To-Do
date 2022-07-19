package com.todo.demo.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.todo.demo.constants.messages.ValidationMessages;
import com.todo.demo.validation.annotations.SkillNameAlreadyExists;
import com.todo.demo.validation.annotations.groups.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data                                              //only name is required
@JsonIgnoreProperties(ignoreUnknown = true)       //ignores values sent in other than name
public class SkillForm {
    //@NotNull(message=ValidationMessages.SKILL_NAME_CANNOT_BE_NULL,groups= NotNullGroup.class)
//    @NotEmpty(message=ValidationMessages.SKILL_NAME_CANNOT_BE_EMPTY,groups= NotEmptyGroup.class)
//    @NotBlank(message=ValidationMessages.SKILL_NAME_CANNOT_BE_BLANK,groups= NotBlankGroup.class)
//    @Length(max=100,message=ValidationMessages.SKILL_NAME_INVALID_LENGTH, groups=LengthGroup.class)
    //@SkillNameAlreadyExists(groups = DBConstraints.class)
    private String name;
}
