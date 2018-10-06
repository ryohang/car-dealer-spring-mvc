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
        User expectedResult = modelFactory.createModel(User.class);
        expectedResult.setEmail("test@gmail.com");
        expectedResult.setPassword("password123");
        expectedResult.setConfirmPassword("password123");
        userService.save(expectedResult);
        User actualResult = userService.findByEmailOrUsername(expectedResult.getEmail());
        assertEquals(expectedResult.getId(),actualResult.getId());
    }
}
