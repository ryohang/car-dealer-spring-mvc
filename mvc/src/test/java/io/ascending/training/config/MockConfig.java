package io.ascending.training.config;

import io.ascending.training.service.StorageService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MockConfig {
    @Bean
    @Primary
    public StorageService getStorageService() {
        return Mockito.mock(StorageService.class);
    }
}
