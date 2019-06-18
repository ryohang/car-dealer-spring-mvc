package io.ascending.training.service;

import com.google.common.collect.Lists;
import io.ascending.training.domain.User;
import io.ascending.training.extend.exp.NotFoundException;
import io.ascending.training.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by ryo on 5/20/18.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public User createUser(User newUser) {
        String code = UUID.randomUUID().toString();
        String encodedPass = encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPass);
        newUser.setConfirmPassword(encodedPass);
        newUser.setConfirmToken(code);
        newUser.setCreatedAt(Instant.now());
        newUser.setLastResetAt(Instant.now());
        userDao.save(newUser);
        return newUser;
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    @Transactional(readOnly = true)
    public User findByEmailOrUsername(String keyword) throws NotFoundException, NullPointerException {
        if (keyword == null || "".equals(keyword.trim())) {
            throw new NullPointerException();
        }
        User user = userDao.findByEmailIgnoreCase(keyword);
        if (user == null) {
            user = userDao.findByUsernameIgnoreCase(keyword);
        }
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    @Transactional
    public User save(User u){
        return userDao.save(u);
    }

    @Transactional
    public User findById(Long Id){
        return userDao.findById(Id);
    }


    @Transactional
    public User findByEmailIgnoreCase(String username){
        return userDao.findByEmailIgnoreCase(username);
    }
}
