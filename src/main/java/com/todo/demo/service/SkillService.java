package com.todo.demo.service;

import com.todo.demo.dto.ResponseDTO;
import com.todo.demo.form.SkillForm;
import com.todo.demo.dto.SkillDTO;

import java.util.Set;

public interface SkillService {
    ResponseDTO<SkillDTO> createSkill(SkillForm skillForm);
    ResponseDTO<SkillDTO> updateSkill(Long id, SkillForm skillForm);
    ResponseDTO<SkillDTO> readSkill(Long id);
    ResponseDTO<Set<SkillDTO>> readSkills();
    ResponseDTO<Void> deleteSkill(Long id);

}
