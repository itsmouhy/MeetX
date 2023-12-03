package com.doodle.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("IdBooking")
    private Long IdBooking;
    private String name;
    private String place;
    private LocalDate day;
    private LocalTime startHour;
    private LocalTime endHour;
    private String description;
    private Integer placesAvailable;
    private String imageFilePath;



    @ManyToMany (fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> BUsers = new ArrayList<>();

}

