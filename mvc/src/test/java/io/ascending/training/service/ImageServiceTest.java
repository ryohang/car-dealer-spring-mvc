package io.ascending.training.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import io.ascending.training.config.AppConfig;
import io.ascending.training.config.MockConfig;
import io.ascending.training.domain.Image;
import io.ascending.training.extend.exp.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class,MockConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class ImageServiceTest {
    @Autowired
    private ImageService imageService;

    @Autowired
    private StorageService storageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        validateMockitoUsage();
    }


    @Test
    public void saveFakeImageTest() throws IOException, ServiceException {
        MultipartFile multipartFile=Mockito.mock(MultipartFile.class);
        S3Object s3Object=Mockito.mock(S3Object.class);
        when(multipartFile.getOriginalFilename()).thenReturn("mutiple-file.png");
        File localFile = new File("test.png");
        when(storageService.getObject(any())).thenReturn(s3Object);
        String testUrl = "testurl";
        when(storageService.getObjectUrl(any())).thenReturn(testUrl);
        Image image = imageService.saveFakeImage(multipartFile);
        assertNotNull(image.getUrl());
        assertEquals(image.getUrl(),testUrl);
//        when(multipartFile.transferTo(localFile)).then();
    }
}
