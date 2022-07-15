package service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import constants.messages.ExceptionMessage;
import constants.messages.ServiceMessage;
import domain.Skill;
import domain.User;
import dto.ResponseDTO;
import dto.SkillDTO;
import dto.UserDTO;
import exception.UserException;
import form.UserForm;
import repository.SkillRepository;
import repository.UserRepository;
import service.UserService;
import utils.ResponseUtil;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public ResponseDTO<UserDTO> createUser(UserForm userForm) {
        try {
            Set<Skill> skillSet = new HashSet<>(skillRepository.findAllById(userForm.getSkillIds()));
            User user = User.builder()
                    .firstName(userForm.getFirstName())
                    .lastName(userForm.getLastName())
                    .username(userForm.getUsername())
                    .password(userForm.getPassword())
                    .skillSet(skillSet).build();
            userRepository.save(user);
            Set<SkillDTO> skillDTO = new HashSet<>();
            skillSet.forEach(skill -> {
                skillDTO.add(new SkillDTO(skill.getId(), skill.getName()));
            });
            UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getPassword(), skillDTO);
            return new ResponseUtil<UserDTO>().generateServiceResponse(ServiceMessage.USER_CREATED_SUCCESSFULLY, true, userDTO, HttpStatus.CREATED.value());
        }catch(Exception e){
            throw new UserException(ExceptionMessage.USER_ADD_EXCEPTION,HttpStatus.INTERNAL_SERVER_ERROR.value(),userForm);
        }
    }

    @Override
    public ResponseDTO<UserDTO> updateUser(Long id, UserForm userForm) {
        return null;
    }

    @Override
    public ResponseDTO<UserDTO> getUser(Long id) {
            User user=userRepository.getOne(id);
            Set<Skill> skillSet=user.getSkillSet();
            Set<SkillDTO> skillDTOS=new HashSet<>();
            skillSet.forEach(skill->{
                skillDTOS.add(new SkillDTO(skill.getId(), skill.getName()));
            });
            UserDTO userDTO=new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(),skillDTOS);
            return new ResponseUtil<UserDTO>().generateServiceResponse(ServiceMessage.USER_FOUND1, true, userDTO, HttpStatus.OK.value());
    }

    @Override
    public ResponseDTO<Set<UserDTO>> getAllUsers() {
        try{
            List<User> users=userRepository.findAll();
            Set<UserDTO> userDTOS=new HashSet<>(users.size());
            users.forEach(user->{
                Set<Skill> skills=user.getSkillSet();
                Set<SkillDTO> skillDTOS=new HashSet<>(skills.size());
                skills.forEach(skill->{
                    skillDTOS.add(new SkillDTO(skill.getId(), skill.getName()));
                });
                userDTOS.add(new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(),skillDTOS));
            });
            return new ResponseUtil<Set<UserDTO>>().generateServiceResponse(ServiceMessage.USER_FOUND, true, userDTOS, HttpStatus.OK.value());
        }catch(Exception e){
            throw new UserException(ExceptionMessage.USER_FETCH_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ResponseDTO<Void> deleteUser(Long id) {
        try{
            userRepository.deleteById(id);
            return new ResponseUtil<Void>().generateServiceResponse(ServiceMessage.USER_DELETED, true, null, HttpStatus.OK.value());
        }catch(Exception e){
            throw new UserException(ExceptionMessage.USER_DELETE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
