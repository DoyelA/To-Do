package form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data                                              //only name is required
@JsonIgnoreProperties(ignoreUnknown = true)       //ignores values sent in other than name
public class SkillForm {
    private String name;
}
