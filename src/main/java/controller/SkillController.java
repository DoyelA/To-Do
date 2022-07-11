package controller;

import constants.messages.ValidationMessages;
import constants.url.ApiUrl;
import domain.Skill;
import dto.ResponseDTO;
import dto.SkillDTO;
import form.SkillForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.SkillService;
import utils.ResponseUtil;
import validation.annotations.sequence.ValidationSequence;

import java.util.Set;

@RestController
@RequestMapping(value= ApiUrl.SKILL_URL)
public class SkillController {
    @Autowired
    private SkillService skillService;
    @PostMapping
    public ResponseEntity<ResponseDTO<SkillDTO>> createSkill(@Validated(value= ValidationSequence.class) @RequestBody SkillForm skillForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseUtil<SkillDTO>().generateValidationResponse(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseUtil<SkillDTO>().generateControllerResponse(skillService.createSkill(skillForm));
    }
    @GetMapping
    public ResponseEntity<ResponseDTO<Set<SkillDTO>>> getSkills(){
        return new ResponseUtil<Set<SkillDTO>>().generateControllerResponse(skillService.readSkills());
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<ResponseDTO<SkillDTO>> getSkill(@PathVariable(value="id") Long id){
        return new ResponseUtil<SkillDTO>().generateControllerResponse(skillService.readSkill(id));
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteSkill(@PathVariable(value="id") Long id){
        return new ResponseUtil<Void>().generateControllerResponse(skillService.deleteSkill(id));
    }
}
