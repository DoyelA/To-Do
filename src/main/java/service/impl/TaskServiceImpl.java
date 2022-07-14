package service.impl;

import constants.messages.ExceptionMessage;
import constants.messages.ServiceMessage;
import domain.Skill;
import domain.Tasks;
import dto.ResponseDTO;
import dto.SkillDTO;
import dto.TaskDTO;
import exception.TaskException;
import form.TaskForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import repository.SkillRepository;
import repository.TaskRepository;
import service.TaskService;
import utils.ResponseUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public ResponseDTO<TaskDTO> createTask(TaskForm taskForm) {
        try{
            Tasks task=new Tasks();
            Set<Skill> taskSkills=new HashSet<>(skillRepository.findAllById(taskForm.getSkillIds()));
            Set<SkillDTO> skillDTOS=new HashSet<>(taskSkills.size());
            taskSkills.forEach(skill->{
                skillDTOS.add(new SkillDTO(skill.getId(), skill.getName()));
            });
            task.setDescription(taskForm.getDescription());
            task.setTaskSkills(taskSkills);
            TaskDTO taskDTO=new TaskDTO(task.getId(), task.getDescription(),skillDTOS);
            return new ResponseUtil<TaskDTO>().generateServiceResponse(ServiceMessage.TASK_CREATED, true, taskDTO, HttpStatus.CREATED.value());
        }
        catch(Exception e){
            throw new TaskException(ExceptionMessage.TASK_ADD_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR.value(), taskForm);
        }
    }

    @Override
    public ResponseDTO<TaskDTO> updateTask(Long id, TaskForm taskForm) {
        Tasks task=taskRepository.getOne(id);
        boolean isUpdateRequired=false;
        if(!taskForm.getDescription().equals(task.getDescription())){
            task.setDescription(taskForm.getDescription());
            isUpdateRequired=true;
        }
        Set<Skill> taskSkills=task.getTaskSkills();
        Set<Long> setOfSkillIds=taskSkills.parallelStream().map(Skill::getId).collect(Collectors.toSet());
        for(Long skillId: taskForm.getSkillIds()){
            if(!setOfSkillIds.contains(skillId)){
                taskSkills.add(skillRepository.getOne(skillId));
                isUpdateRequired=true;
            }
        }
        if(isUpdateRequired){
            try{
                task.setTaskSkills(taskSkills);
                task=taskRepository.save(task);
            }
            catch(Exception e){
                throw new TaskException(ExceptionMessage.TASK_UPDATE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR.value(), taskForm, new Object[]{id});
            }
        }
        Set<SkillDTO> skillDTOS= new HashSet<>(taskSkills.size());
        taskSkills.forEach(skill->{
            skillDTOS.add(new SkillDTO(skill.getId(), skill.getName()));
        });
        TaskDTO taskDTO=new TaskDTO(task.getId(), task.getDescription(), skillDTOS);
        return new ResponseUtil<TaskDTO>().generateServiceResponse(ServiceMessage.TASK_UPDATED, true, taskDTO, HttpStatus.OK.value());
    }

    @Override
    public ResponseDTO<TaskDTO> getTask(Long id) {
        return null;
    }

    @Override
    public ResponseDTO<TaskDTO> getTask(Long id, TaskForm taskForm) {
        Tasks task=taskRepository.getOne(id);
        Set<Skill> taskSkills=task.getTaskSkills();
        Set<SkillDTO> skillDTOS=new HashSet<>(taskSkills.size());
        taskSkills.forEach(skill-> {
            skillDTOS.add(new SkillDTO(skill.getId(), skill.getName()));
        });
        TaskDTO taskDTO= new TaskDTO(task.getId(), task.getDescription(), skillDTOS);
        return new ResponseUtil<TaskDTO>().generateServiceResponse(ServiceMessage.TASK_FOUND ,true, taskDTO, HttpStatus.OK.value());
    }

    @Override
    public ResponseDTO<Set<TaskDTO>> getAllTasks() {
        try{
            List<Tasks> tasks=taskRepository.findAll();
            Set<TaskDTO> taskDTOS=new HashSet<>();
            tasks.forEach(task->{
                Set<Skill> taskSkills=task.getTaskSkills();
                Set<SkillDTO> skillDTOS=new HashSet<>(taskSkills.size());
                taskSkills.forEach(skill->{
                    skillDTOS.add(new SkillDTO(skill.getId(), skill.getName()));
                });
                taskDTOS.add(new TaskDTO(task.getId(), task.getDescription(), skillDTOS));
            });
            return new ResponseUtil<Set<TaskDTO>>().generateServiceResponse(ServiceMessage.TASKS_FETCHED, true, taskDTOS, HttpStatus.OK.value());
        }
        catch(Exception e){
            throw new TaskException(ExceptionMessage.TASK_FETCH_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ResponseDTO<Void> deleteTask(Long id) {
        try{
            taskRepository.deleteById(id);
        }
        catch(Exception e){
            throw new TaskException(ExceptionMessage.TASK_DELETE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return new ResponseUtil<Void>().generateServiceResponse(ServiceMessage.TASK_DELETED, true, null, HttpStatus.OK.value());
    }
}

