package com.example.firststep;

import com.example.firststep.Entity.Role;
import com.example.firststep.Entity.User;
import com.example.firststep.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




import java.util.ArrayList;
//@EnableScheduling
@Configuration
@SpringBootApplication


public class SpringBootSecurity {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurity.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
		 userService.saveRole(new Role(null,"ADMIN"));
		 userService.saveRole(new Role(null,"SUPER_ADMIN"));
		 userService.saveUser(new User(null, "mahdi","mahdi@gmail.com","1234",new ArrayList<>()));
		 userService.saveUser(new User(null, "ghaith","ghaith@gmail.com","1234",new ArrayList<>()));
		 userService.addRoleToUser("mahdi","SUPER_ADMIN");
		 userService.addRoleToUser("ghaith","ADMIN");
		 userService.addRoleToUser("mahdi","ADMIN");
		};
	}


}
