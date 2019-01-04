package com.ascending.training.worker.config;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.Session;

@Configuration
@EnableJms
public class JmsConfig {
    @Value("${aws.region}")
    private String region;

    @Bean(name="connectionFactory")
    public SQSConnectionFactory getSQSConnectionFactory(){
        AmazonSQS amazonSQSClient = AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).withRegion(region).build();
        SQSConnectionFactory factory = new SQSConnectionFactory( new ProviderConfiguration(),amazonSQSClient);
        return factory;
    }

    @Bean
    public JmsTemplate getJmsTemplate(@Autowired SQSConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        return jmsTemplate;
    }

    @Bean
    public DynamicDestinationResolver getTopicDynamicDestinationResolver(){
        return new DynamicDestinationResolver();
    }

    @Bean(name="jmsListenerContainerFactory")
    @DependsOn("connectionFactory")
    public DefaultJmsListenerContainerFactory getDefaultJmsListenerContainerFactory(@Autowired SQSConnectionFactory connectionFactory,@Autowired DynamicDestinationResolver dynamicDestinationResolver){
            DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
            jmsListenerContainerFactory.setSessionTransacted(false);
            jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
            jmsListenerContainerFactory.setDestinationResolver(dynamicDestinationResolver);
            jmsListenerContainerFactory.setConcurrency("1");
            jmsListenerContainerFactory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
            return jmsListenerContainerFactory;
    }

//    @Bean
//    public AmazonSQS getAmazonSQS() {
//        AmazonSQS client = AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
//        return client;
//    }



}
