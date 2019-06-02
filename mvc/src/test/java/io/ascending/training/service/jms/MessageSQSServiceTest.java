package io.ascending.training.service.jms;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import io.ascending.training.service.ServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageSQSServiceTest extends ServiceTest {
    @Autowired
    private MessageSQSService messageSQSService;
    @Test
    public void sendMessageTest(){
        messageSQSService.sendMessage("helloworld",5);
    }
}
