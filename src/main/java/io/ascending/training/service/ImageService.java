package io.ascending.training.service;

import io.ascending.training.domain.Car;
import io.ascending.training.domain.Image;
import io.ascending.training.repository.CarRepository;
import io.ascending.training.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by ryo on 5/20/18.
 */
@Service
public class ImageService extends CrudService<Image,Long> {
    @Autowired
    private ImageRepository imageRepository;
    @Override
    protected CrudRepository<Image, Long> getCrudRepository() {
        return imageRepository;
    }
}
