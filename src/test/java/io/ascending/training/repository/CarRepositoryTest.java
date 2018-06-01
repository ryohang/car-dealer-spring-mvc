package io.ascending.training.repository;

import io.ascending.training.domain.Car;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CarRepositoryTest extends RepositoryTest {
    @Test
    @Transactional
    public void findByIdTest() {
        Car c = new Car();
        c.setBrand("toyota");
        c.setModel("xle");
        carRepository.save(c);
        Optional<Car> testCar = carRepository.findById(c.getId());
        assertNotNull(testCar);
        assertEquals(c.getId(),testCar.get().getId());
    }

}
