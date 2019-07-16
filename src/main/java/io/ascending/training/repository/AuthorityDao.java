package io.ascending.training.repository;

import io.ascending.training.domain.Authority;
import io.ascending.training.domain.User;

import java.util.List;

public interface AuthorityDao extends CRUDDao<Authority,Long> {
    List<Authority> findByUserId(Long id);
}

