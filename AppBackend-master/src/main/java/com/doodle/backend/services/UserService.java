package com.doodle.backend.services;

import com.doodle.backend.DTO.LoginDTO;
import com.doodle.backend.DTO.userDTO;
import com.doodle.backend.Response.LoginResponse;
import com.doodle.backend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    User saveUser(User user);
    User updateUser(User user);
    User getUser(Long id);
    List<User> getUsers();
    Page<User> getAllUsersByPage(int page, int size);
    void deleteUserById(Long id);
    void deleteAllUsers();

    String addUser(userDTO userdto);

    LoginResponse LoginUser(LoginDTO loginDTO);

    //User FindByEmailAndPassword(LoginDTO loginDTO);


}
