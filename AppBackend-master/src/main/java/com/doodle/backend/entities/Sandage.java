package com.doodle.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Sandage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdSandage;
    private String titre;
    private String Description;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @ManyToMany (fetch = FetchType.EAGER,cascade ={CascadeType.PERSIST,CascadeType.MERGE})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    @JoinTable(name="sandage_user",
            joinColumns = @JoinColumn(name = "sandage_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users=new ArrayList<>();


    @OneToMany (mappedBy = "sandage",fetch = FetchType.LAZY,cascade =CascadeType.ALL,orphanRemoval = true)
    private List<Option> options=new ArrayList<>();

    @OneToOne
    private Option booking;


}