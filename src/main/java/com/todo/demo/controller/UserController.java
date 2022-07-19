package com.todo.demo.controller;

import com.todo.demo.constants.url.ApiUrl;
import com.todo.demo.service.UserService;
import com.todo.demo.utils.ResponseUtil;
import com.todo.demo.dto.ResponseDTO;
import com.todo.demo.dto.UserDTO;
import com.todo.demo.form.UserForm;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.todo.demo.validation.annotations.sequence.ValidationSequence;

@RestController
@RequestMapping(value= ApiUrl.USER_URL)
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<ResponseDTO<UserDTO>> addUser(@Validated(value=ValidationSequence.class) @RequestBody UserForm userForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            final FieldError fieldError = bindingResult.getFieldErrors().get(0);
            return new ResponseUtil<UserDTO>().generateValidationResponse(fieldError, false, HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseUtil<UserDTO>().generateControllerResponse(userService.createUser(userForm));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO<UserDTO>> getUser(@PathVariable(value="id") Long id){
        return new ResponseUtil<UserDTO>().generateControllerResponse(userService.getUser(id));
    }
    @GetMapping
    public ResponseEntity<ResponseDTO<Set<UserDTO>>> listAllUsers(){
        return new ResponseUtil<Set<UserDTO>>().generateControllerResponse(userService.getAllUsers());
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ResponseDTO<UserDTO>> updateUser(@PathVariable(value="id") Long id, @Validated(value=ValidationSequence.class)  @RequestBody UserForm userForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            final FieldError fieldError = bindingResult.getFieldErrors().get(0);
            return new ResponseUtil<UserDTO>().generateValidationResponse(fieldError, false, HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseUtil<UserDTO>().generateControllerResponse(userService.updateUser(id, userForm));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteUserById(@PathVariable(value="id") Long id){
        return new ResponseUtil<Void>().generateControllerResponse(userService.deleteUser(id));
    }
}
