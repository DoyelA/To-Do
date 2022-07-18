package com.todo.demo.repository;

import com.todo.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByUsernameAndIdIsNot(String username, Long valueOf);
}
