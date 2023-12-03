package com.doodle.backend.controller;


import com.doodle.backend.DTO.OptionDTO;
import com.doodle.backend.entities.Option;
import com.doodle.backend.entities.Sandage;
import com.doodle.backend.entities.User;
import com.doodle.backend.services.OptionServiceImp;
import com.doodle.backend.services.SandageServiceImp;
import com.doodle.backend.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@CrossOrigin("http://localhost:3000")
public class OptionController {


    @Autowired
    OptionServiceImp optionServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    SandageServiceImp sandageServiceImp;


    @GetMapping("/options")
    public List<Option> getAllOptions() {
        return optionServiceImp.getOptions();
    }

    @PostMapping("/saveOption")
    Option saveOption(@RequestBody Option option) {
        return optionServiceImp.saveOption(option);
    }

    @GetMapping ("/userForOption/{idOption}")
    List<User> userForOption(@PathVariable Long idOption){
        Option option=optionServiceImp.getOption(idOption);
        return option.getUserList();
    }
    @PostMapping ("/addUserToOption/{idOption}/{idUser}")
    Option addUserToOption(@PathVariable Long idOption , @PathVariable Long idUser) {
        User user=userServiceImp.getUser(idUser);
        Option option=optionServiceImp.getOption(idOption);
        option.getUserList().add(user);
        return optionServiceImp.saveOption(option);
    }

    @GetMapping ("/OptionsFormatDate/{id}")
    List <OptionDTO> OptionsFormatDate(@PathVariable Long id){
        Sandage sandage=sandageServiceImp.getSandage(id);
        List <Option> Options=sandage.getOptions();
        List <OptionDTO> DateFormates=new ArrayList<>();

        for(Option opt:Options){
            OptionDTO optionDT=new OptionDTO();
            optionDT.setIdDate(opt.getIdDate());
            optionDT.setDateF(changeFormatFordate(opt.getDate()));
            optionDT.setStartTime(opt.getTime());
            optionDT.setEndTime(opt.getEndTime());
            optionDT.setNumUser(opt.getUserList().size());
            DateFormates.add(optionDT);
        }
        return DateFormates;
    }


    /*@GetMapping ("/OneOptionFormatDate/{idSandage}")
    OptionDTO OneOptionFormatDate(@PathVariable Long idSandage){
    Sandage sandage=sandageServiceImp.getSandage(idSandage);
    Option booking=sandage.getBooking();
    OptionDTO optionDT=new OptionDTO();
    optionDT.setDateF(changeFormatFordate(booking.getDate()));
    optionDT.setStartTime(booking.getTime());
    optionDT.setEndTime(booking.getEndTime());
    optionDT.setNumUser(booking.getUserList().size());
    return optionDT;
    }*/


    @GetMapping("/OneOptionFormatDate/{idSandage}")
    @ResponseBody
    public ResponseEntity<?> OneOptionFormatDate(@PathVariable Long idSandage) {
        Sandage sandage = sandageServiceImp.getSandage(idSandage);

        if (sandage == null) {
            // Gérer le cas où Sandage est null, peut-être renvoyer une réponse d'erreur appropriée
            return ResponseEntity.notFound().build();
        }

        Option booking = sandage.getBooking();

        if (booking == null) {
            return ResponseEntity.notFound().build();
        }

        OptionDTO optionDT = new OptionDTO();
        optionDT.setDateF(changeFormatFordate(booking.getDate()));
        optionDT.setStartTime(booking.getTime());
        optionDT.setEndTime(booking.getEndTime());
        optionDT.setNumUser(booking.getUserList().size());
        optionDT.setIdDate(booking.getIdDate());

        return ResponseEntity.ok(optionDT);
    }

    String changeFormatFordate(LocalDate date){
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d EEE",Locale.ENGLISH);
       String formattedDate = date.format(outputFormatter);
       return formattedDate;
   }


   @DeleteMapping("/deleteOptionById/{idOpt}")
   void deleteOptionById(@PathVariable Long idOpt){
        optionServiceImp.deleteOptionById(idOpt);
   }
}


