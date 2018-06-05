package io.ascending.training.service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import io.ascending.training.domain.Car;
import io.ascending.training.domain.User;
import io.ascending.training.repository.CarRepository;
import io.ascending.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ryo on 5/20/18.
 */
@Service
public class UserService extends CrudService<User,Long> {
    @Autowired
    private UserRepository userRepository;
    @Override
    protected CrudRepository<User, Long> getCrudRepository() {
        return userRepository;
    }

    public List<User> findAll(){
        List<User> users = Lists.newArrayList(userRepository.findAll());
        return users;
    }
}
