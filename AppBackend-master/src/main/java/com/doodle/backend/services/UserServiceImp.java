package com.doodle.backend.services;

import com.doodle.backend.DTO.LoginDTO;
import com.doodle.backend.DTO.userDTO;
import com.doodle.backend.Response.LoginResponse;
import com.doodle.backend.entities.User;
import com.doodle.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;



    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAllUsersByPage(int page, int size) {
        return userRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }



    @Override
    public String addUser(userDTO userdto) {
        User user = new User(
                userdto.getIdUser(),
                userdto.getUsername(),
                userdto.getEmail(),
                userdto.getPassword(),
                "USER"
        );
       // accountServiceImp.addRoleToUser(user,"USER");
        userRepository.save(user);
        return user.getUsername();
    }

    @Override
    public LoginResponse LoginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userRepository.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            boolean isPwdRight=false;
            if(password.matches(encodedPassword)) { isPwdRight = true;}
            if (isPwdRight) {
                Optional<User> user = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginResponse("Login Success", true,user1.getIdUser(),user1.getRole(),user1.getUsername());
                } else {
                    return new LoginResponse("Login Failed", false,0L,null,null);
                }
            } else {
                return new LoginResponse("password Not Match", false,0L,null,null);
            }
        }else {
            return new LoginResponse("Email not exits", false,0L,null,null);
        }
    }




}
