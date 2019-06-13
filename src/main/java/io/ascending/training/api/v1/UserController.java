package io.ascending.training.api.v1;

import io.ascending.training.domain.User;
import io.ascending.training.repository.UserDao;
import io.ascending.training.repository.UserDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//
//
//import io.ascending.training.domain.User;
//import io.ascending.training.enumdef.UserConfirmStatus;
//import io.ascending.training.service.UserService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
@RequestMapping(value = {"/api/users","/api/user"})
@Controller
@ResponseBody
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
//
    @Autowired
    private UserDao userDao;
//
//
    // /api/users GET
    @RequestMapping(value="",method = RequestMethod.GET)
    public List getUserList() {
        logger.debug("list users");
        return userDao.findAll();
    }

    // /api/users/5/paystub/10 GET /object/object_id    /object?id=5
    @RequestMapping(value="/{user_id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable("user_id") Long Id) {
        logger.debug("find users id: "+Id);
        return userDao.findById(Id);
    }
//
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public String userLogin(@RequestParam("username") String username) {
//        logger.debug("request parameters: "+ username);
//        return username;
//    }
//
//    @RequestMapping(value = "/signup", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public User processRegistration(@RequestParam("s_username") String username, @RequestParam("s_email") String email,
//                                    @RequestParam("s_password") String password,
//                                    @RequestParam(value = "s_firstname", required = false) String firstName,
//                                    @RequestParam(value = "s_lastname", required = false)  String lastName) {
//        User newUser = new User();
//        newUser.setEmail(email);
//        newUser.setUsername(username);
//        newUser.setPassword(password);
//        newUser.setConfirmStatus(UserConfirmStatus.CREATED.ordinal());
//        if (firstName != null) {
//            newUser.setFirstName(firstName);
//        }
//        if (lastName != null) {
//            newUser.setLastName(lastName);
//        }
//        userService.createUser(newUser);
//        return newUser;
//    }
//
//
}
