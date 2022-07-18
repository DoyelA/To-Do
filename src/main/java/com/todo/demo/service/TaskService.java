package com.todo.demo.service;

import com.todo.demo.dto.ResponseDTO;
import com.todo.demo.dto.TaskDTO;
import com.todo.demo.form.TaskForm;

import java.util.Set;

public interface TaskService {
    ResponseDTO<TaskDTO> createTask(TaskForm taskForm);
    ResponseDTO<TaskDTO> updateTask(Long id, TaskForm taskForm);
    ResponseDTO<TaskDTO> getTask(Long id);

    ResponseDTO<TaskDTO> getTask(Long id, TaskForm taskForm);

    ResponseDTO<Set<TaskDTO>> getAllTasks();
    ResponseDTO<Void> deleteTask(Long id);
}
