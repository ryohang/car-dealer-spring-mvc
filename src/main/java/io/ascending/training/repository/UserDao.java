package io.ascending.training.repository;

import io.ascending.training.domain.User;

import java.util.List;

public interface UserDao {
    User save(User user);

    List<User> findAll();

    User findByIdEager(Long id);

    User findById(Long id);
}

