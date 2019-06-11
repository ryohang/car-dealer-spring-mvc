package io.ascending.training.repository;

import io.ascending.training.domain.User;

import java.util.List;

public interface UserDao extends CRUDDao<User,Long> {
    User findByIdEager(Long id);
}

