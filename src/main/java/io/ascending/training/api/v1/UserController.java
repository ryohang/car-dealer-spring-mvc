package io.ascending.training.api.v1;


import io.ascending.training.domain.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = {"/api/users","/api/user"},produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping(method = RequestMethod.GET)
    public List getUserList() {
        logger.debug("list empty users");
        return null;
    }

}
