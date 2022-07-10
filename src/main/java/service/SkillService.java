package service;

import domain.Skill;
import form.SkillForm;

import java.util.List;
import java.util.Set;

public interface SkillService {
    public Skill createSkill(SkillForm skillForm);
    public void updateSkill(Long id,SkillForm skillForm);
    public Skill readSkill(Long id);
    public Set<Skill> readSkills();
    public void deleteSkill(Long idr);
}
