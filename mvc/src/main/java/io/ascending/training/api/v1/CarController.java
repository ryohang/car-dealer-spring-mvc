package io.ascending.training.api.v1;

import com.fasterxml.jackson.annotation.JsonView;
import io.ascending.training.domain.Car;
import io.ascending.training.domain.JsView;
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
public class CarController extends BaseController{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarService carService;

    @RequestMapping(method = RequestMethod.POST)
    public Car generateCar(@RequestBody Car car) {
        return carService.save(car);
    }

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(Car.WithoutImagesView.class)
    public List<Car> getCarList() {
//        setJsonViewClass(JsView.User.class);
//        disableMapperFeature_DEFAULT_VIEW_INCLUSION();
        Iterable<Car> iterable = carService.findAll();
        List<Car> list = new ArrayList<>();
        for (Car car : iterable) {
            list.add(car);
        }
//        List<Car> list = carService.findAllWithImages();
        return list;
    }

    @RequestMapping(value="/{Id}" , method= RequestMethod.GET)
    @JsonView(Car.WithImageView.class)
    public Car getCarById(@PathVariable("Id") Long carId) {
        logger.debug("id is: "+carId);
        return carService.findBy(new Car(carId)).get();
    }


    @RequestMapping(value="/{Id}" , method= RequestMethod.GET,params = {"carName"})
    public Car getCarById(@PathVariable("Id") Long carId,@RequestParam(value = "carName") String carName,@RequestHeader("Accept-Encoding") String encoding) {
        logger.debug("parameter name is: "+ carName);
        logger.debug("encoding header is: "+ encoding);
        return carService.findBy(new Car(carId)).get();
    }


}
