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

@RequestMapping(value = {"/api/users","/api/user"})
@Controller
@ResponseBody
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

    // /api/users GET
    @RequestMapping(value="",method = RequestMethod.GET)
    public List getUserList() {
        logger.debug("list users");
        return userService.findAll();
    }

    // /api/login POST
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody RestAuthenticationRequest authenticationRequest) {
        try {
            Authentication notFullyAuthenticated = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );
            final Authentication authentication = authenticationManager.authenticate(notFullyAuthenticated);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            try {
                final UserDetails userDetails = userService.findByEmailOrUsername(authenticationRequest.getUsername());
                final String token = jwtTokenUtil.generateToken(userDetails);
                return ResponseEntity.ok(new JwtAuthenticationResponse(token));
            } catch (NotFoundException e) {
                logger.error("System can't find user by email or username", e);
                return ResponseEntity.notFound().build();
            }
        } catch (AuthenticationException ex) {
//            return new ResponseEntity<>("authentication failure, please check your username and password",HttpStatus.UNAUTHORIZED);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("authentication failure, please check your username and password");
        }
    }

    // /api/users/5 GET /object/object_id    /object?id=5
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") Long Id) {
        logger.debug("find users id: "+Id);
        return userService.findById(Id);
    }

    // api/user?username=xxxx GET
    @RequestMapping(value="",method = RequestMethod.GET,params = "username")
    public User getUserByUsername(@RequestParam("username") String username) {
        logger.debug("find users by username: "+username);
        return userService.findByEmailIgnoreCase(username);
    }

    //POST /api/user
    @RequestMapping(value = "",method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        userService.createUser(user);
        return user;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User processRegistration(@RequestBody User u) {
        logger.debug("create user for username: "+  u.getUsername());
        userService.createUser(u);
        return u;
    }

}
