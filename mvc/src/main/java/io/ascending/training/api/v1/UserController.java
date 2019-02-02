package io.ascending.training.api.v1;


import io.ascending.training.domain.User;
import io.ascending.training.enumdef.UserConfirmStatus;
import io.ascending.training.extend.exp.NotFoundException;
import io.ascending.training.extend.security.JwtAuthenticationResponse;
import io.ascending.training.extend.security.JwtTokenUtil;
import io.ascending.training.extend.security.RestAuthenticationRequest;
import io.ascending.training.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = {"/api/users","/api/user"},produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("#{shareProperties['jwt.header']}")
    private String tokenHeader;

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier(BeanIds.AUTHENTICATION_MANAGER)
    private AuthenticationManager authenticationManager;



    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUserList() {
        logger.debug("list users");
        return userService.findAll();
    }

//    @RequestMapping(method = RequestMethod.PUT)
//    public User updateUser(User user) {
////        TODO add user url
////        logger.debug("list users");
////        return userService.findAll();
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody RestAuthenticationRequest authenticationRequest, Device device) {
        try {
            Authentication notFullyAuthenticated = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );
            final Authentication authentication = authenticationManager.authenticate(notFullyAuthenticated);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            try {
                final UserDetails userDetails = userService.findByEmailOrUsername(authenticationRequest.getUsername());
//                userService.timeStampLogin((User) userDetails);
                final String token = jwtTokenUtil.generateToken(userDetails, device);
//                ResponseEntity rt = new  ResponseEntity(new JwtAuthenticationResponse(token),HttpStatus.OK);
                return ResponseEntity.ok(new JwtAuthenticationResponse(token));
            } catch (NotFoundException e) {
                logger.error("System can't find user by email or username",e);
                return ResponseEntity.notFound().build();
            }
        }catch (AuthenticationException ex){
//            return new ResponseEntity<>("authentication failure, please check your username and password",HttpStatus.UNAUTHORIZED);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("authentication failure, please check your username and password");
        }
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
