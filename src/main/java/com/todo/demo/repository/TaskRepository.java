package com.todo.demo.repository;

import com.todo.demo.domain.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

}
