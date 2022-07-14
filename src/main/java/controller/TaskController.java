package controller;

import constants.url.ApiUrl;
import dto.ResponseDTO;
import dto.TaskDTO;
import form.TaskForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.TaskService;
import utils.ResponseUtil;
import validation.annotations.sequence.ValidationSequence;

import java.util.Set;

@RestController
@RequestMapping(value= ApiUrl.TASK_URL)
public class TaskController{
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<ResponseDTO<TaskDTO>> addTask(@Validated(value= ValidationSequence.class)
                                                            @RequestBody TaskForm taskForm,
                                                        BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseUtil<TaskDTO>().generateValidationResponse(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseUtil<TaskDTO>().generateControllerResponse(taskService.createTask(taskForm));
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ResponseDTO<TaskDTO>> updateTask(@PathVariable(value="id") Long id, @Validated(value=ValidationSequence.class) @RequestBody TaskForm taskForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseUtil<TaskDTO>().generateValidationResponse(bindingResult.getAllErrors().get(0).getDefaultMessage());
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
}
