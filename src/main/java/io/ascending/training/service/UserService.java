package io.ascending.training.service;

import com.google.common.collect.Lists;
import io.ascending.training.domain.User;
import io.ascending.training.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ryo on 5/20/18.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
