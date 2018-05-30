package io.ascending.training.repository;

import io.ascending.training.domain.Car;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ryo on 5/20/18.
 */
public interface CarRepository extends CrudRepository<Car, Long> {
}
