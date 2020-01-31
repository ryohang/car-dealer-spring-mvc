package io.ascending.training.repository;


import io.ascending.training.config.AppConfig;
import io.ascending.training.domain.Car;
import io.ascending.training.domain.Image;
import io.ascending.training.domain.User;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class CarDaoTest {
    @Autowired
    private CarDao carDao;
    @Autowired
    private ImageDao imgDao;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    @Transactional
    public void findByIdEagerTest(){
        Car expectedResult = new Car();
        expectedResult.setModel("camery");
        expectedResult.setBrand("toyota");
        expectedResult.setCreatedDate(LocalDate.now());
        carDao.save(expectedResult);
        Image img = new Image();
        img.setUrl("https://test");
        img.setTitle("testImage");
        img.setCar(expectedResult);
        imgDao.save(img);
        assertNotNull(img.getId());
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().refresh(expectedResult);
        Car actualResult = carDao.findByIdEager(expectedResult.getId());
        List images = actualResult.getImages();
        assertEquals(images.size(),1);
    }
}
