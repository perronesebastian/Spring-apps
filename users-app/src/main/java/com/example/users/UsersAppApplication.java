package com.example.users;



import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.users.entity.Role;
import com.example.users.entity.User;
import com.example.users.entity.UserInRol;
import com.example.users.repository.RoleRepository;
import com.example.users.repository.UserInRoleRepository;
import com.example.users.repository.UserRepository;
import com.github.javafaker.Faker;

@SpringBootApplication
public class UsersAppApplication implements ApplicationRunner {
	
	@Autowired
	private Faker faker;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	private static final Logger log = LoggerFactory.getLogger(UsersAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UsersAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Role roles[] = { new Role("ADMIN"), new Role("USER"), new Role("SUPPORT") };
		
		for (Role role : roles) {
			roleRepository.save(role);
		}
		
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			User created = userRepository.save(user);
			UserInRol userInRol = new UserInRol(created, roles[new Random().nextInt(3)]);
			userInRoleRepository.save(userInRol);
			log.info("User created username: {} pasword: {} role: {}", created.getUsername(), created.getPassword(), userInRol.getRole().getName());		
		}
	}

}
