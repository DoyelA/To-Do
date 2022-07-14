package controller;

import constants.url.ApiUrl;
import dto.ResponseDTO;
import dto.UserDTO;
import form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import utils.ResponseUtil;
import validation.annotations.sequence.ValidationSequence;

@RestController
@RequestMapping(value= ApiUrl.USER_URL)
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<ResponseDTO<UserDTO>> addUser(@Validated(value= ValidationSequence.class) @RequestBody UserForm userForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseUtil<UserDTO>().generateValidationResponse(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseUtil<UserDTO>().generateControllerResponse(userService.createUser(userForm));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<UserDTO>> getUser(@PathVariable(value="id") Long id){
        return new ResponseUtil<UserDTO>().generateControllerResponse(userService.getUser(id));
    }
    @GetMapping
    public ResponseEntity<ResponseDTO<UserDTO>> listAllUsers(){
        return new ResponseUtil<UserDTO>().generateControllerResponse(userService.getAllUsers());
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ResponseDTO<UserDTO>> updateUser(@PathVariable(value="id") Long id, @Validated(value=ValidationSequence.class)  @RequestBody UserForm userForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseUtil<UserDTO>().generateValidationResponse(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseUtil<UserDTO>().generateControllerResponse(userService.updateUser(id, userForm));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteUserById(@PathVariable(value="id") Long id){
        return new ResponseUtil<Void>().generateControllerResponse(userService.deleteUser(id));
    }
}