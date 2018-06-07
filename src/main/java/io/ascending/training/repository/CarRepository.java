package io.ascending.training.repository;

import io.ascending.training.domain.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ryo on 5/20/18.
 */
public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findAll();

    @Query("Select c FROM Car c LEFT JOIN FETCH c.images where c.id =?1")
    Car findByIdWithImages(Long Id);

    @Query("Select c FROM Car c LEFT JOIN FETCH c.images")
    List<Car> findAllCarsEager();
}
