package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Set<SkillDTO> skillDTO;

}
