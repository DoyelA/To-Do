package service;

import domain.Skill;
import dto.ResponseDTO;
import dto.SkillDTO;
import form.SkillForm;

import java.util.List;
import java.util.Set;

public interface SkillService {
    public ResponseDTO<SkillDTO> createSkill(SkillForm skillForm);
    public ResponseDTO<SkillDTO> updateSkill(Long id,SkillForm skillForm);
    public ResponseDTO<SkillDTO> readSkill(Long id);
    public ResponseDTO<Set<SkillDTO>> readSkills();
    public ResponseDTO<Void> deleteSkill(Long id);

}
