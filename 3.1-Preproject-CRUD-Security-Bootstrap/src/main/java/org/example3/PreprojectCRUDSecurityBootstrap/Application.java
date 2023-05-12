package org.example3.PreprojectCRUDSecurityBootstrap;

import org.example3.PreprojectCRUDSecurityBootstrap.model.Role;
import org.example3.PreprojectCRUDSecurityBootstrap.repository.RolesRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		RolesRepository roleRepository = context.getBean(RolesRepository.class);

//		roleRepository.save(new Role("ROLE_USER"));
//		roleRepository.save(new Role("ROLE_ADMIN"));

		//userRepository.save(new User("ADMIN", "ADMIN", "ADMIN",
		//				passwordEncoder.encode("ADMIN"),
		//				Collections.singleton(roleService.getRoleByName("ROLE_ADMIN")))
		//		);

		//SpringApplication.run(Application.class, args);
	}
}
