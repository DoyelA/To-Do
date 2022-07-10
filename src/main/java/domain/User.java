package domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="users",schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(allocationSize = 1,initialValue = 1,name="user_seq", schema = "public")
    @Column(name="user_id", nullable=false, unique=true)
    private Long id;
    @Column(name="first_name", nullable=false,length = 50)
    private String firstName;
    @Column(name="last_name", nullable=true,length = 50)
    private String lastName;
    @Column(name="username", nullable=false,length = 50)
    private String username;
    @Column(name="password", nullable=false,length = 50)
    private String password;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch=FetchType.EAGER)                      //one user can have many skills
    @JoinColumn(name="user_skills")
    private Set<Skill> skillSet;          //no duplicate skills should be present
}
