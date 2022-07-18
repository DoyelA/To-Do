package com.todo.demo.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name="skills",schema = "public")
public class Skill {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "skill_seq")
    @SequenceGenerator(allocationSize = 1,initialValue = 1,name="skill_seq", schema = "public")
    @Column(name="skill_id", nullable=false, unique=true)
    private Long id;
    @Column(name="skill_name", nullable = false, unique = true, length = 100)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Skill skill = (Skill) o;
        return id != null && Objects.equals(id, skill.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
