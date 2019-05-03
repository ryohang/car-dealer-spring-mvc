package io.ascending.training.service;

import com.amazonaws.services.s3.model.S3Object;
import io.ascending.training.domain.Car;
import io.ascending.training.domain.Image;
import io.ascending.training.extend.exp.ServiceException;
import io.ascending.training.repository.CarRepository;
import io.ascending.training.repository.ImageRepository;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by ryo on 5/20/18.
 */
@Service
public class ImageService extends CrudService<Image,Long> {
    @Autowired
    private StorageService storageService;
    @Autowired
    private ImageRepository imageRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Transactional
    public Image saveFakeImage(MultipartFile multipartFile) throws ServiceException {
        if (multipartFile == null || multipartFile.isEmpty()) throw new ServiceException("File must not be null!");
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String homeDir = System.getProperty("catalina.base") !=null ? System.getProperty("catalina.base") : "/tmp/";
        Image image = new Image();
        String s3Key = FilenameUtils.getBaseName(multipartFile.getOriginalFilename()) + "_"+image.getUuid()+"."+extension;
        File localFile = new File(homeDir + s3Key);
        try {
            multipartFile.transferTo(localFile);
            storageService.putObject(s3Key,localFile);
            S3Object s3Object = storageService.getObject(s3Key);
            image.setUrl(storageService.getObjectUrl(s3Object.getKey()));
            image.setExtension(extension);
            return image;
        } catch (IOException e) {
            logger.warn("can't find image file");
        }
        return null;
    }

    @Override
    protected CrudRepository<Image, Long> getCrudRepository() {
        return imageRepository;
    }
}
