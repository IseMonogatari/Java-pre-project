package org.example;

import org.example.entity.Car;
import org.example.repository.CarRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(value = "org.example.repository")
public class Main {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        CarRepository repository = context.getBean(CarRepository.class);

        repository.save(new Car(1, "Audi", "black"));
        repository.save(new Car(2, "Audi", "red"));
        repository.save(new Car(3, "Toyota", "black"));
        repository.save(new Car(4, "Toyota", "white"));
        repository.save(new Car(5, "Uaz", "black"));
        repository.save(new Car(6, "Uaz", "brown"));
        repository.save(new Car(7, "Hummer", "red"));
        repository.save(new Car(8, "Hummer", "white"));
        repository.save(new Car(9, "Nisan", "black"));
        repository.save(new Car(10, "Nisan", "white"));
    }
}