package repository;

import domain.Skill;
import dto.SkillDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    boolean existsByName(String skillName);
@Query(value="select new dto.SkillDTO(id,name) from Skills")      //custom query to fulfill a particular case
    Set<SkillDTO> readSkills();
@Query(value="select new dto.SkillDTO(id) from Skills")
    SkillDTO readSkill();

    boolean existsByIdAndName(Long valueOf, String skillName);

     boolean existsByNameAndIdIsNot(String skillName, Long valueOf);
}
