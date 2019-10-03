package io.ascending.training.service.jms;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import io.ascending.training.service.ServiceTest;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;

public class MessageSQSServiceTest extends ServiceTest {
    @Autowired
    private MessageSQSService messageSQSService;
    @Test
    public void sendMessageTest(){
        Mockito.verify(messageSQSService,times(1)).sendMessage(anyString(),any(Integer.class));
        messageSQSService.sendMessage("helloworld",5);
    }
}
