package service;

import dto.ResponseDTO;
import dto.UserDTO;
import form.UserForm;

public interface UserService {
    public ResponseDTO<UserDTO> createUser(UserForm userForm);
    public ResponseDTO<UserDTO> updateUser(Long id,UserForm userForm);
    public ResponseDTO<UserDTO> getUser(Long id);
    public ResponseDTO<UserDTO> getAllUsers();
    public ResponseDTO<Void> deleteUser(Long id);
}
