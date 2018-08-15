package io.ascending.training.config;

import io.ascending.training.service.StorageService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MockConfig {
    @Bean
    @Primary
    public StorageService getStorageService() {
        return Mockito.mock(StorageService.class);
    }

    @Profile({"unit"})
    @Bean(name="mailSender")
    public JavaMailSenderImpl getEmailSender(){
        JavaMailSenderImpl emailSender = Mockito.mock(JavaMailSenderImpl.class);
        return emailSender;
    }
}
