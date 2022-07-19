package com.todo.demo.controller;

import java.util.Set;

import com.todo.demo.constants.url.ApiUrl;
import com.todo.demo.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.demo.dto.ResponseDTO;
import com.todo.demo.dto.SkillDTO;
import com.todo.demo.form.SkillForm;
import com.todo.demo.service.SkillService;
import com.todo.demo.validation.annotations.sequence.ValidationSequence;

@RestController
@RequestMapping(value= ApiUrl.SKILL_URL)
public class SkillController {
    @Autowired
    private SkillService skillService;
    @PostMapping(value="/add")
    public ResponseEntity<ResponseDTO<SkillDTO>> addSkill(@Validated(value=ValidationSequence.class) @RequestBody SkillForm skillForm, BindingResult bindingResult){
      if(bindingResult.hasErrors()){
          final FieldError fieldError = bindingResult.getFieldErrors().get(0);
            return new ResponseUtil<SkillDTO>().generateValidationResponse(fieldError, false, HttpStatus.BAD_REQUEST.value(), fieldError.getDefaultMessage());
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

    @PutMapping(value="/{id}")
    public ResponseEntity<ResponseDTO<SkillDTO>> updateSkill(@PathVariable(value="id") Long id,@Validated(value=ValidationSequence.class) @RequestBody SkillForm skillForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            final FieldError fieldError = bindingResult.getFieldErrors().get(0);
            return new ResponseUtil<SkillDTO>().generateValidationResponse(fieldError, false, HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseUtil<SkillDTO>().generateControllerResponse(skillService.updateSkill(id, skillForm));
    }
}
