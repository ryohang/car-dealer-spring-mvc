package io.ascending.training.api.v1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cars",produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

    @RequestMapping(method = RequestMethod.GET)
    public Object getAllCars(){
        return null;
    }
}
