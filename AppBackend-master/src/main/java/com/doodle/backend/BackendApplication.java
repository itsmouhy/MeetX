package com.doodle.backend;

import com.doodle.backend.entities.User;
import com.doodle.backend.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {


	@Autowired
	UserServiceImp userServiceImp;
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	//@Bean
	public void Admin(){
		User admin=new User("admin","admin@gmail.com","12345678","ADMIN");
		userServiceImp.saveUser(admin);
	}








}
