package com.doodle.backend.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.doodle.backend.entities.Sandage;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdUser;
    private String username;
    private String email;
    private String password;

    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Sandage> sandages_c;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Sandage> sandages_p = new ArrayList<>();


    @ManyToMany (mappedBy = "userList",fetch = FetchType.EAGER)
   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Option> options=new ArrayList<>();

    @ManyToMany(mappedBy = "BUsers", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    public User(Long idUser, String username, String email, String password,String role) {
        IdUser = idUser;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role=role;
    }
    public User( String username, String email, String password,String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role=role;
    }




}

