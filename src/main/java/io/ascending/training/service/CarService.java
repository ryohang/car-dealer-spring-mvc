package io.ascending.training.service;

import io.ascending.training.domain.Car;
import io.ascending.training.repository.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarDao carDao;

    @Transactional
    public Car save(Car c){
        return carDao.save(c);
    }

    @Transactional
    public List<Car> findAll(){
        return carDao.findAll();
    }

    public List<Car> getBestValue(){
        List<Car> cars = carDao.findAll();
        //todo getbestvalue filter
//        cars;
        return cars;
    }
}
