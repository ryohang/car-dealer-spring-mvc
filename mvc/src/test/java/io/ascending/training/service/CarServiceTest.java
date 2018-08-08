package io.ascending.training.service;

import com.google.common.collect.Lists;
import com.tobedevoured.modelcitizen.CreateModelException;
import io.ascending.training.domain.Car;
import io.ascending.training.domain.Image;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CarServiceTest extends ServiceTest {
    @PersistenceContext()
    EntityManager em;

    @Test
    @Transactional
    public void findByTest() throws CreateModelException {
        Car c = modelFactory.createModel(Car.class);
        carService.save(c);
        Image img1 = modelFactory.createModel(Image.class);
        img1.setCar(c);
        Image img2 = modelFactory.createModel(Image.class);
        img2.setCar(c);
        imageService.save(img1);
        imageService.save(img2);
        List<Image> images = new ArrayList();
        images.add(img1);
        images.add(img2);
        em.flush();
        assertNotNull(carService.findById(c.getId()));
        assertEquals( images.size(),Lists.newArrayList(imageService.findAll()).size());
        assertEquals(0,c.getImages().size());
        Car eager = carService.findBy(c).get();
        em.refresh(eager);
        assertEquals( images.size(),eager.getImages().size());
    }
}
