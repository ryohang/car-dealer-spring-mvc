package io.ascending.training.repository;

import io.ascending.training.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ryo on 5/20/18.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailIgnoreCase(String email);

    User findByUsernameIgnoreCase(String username);

    @Query(value = "select * from users where username like ?1 or email like ?1 or first_name like ?1 or last_name like ?1 limit ?2", nativeQuery = true)
    List<User> findByUsernameOrEmailOrNameLike(String usernameOrEmail, Integer num);
}
