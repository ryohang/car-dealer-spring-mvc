package io.ascending.training.service;

import com.tobedevoured.modelcitizen.CreateModelException;
import io.ascending.training.domain.User;
import io.ascending.training.extend.exp.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

public class UserServiceTest extends ServiceTest{
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void findByEmailTest() throws CreateModelException, NotFoundException {
        User user = modelFactory.createModel(User.class);
        user.setPassword("password123");
        user.setConfirmPassword("password123");
        userService.save(user);
        User expected = userService.findByEmailOrUsername(user.getEmail());
        assertEquals(user.getId(),expected.getId());
    }
}
