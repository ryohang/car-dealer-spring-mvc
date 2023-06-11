package io.ascending.training.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import io.ascending.training.service.StorageService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static org.powermock.api.mockito.PowerMockito.when;

@Configuration
public class MockConfig {
    @Value("${jms.queue.name}")
    private String sqsName;

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

    @Bean
    @Profile({"unit"})
    public AmazonSQS getAmazonSQS() {
        AmazonSQS client = Mockito.mock(AmazonSQS.class);
        when(client.getQueueUrl(sqsName)).thenReturn(Mockito.mock(GetQueueUrlResult.class));
        return client;
    }
}
