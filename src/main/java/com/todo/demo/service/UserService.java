package com.todo.demo.service;

import com.todo.demo.dto.ResponseDTO;
import com.todo.demo.dto.UserDTO;
import com.todo.demo.form.UserForm;

import java.util.Set;

public interface UserService {
    ResponseDTO<UserDTO> createUser(UserForm userForm);
    ResponseDTO<UserDTO> updateUser(Long id,UserForm userForm);
    ResponseDTO<UserDTO> getUser(Long id);
    ResponseDTO<Set<UserDTO>> getAllUsers();
    ResponseDTO<Void> deleteUser(Long id);
}
