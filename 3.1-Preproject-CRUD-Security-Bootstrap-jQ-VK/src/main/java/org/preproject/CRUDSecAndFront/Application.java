package org.preproject.CRUDSecAndFront;

import org.preproject.CRUDSecAndFront.model.Role;
import org.preproject.CRUDSecAndFront.model.Status;
import org.preproject.CRUDSecAndFront.repository.RolesRepository;
import org.preproject.CRUDSecAndFront.repository.StatusRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		RolesRepository roleRepository = context.getBean(RolesRepository.class);
		StatusRepository statusRepository = context.getBean(StatusRepository.class);

		roleRepository.save(new Role("ROLE_USER"));
		roleRepository.save(new Role("ROLE_ADMIN"));

		statusRepository.save(new Status(true));
		statusRepository.save(new Status(false));

//		SpringApplication.run(Application.class, args);
	}
}