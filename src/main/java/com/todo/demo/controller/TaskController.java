package com.todo.demo.controller;

import com.todo.demo.constants.url.ApiUrl;
import com.todo.demo.dto.TaskDTO;
import com.todo.demo.utils.ResponseUtil;
import com.todo.demo.validation.annotations.sequence.ValidationSequence;
import com.todo.demo.dto.ResponseDTO;
import com.todo.demo.form.TaskForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.todo.demo.service.TaskService;

import java.util.Set;

@RestController
@RequestMapping(value= ApiUrl.TASK_URL)
public class TaskController{
    @Autowired
    private TaskService taskService;

    @PostMapping(value="/add")
    public ResponseEntity<ResponseDTO<TaskDTO>> addTask(@Validated(value= ValidationSequence.class)
                                                            @RequestBody TaskForm taskForm,
                                                        BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            final FieldError fieldError = bindingResult.getFieldErrors().get(0);
            return new ResponseUtil<TaskDTO>().generateValidationResponse(fieldError, false, HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseUtil<TaskDTO>().generateControllerResponse(taskService.createTask(taskForm));
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ResponseDTO<TaskDTO>> updateTask(@PathVariable(value="id") Long id, @Validated(value=ValidationSequence.class) @RequestBody TaskForm taskForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            final FieldError fieldError = bindingResult.getFieldErrors().get(0);
            return new ResponseUtil<TaskDTO>().generateValidationResponse(fieldError, false, HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseUtil<TaskDTO>().generateControllerResponse(taskService.updateTask(id, taskForm));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ResponseDTO<TaskDTO>> getTaskById(@PathVariable(value="id") Long id){
        return new ResponseUtil<TaskDTO>().generateControllerResponse(taskService.getTask(id));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<Set<TaskDTO>>> getAllTasks(){
        return new ResponseUtil<Set<TaskDTO>>().generateControllerResponse(taskService.getAllTasks());
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteTask(@PathVariable(value="id") Long id){
        return new ResponseUtil<Void>().generateControllerResponse(taskService.deleteTask(id));
    }
}
