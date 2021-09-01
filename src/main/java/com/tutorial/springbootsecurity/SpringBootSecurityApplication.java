package com.tutorial.springbootsecurity;


import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableMongoAuditing
@EnableMongoRepositories
@SpringBootApplication
public class SpringBootSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

	@Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
    }
/*
	@Bean
	CommandLineRunner run(UserService userService){
		return args ->{
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			userService.saveUser(new User(null, "John Travolta", "john", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Will Smith", "will", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Jim Carry", "jim", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Arnold Schwarzneger", "arnold", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Jack Dee", "jack", "1234", new ArrayList<>()));
			userService.addToRoleToUser("jack", null);
			userService.addToRoleToUser("john", "ROLE_USER");
			userService.addToRoleToUser("john", "ROLE_MANAGER");
			userService.addToRoleToUser("will", "ROLE_MANAGER");
			userService.addToRoleToUser("jim", "ROLE_ADMIN");
			userService.addToRoleToUser("arnold", "ROLE_SUPER_ADMIN");
			userService.addToRoleToUser("arnold", "ROLE_ADMIN");
			userService.addToRoleToUser("arnold", "ROLE_USER");
		};
	}
		*/
}