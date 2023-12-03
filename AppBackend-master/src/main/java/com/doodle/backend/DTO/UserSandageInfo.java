package com.doodle.backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSandageInfo {
    private Long IdUser;
    private String username;
    private String email;
    private String password;
    private int SandageNumberC;
    private int SandageNumberP;


}
