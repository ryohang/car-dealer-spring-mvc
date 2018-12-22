package io.ascending.training.service.jms;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageSQSService {
    private AmazonSQS sqs;
    private String queueUrl;
//    @Value("${jms.queue.name}")
//    private String sqsName;

    /**
     * AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
     * MessageSQSService msqs = new MessageSQSService(sqs)
     */
    public MessageSQSService(@Autowired AmazonSQS sqs,@Value("${jms.queue.name}") String sqsName){
        this.sqs = sqs;
        this.queueUrl = getQueueUrl(sqsName);
    }

    public String getQueueUrl(String queueName){
        String queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();
        return queueUrl;
    }

    public void sendMessage(String messageBody,Integer delaySec){
        SendMessageRequest sendMsgRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody)
                .withDelaySeconds(delaySec);
        sqs.sendMessage(sendMsgRequest);
    }


}
