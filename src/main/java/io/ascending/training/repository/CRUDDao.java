package io.ascending.training.repository;

import io.ascending.training.domain.Car;

import java.util.List;

public interface CRUDDao<T,ID> {
    T save(T t);

    List<T> findAll();

    T findById(ID id);
}
