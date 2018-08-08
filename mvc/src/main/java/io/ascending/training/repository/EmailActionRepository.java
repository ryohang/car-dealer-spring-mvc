package io.ascending.training.repository;

import io.ascending.training.domain.EmailAction;
import org.springframework.data.repository.CrudRepository;

public interface EmailActionRepository extends CrudRepository<EmailAction, Long> {
}
