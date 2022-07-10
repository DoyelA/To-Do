package form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserForm {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Set<Long> skillIds;
}
