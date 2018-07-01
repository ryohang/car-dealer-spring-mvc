package io.ascending.training.repository;

import io.ascending.training.domain.Authority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ryo on 5/20/18.
 */
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    @Query("select a from Authority a LEFT JOIN a.user Users where user_id = ?1")
    List<Authority> findAuthoritiesByUserId (Long userId);
}
