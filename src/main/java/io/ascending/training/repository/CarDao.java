package io.ascending.training.repository;

import io.ascending.training.domain.Car;
import io.ascending.training.domain.User;

import java.util.List;

public interface CarDao extends CRUDDao<Car,Long>{
    Car findByIdEager(Long id);
}

