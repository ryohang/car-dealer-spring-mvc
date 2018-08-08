package io.ascending.training.repository;

import io.ascending.training.domain.Image;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ryo on 5/20/18.
 */
public interface ImageRepository extends CrudRepository<Image, Long> {
}
