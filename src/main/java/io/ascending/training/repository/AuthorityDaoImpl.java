package io.ascending.training.repository;

import io.ascending.training.domain.Authority;
import io.ascending.training.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityDaoImpl implements AuthorityDao{


    @Override
    public List<Authority> findByUserId(Long id) {
        return null;
    }

    @Override
    public Authority save(Authority authority) {
        return null;
    }

    @Override
    public List<Authority> findAll() {
        return null;
    }

    @Override
    public Authority findById(Long aLong) {
        return null;
    }
}