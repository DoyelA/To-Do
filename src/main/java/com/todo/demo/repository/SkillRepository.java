package com.todo.demo.repository;

import com.todo.demo.domain.Skill;
import com.todo.demo.dto.SkillDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.Set;
public interface SkillRepository extends JpaRepository<Skill, Long> {

    boolean existsByName(String skillName);
@Query(value="select new com.todo.demo.dto.SkillDTO(id,name) from Skill")      //custom query to fulfill a particular case
    Set<SkillDTO> readSkills();
@Query(value="select new com.todo.demo.dto.SkillDTO(id,name) from Skill where id=:id")
    SkillDTO readSkill(@Param("id") Long id);
     boolean existsByNameAndIdIsNot(String skillName, Long valueOf);
}
