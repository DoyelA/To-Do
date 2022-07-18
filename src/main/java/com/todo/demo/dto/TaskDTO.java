package com.todo.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String name;
    private String description;

    public TaskDTO(Long id, String description, Set<SkillDTO> skillDTOS) {
    }
}
