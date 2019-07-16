package io.ascending.training.api.v1;

import io.ascending.training.domain.Car;
import io.ascending.training.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        logger.info("list all cars");
        List<Car> cars = carService.findAll();
        return cars;
    }

//    @RequestMapping(value="/{Id}" , method= RequestMethod.GET)
//    public Car getCarById(@PathVariable("Id") Long carId) {
//        return carDao.findById(carId);
//    }


//    @RequestMapping(value="/{Id}" , method= RequestMethod.GET,params = {"carName"})
//    public Car getCarById(@PathVariable("Id") Long carId,@RequestParam(value = "carName") String carName,@RequestHeader("Accept-Encoding") String encoding) {
//        logger.debug("parameter name is: "+ carName);
//        logger.debug("encoding header is: "+ encoding);
//        return carService.findBy(new Car(carId)).get();
//    }


}
