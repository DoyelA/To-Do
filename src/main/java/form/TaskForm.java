package form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import constants.messages.ValidationMessages;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import validation.annotations.groups.NotBlankGroup;
import validation.annotations.groups.NotEmptyGroup;
import validation.annotations.groups.NotNullGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskForm {  //only description and user are required
    @NotNull(message=ValidationMessages.TASK_DESCRIPTION_NULL, groups= NotNullGroup.class)
    @NotEmpty(message=ValidationMessages.TASK_DESCRIPTION_EMPTY, groups= NotEmptyGroup.class)
    @NotBlank(message=ValidationMessages.TASK_DESCRIPTION_BLANK, groups= NotBlankGroup.class)
    @Length(max=200, message=ValidationMessages.TASK_DESCRIPTION_LENGTH_EXCEEDED)
    private String description;

    @NotEmpty(message=ValidationMessages.TASK_SKILLS_EMPTY, groups=NotEmptyGroup.class)
    private Set<Long> skillIds;
}
