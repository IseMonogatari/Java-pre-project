package org.example;

import org.example.model.Role;
import org.example.model.User;
import org.example.repository.RolesRepository;
import org.example.repository.UsersRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		UsersRepository userRepository = context.getBean(UsersRepository.class);
		RolesRepository roleRepository = context.getBean(RolesRepository.class);
		BCryptPasswordEncoder passwordEncoder = context.getBean(BCryptPasswordEncoder.class);

		userRepository.save(new User("ADMIN", "ADMIN", "ADMIN",
				passwordEncoder.encode("ADMIN"),
				Collections.singleton(new Role("ROLE_ADMIN")))
		);

		roleRepository.save(new Role("ROLE_USER"));

		//SpringApplication.run(Application.class, args);
	}
}
