package io.ascending.training.api.v1;


import io.ascending.training.domain.User;
import io.ascending.training.enumdef.UserConfirmStatus;
import io.ascending.training.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/users","/api/user"},produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    public List getUserList() {
        logger.debug("list users");
        return userService.findAll();
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String userLogin(@RequestParam("username") String username) {
        logger.debug("request parameters: "+ username);
        return username;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User processRegistration(@RequestParam("s_username") String username, @RequestParam("s_email") String email,
                                    @RequestParam("s_password") String password,
                                    @RequestParam(value = "s_firstname", required = false) String firstName,
                                    @RequestParam(value = "s_lastname", required = false)  String lastName) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setConfirmStatus(UserConfirmStatus.CREATED.ordinal());
        if (firstName != null) {
            newUser.setFirstName(firstName);
        }
        if (lastName != null) {
            newUser.setLastName(lastName);
        }
        userService.createUser(newUser);
        return newUser;
    }


}
