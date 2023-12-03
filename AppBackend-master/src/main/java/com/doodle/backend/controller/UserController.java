package com.doodle.backend.controller;

import com.doodle.backend.DTO.LoginDTO;
import com.doodle.backend.DTO.UserSandageInfo;
import com.doodle.backend.DTO.userDTO;
import com.doodle.backend.Response.LoginResponse;
import com.doodle.backend.entities.User;
import com.doodle.backend.repositories.UserRepository;
import com.doodle.backend.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    UserRepository userRepository;



    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id){return userServiceImp.getUser(id);}

    @PostMapping("/saveUser")
    public String addUser(@RequestBody userDTO userdto)
    {
        return userServiceImp.addUser(userdto);

    }

    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = userServiceImp.LoginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

/*
    @GetMapping("FindUser")
    public User findOneByEmailAndPassword(@RequestBody LoginDTO loginDTO){
        return userServiceImp.FindByEmailAndPassword(loginDTO);
    }
    @GetMap
*/
    @PostMapping("/FindByEmail")
    public User FindEmail(@RequestBody LoginDTO loginDTO){
        return userRepository.findByEmail(loginDTO.getEmail());
    }


    @PostMapping("updateUser/{id}/{username}/{email}/{password}")
    User UpdateUser(@PathVariable Long id,@PathVariable String username,@PathVariable String email,@PathVariable String password){
        User user=userServiceImp.getUser(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userServiceImp.saveUser(user);
        return user;
    }

    @GetMapping("/usersInfo")
    public List<UserSandageInfo> getUsers(){

      List<UserSandageInfo> userSandageInfoList=new ArrayList<>();

       for(User user: userServiceImp.getUsers()){

           if(user.getRole().equals("USER")) {
               userSandageInfoList.add(new UserSandageInfo(user.getIdUser(), user.getUsername(), user.getEmail(),
                       user.getPassword(), user.getSandages_c().size(), user.getSandages_p().size()));
           }

       }

       return userSandageInfoList;
    }

    @DeleteMapping("/deleteUser/{idUser}")
    void deleteOptionById(@PathVariable Long idUser){
        userServiceImp.deleteUserById(idUser);
    }









}
