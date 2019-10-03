package io.ascending.training.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import io.ascending.training.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.parameters.P;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = "io.ascending.training",
        excludeFilters = @ComponentScan.Filter(type=FilterType.REGEX,pattern="io.ascending.training.api.*"))
public class AppConfig {
    @Autowired
    private Environment environment;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String propertyKey= "amazon.s3.bucket";

    @Bean(name = "applicationProperties")
    public PropertiesFactoryBean getDbProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        String profile = environment.getActiveProfiles()[0];
        logger.debug("applicationProperties is "+profile);
        bean.setLocation(new ClassPathResource("META-INF/env/application-"+profile+".properties"));
        return bean;
    }

    @Bean(name = "shareProperties")
    public PropertiesFactoryBean getShareProperties() {
        logger.debug("I am in the share properties");
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("META-INF/share-runtime.properties"));
        return bean;
    }

    @Bean
    public StorageService getStorageService(@Autowired @Qualifier("applicationProperties") PropertiesFactoryBean beanFactory) throws IOException {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        StorageService storageService = new StorageService(s3Client);
        storageService.setBucket(beanFactory.getObject().getProperty(propertyKey));
        return storageService;
    }

    @Bean
    @Profile({"dev","staging","prod"})
    public AmazonSQS getAmazonSQS() {
        AmazonSQS client = AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        return client;
    }
}
