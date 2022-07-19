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
    @Column(name="skill_name", nullable = false, unique = true)
    private String name;
}
