package org.example.service;

import org.example.dto.CarDTO;
import org.example.entity.Car;
import org.example.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @Value("${maxCar}")
    private Integer maxCar;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

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
        List<Car> carList;
        if (count == null || count >= maxCar) {
            carList = readAll();
        } else {
            carList = carRepository.findCars(count);
        }
        return carList;
    }

    public void delete(Integer id) {
        carRepository.deleteById(id);
    }
}
