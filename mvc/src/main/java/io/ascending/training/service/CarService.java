package io.ascending.training.service;

import io.ascending.training.domain.Car;
import io.ascending.training.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by ryo on 5/20/18.
 */
@Service
public class CarService extends CrudService<Car,Long> {
    @Autowired
    private CarRepository carRepository;
    @Override
    protected CrudRepository<Car, Long> getCrudRepository() {
        return carRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Car> findBy(Car c){
        return carRepository.findByIdWithImages(c.getId());
    }

    @Transactional(readOnly = true)
    public List<Car> findAllWithImages(){
        return carRepository.findAllWithImages();
    }
}
