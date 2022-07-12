package service.impl;

import constants.messages.ExceptionMessage;
import constants.messages.ServiceMessage;
import domain.Skill;
import dto.ResponseDTO;
import dto.SkillDTO;
import exception.SkillException;
import form.SkillForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import repository.SkillRepository;
import service.MessageService;
import service.SkillService;
import utils.ResponseUtil;

import java.util.Set;

public class SkillServiceImplementation implements SkillService {
    @Autowired
    private SkillRepository skillRepository;
    private MessageService messageService;
    @Override
    public ResponseDTO<SkillDTO> createSkill(SkillForm skillForm) {    //it will return skill dto
        Skill skill=new Skill();
        skill.setName(skillForm.getName());
        skill=skillRepository.save(skill);
        try{
            skill=skillRepository.save(skill);
            SkillDTO skillDTO= new SkillDTO(skill.getId(), skill.getName());
            new ResponseUtil<SkillDTO>().generateServiceResponse(ServiceMessage.SKILL_CREATED_SUCCESSFULLY, true,skillDTO, HttpStatus.CREATED.value());
        }
        catch (Exception exception){
            throw new SkillException(ExceptionMessage.SKILL_ADD_EXCEPTION,HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return null;
    }

    @Override
    public ResponseDTO<SkillDTO> updateSkill(Long id, SkillForm skillForm) {
        Skill skill=skillRepository.getOne(id);
        SkillDTO skillDTO=new SkillDTO(id, skillForm.getName());
        boolean updateRequired=false;
        if(!skillForm.getName().equals(skill.getName())){
            skill.setName(skillForm.getName());
            updateRequired=true;
        }
        if(updateRequired){
            skillRepository.save(skill);
        }
        return new ResponseUtil<SkillDTO>().generateServiceResponse(ServiceMessage.SKILL_UPDATED, true, skillDTO, HttpStatus.OK.value());
    }

    @Override
    public ResponseDTO<SkillDTO> readSkill(Long id) {
        Skill skill=skillRepository.getOne(id);
        SkillDTO skillDTO=new SkillDTO(skill.getId(),skill.getName());
        return new ResponseUtil<SkillDTO>().generateServiceResponse(ServiceMessage.SKILL_FOUND, true, skillRepository.readSkill(),HttpStatus.OK.value());
    }

    @Override
    public ResponseDTO<Set<SkillDTO>> readSkills() {
        return new ResponseUtil<Set<SkillDTO>>().generateServiceResponse(ServiceMessage.SKILLS_FOUND, true, skillRepository.readSkills(), HttpStatus.OK.value());
    }

    @Override
    public ResponseDTO<Void> deleteSkill(Long id) {
        skillRepository.deleteById(id);
        return new ResponseUtil<Void>().generateServiceResponse(ServiceMessage.SKILL_DELETED, true, null, HttpStatus.OK.value());
    }
}
