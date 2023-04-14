package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.CarDTO;
import org.example.entity.Car;
import org.example.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car create(CarDTO carDTO){
        return carRepository.save(Car.builder()
                .model(carDTO.getModel())
                .color(carDTO.getColor())
                .build()
        );
    }

    public List<Car> readAll() {
        return carRepository.findAll();
    }

    public List<Car> findCars(Long count) {
        return carRepository.findCars(count);
    }

    public void delete(Integer id) {
        carRepository.deleteById(id);
    }
}
