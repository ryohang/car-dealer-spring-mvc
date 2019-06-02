package io.ascending.training.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class LamStorageServiceTest extends ServiceTest{
    @Autowired
    private LamStorageService lamStorageService;
    @Test
    public void uploadObjectTest(){
        File testFile= new File("/Users/ryo/Documents/javaworkspace/car-dealer-spring-mvc/pom.xml");
        lamStorageService.uploadObject("car-service-dev",testFile);

    }
}
