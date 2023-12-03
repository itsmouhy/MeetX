package com.doodle.backend.entities;


import com.doodle.backend.entities.Sandage;
import com.doodle.backend.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdDate;

    private LocalDate date;
    private LocalTime time;

    private LocalTime endTime;



    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Sandage sandage;



    @ManyToMany (fetch = FetchType.EAGER,cascade ={CascadeType.PERSIST,CascadeType.MERGE})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name="option_user",
    joinColumns = @JoinColumn(name = "option_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userList=new ArrayList<>();




}
