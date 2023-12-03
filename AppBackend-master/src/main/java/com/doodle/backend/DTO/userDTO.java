package com.doodle.backend.DTO;

public class userDTO {
    private Long IdUser;

    private String username ;

    private String email;

    private String password;



    public userDTO(Long idUser, String username, String email, String password) {
        IdUser = idUser;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public userDTO() {
    }

    public void setIdUser(Long idUser) {
        IdUser = idUser;
    }

    public Long getIdUser() {
        return IdUser;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "userDTO{" +
                "IdUser=" + IdUser +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
