package io.ascending.training.repository;

import io.ascending.training.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ryo on 5/20/18.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
