package domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="skill",schema = "public")
public class Skill {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "skill_seq")
    @SequenceGenerator(allocationSize = 1,initialValue = 1,name="skill_seq", schema = "public")
    @Column(name="skill_id", nullable=false, unique=true)
    private Long id;
    @Column(name="skill_name", nullable = false, unique = true,length = 100)
    private String name;
}
