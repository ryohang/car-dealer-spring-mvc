package io.ascending.training.api.v1;

import io.ascending.training.domain.Car;
import io.ascending.training.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cars",produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarService carService;

    @RequestMapping(method = RequestMethod.POST)
    public Car generateCar(@RequestBody Car car) {
        return carService.save(car);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Car> getCarList() {
        Iterable<Car> iterable = carService.findAll();
        List<Car> list = new ArrayList<>();
        for (Car car : iterable) {
            list.add(car);
        }
        return list;
    }

    @RequestMapping(value="/{Id}" , method= RequestMethod.GET)
    public Car getCarById(@PathVariable("Id") Long carId) {
        return carService.findBy(new Car(carId)).get();
    }


}