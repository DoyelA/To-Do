package service;

import dto.ResponseDTO;
import dto.TaskDTO;
import form.TaskForm;

import java.util.Set;

public interface TaskService {
    public ResponseDTO<TaskDTO> createTask(TaskForm taskForm);
    public ResponseDTO<TaskDTO> updateTask(Long id, TaskForm taskForm);
    public ResponseDTO<TaskDTO> getTask(Long id);

    ResponseDTO<TaskDTO> getTask(Long id, TaskForm taskForm);

    public ResponseDTO<Set<TaskDTO>> getAllTasks();
    public ResponseDTO<Void> deleteTask(Long id);
}
