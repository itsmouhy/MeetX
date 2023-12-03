package com.doodle.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionDTO {

    Long IdDate;
    String DateF;
    LocalTime StartTime;
    LocalTime EndTime;
    int NumUser;
}
