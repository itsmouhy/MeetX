package com.doodle.backend.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String Message ;
    private boolean Status;
    private Long IdUser;

    private String role;


    private String username;



}
