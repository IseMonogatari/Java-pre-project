package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.CarDTO;
import org.example.entity.Car;
import org.example.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class CarController {

    private CarService carService;

    @PostMapping
    public ResponseEntity<Car> create(@RequestBody CarDTO carDTO) {
        return new ResponseEntity<>(carService.create(carDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Integer id) {
        carService.delete(id);
        return HttpStatus.OK;
    }

    //@GetMapping()//"/cars"
    //public ResponseEntity<List<Car>> readAll() {
    //        return new ResponseEntity<>(carService.readAll(), HttpStatus.OK);
    //    }

    //@GetMapping("/cars")
    //    public ResponseEntity<List<Car>> findCars(@RequestParam(value = "count", required = false) Long count) {
    //        return new ResponseEntity<>(carService.findCars(count), HttpStatus.OK);
    //    }

    @GetMapping("/cars")
    public String findCars(@RequestParam(value = "count", required = false) Long count,
                                 Model model) {
        model.addAttribute("cars", carService.findCars(count));
        return "class/cars";
    }
}
