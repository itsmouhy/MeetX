package com.doodle.backend.controller;


import com.doodle.backend.entities.Option;
import com.doodle.backend.entities.Sandage;
import com.doodle.backend.entities.User;
import com.doodle.backend.services.OptionServiceImp;
import com.doodle.backend.services.SandageServiceImp;
import com.doodle.backend.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class SandageController {


    @Autowired
    SandageServiceImp sandageServiceImp;
    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    OptionServiceImp optionServiceImp;


    @GetMapping("/sandages")
     List<Sandage> getAllSandage(){
        return sandageServiceImp.getSandages();
    }

    @PostMapping ("/saveSandage/{idUser}")
     Sandage saveSandage(@RequestBody Sandage sandage,@PathVariable Long idUser){
        User user=userServiceImp.getUser(idUser);
        sandage.setUser(user);
        return sandageServiceImp.saveSandage(sandage);
    }

    @GetMapping ("/sandage/{id}")
    Sandage getSandage(@PathVariable Long id){
        return sandageServiceImp.getSandage(id);
    }


    @GetMapping ("/UsersBySandage/{id}")
    List <User> getUsersBySandage(@PathVariable Long id){
        Sandage sandage=sandageServiceImp.getSandage(id);
        List <User> users=sandage.getUsers();
        return users;
    }

    @GetMapping ("/OtherParticipants/{id}/{idUser}")
    List <User> OtherParticipants(@PathVariable Long id,@PathVariable Long idUser){
        User user=userServiceImp.getUser(idUser);
        Sandage sandage=sandageServiceImp.getSandage(id);
        List <User> users=sandage.getUsers();
        List <User> newUsers=new ArrayList<>();

        for(User myuser:users){
            if(!myuser.equals(user)){
                newUsers.add(myuser);
            }
        }

        return newUsers;
    }

    @GetMapping ("/OptionsBySandage/{id}")
    List <Option> getOptionsBySandage(@PathVariable Long id){
        Sandage sandage=sandageServiceImp.getSandage(id);
        List <Option> Options=sandage.getOptions();
        return Options;
    }

    @PostMapping ("/addUserToSandage/{idSandage}/{idUser}")
    Sandage addUserToSandage(@PathVariable Long idSandage , @PathVariable Long idUser) {
        User user=userServiceImp.getUser(idUser);
        Sandage sandage=sandageServiceImp.getSandage(idSandage);
        sandage.getUsers().add(user);
        return sandageServiceImp.saveSandage(sandage);
    }


    @DeleteMapping ("/booking/{idSandage}/{idOption}")
     void booking(@PathVariable Long idSandage,@PathVariable Long idOption){
     Option option=optionServiceImp.getOption(idOption);
     Sandage sandage=sandageServiceImp.getSandage(idSandage);
     sandage.setBooking(option);

     List<Option> Options=sandage.getOptions();
     for (Option opt : Options) {
         if (!opt.equals(option)){
             optionServiceImp.deleteOptionById(opt.getIdDate());
         }
     }
     sandageServiceImp.saveSandage(sandage);
    }

    @GetMapping ("/organizator/{idSandage}")
    User getOrganisator(@PathVariable Long idSandage){
     Sandage sandage=sandageServiceImp.getSandage(idSandage);
     return sandage.getUser();
    }


    @GetMapping("/sandagesC/{idUser}")
    List<Sandage> getAllSandageByUser(@PathVariable Long idUser){
        User user=userServiceImp.getUser(idUser);
        return user.getSandages_c();
        //return sandageServiceImp.getSandages();
    }

    @GetMapping("/sandagesP/{idUser}")
    List<Sandage> getAllSandageP(@PathVariable Long idUser){
        User user=userServiceImp.getUser(idUser);
        return user.getSandages_p();
        //return sandageServiceImp.getSandages();
    }


    @GetMapping("/AllSandageByUser/{idUser}")
    List<Sandage> getAllSandage(@PathVariable Long idUser){
        User user=userServiceImp.getUser(idUser);
        List<Sandage> LS=new ArrayList<>();
        LS.addAll(user.getSandages_c());
        LS.addAll(user.getSandages_p());
        return LS;
    }


    @GetMapping("/NumberOption/{idSandage}")
    int NumberOption(@PathVariable Long idSandage){
        Sandage sandage=sandageServiceImp.getSandage(idSandage);
        return sandage.getOptions().size();
    }
    @GetMapping("/NumberParticipant/{idSandage}")
    int NumberParticipant(@PathVariable Long idSandage){
        Sandage sandage=sandageServiceImp.getSandage(idSandage);
        return sandage.getUsers().size();
    }

    @PostMapping("/UpdateSandage/{idSandage}")
    Sandage UpdateSandage(@PathVariable Long idSandage,@RequestBody Sandage sandage){
    Sandage sandage1=sandageServiceImp.getSandage(idSandage);
    sandage1.setTitre(sandage.getTitre());
    sandage1.setDescription(sandage.getDescription());
    return sandageServiceImp.saveSandage(sandage1);
    }

    @DeleteMapping("deleteSandage/{id}")
    void deleteSandage(@PathVariable Long id){
     sandageServiceImp.deleteSandageById(id);
    }

    @GetMapping ("notVoting/{idSandage}/{idUser}")
    boolean notVoting(@PathVariable Long idSandage,@PathVariable Long idUser){
        Sandage sandage=sandageServiceImp.getSandage(idSandage);
        User user=userServiceImp.getUser(idUser);

        if(sandage.getUsers().contains(user)){
            return true;
        }else{
            return false;
        }
    }
    @GetMapping ("NotBooked/{idSandage}")
    boolean NotBooked(@PathVariable Long idSandage){
        Sandage sandage=sandageServiceImp.getSandage(idSandage);

        if(sandage.getBooking()==null){
            return true;
        }else{
            return false;
        }
    }
















}
