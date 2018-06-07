package io.ascending.training.service;

import com.tobedevoured.modelcitizen.CreateModelException;
import io.ascending.training.domain.Car;
import io.ascending.training.domain.Image;
import io.ascending.training.repository.ImageRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CarServiceTest extends ServiceTest {
    @Autowired
    private ImageRepository imageRepository;
    @PersistenceContext()
    EntityManager em;

    @Test
//    @Transactional
    public void findByTest() throws CreateModelException {
        Car c = modelFactory.createModel(Car.class);
        carService.save(c);
        Image img1 = modelFactory.createModel(Image.class);
        img1.setCar(c);
        Image img2 = modelFactory.createModel(Image.class);
        img2.setCar(c);
        imageRepository.save(img1);
        imageRepository.save(img2);
        List<Image> images = new ArrayList();
        images.add(img1);
        images.add(img2);
        assertNotNull(carService.findById(c.getId()));
        assertNull(carService.findById(c.getId()).get().getImages());
        assertEquals(carService.findBy(c).getImages().size(),images.size());

    }
}
