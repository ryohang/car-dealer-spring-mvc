package io.ascending.training.repository;


import io.ascending.training.config.AppConfig;
import io.ascending.training.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void findByIdTest(){
        User expectedResult = new User();
        expectedResult.setUsername("zhang34");
        expectedResult.setEmail("test4@gmail.com");
        expectedResult.setPassword("password1234");
        expectedResult.setConfirmPassword("password1234");
        userDao.save(expectedResult);
        User actualResult = userDao.findById(expectedResult.getId());
        assertNotNull(actualResult);
//        assertTrue(false);
    }
}
