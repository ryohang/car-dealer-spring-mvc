package io.ascending.training.service.jms;

import com.amazon.sqs.javamessaging.message.SQSObjectMessage;
import io.ascending.training.enumdef.WorkerMessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xshen
 */
@Service
public class MessageService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${jms.queue.name}")
    private String defaultDestination;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Transactional
    public void sendMessage(WorkerMessageType msgType, String message, long... delay) {
        sendMessage(defaultDestination,msgType.name(),message,delay);
    }

    @Transactional
    public void sendMessage(String destination,WorkerMessageType msgType, String message, long... delay) {
        sendMessage(destination,msgType.name(),message,delay);
    }
    @Transactional
    public void sendMessage(String destination, String msgType, String message, long... delay) {
        Map<String, Object> map = new HashMap<>();
        map.put("msgType", msgType);
        map.put("msgText", message);
        ObjectMessage objectMessage;
        try {
            objectMessage = new SQSObjectMessage();
            objectMessage.setObject((Serializable) map);
            if (delay.length > 0) {
                // in order to make the delayed message work
                // need add schedulerSupport="true" to the following line in activemq.xml in
                // the activemq/conf install directory.
                // <broker xmlns="http://activemq.apache.org/schema/core"
                // brokerName="localhost" dataDirectory="${activemq.data}" schedulerSupport="true">
                //TODO delay will be support in 1.0.2 https://github.com/awslabs/amazon-sqs-java-messaging-lib/pull/12/commits/0a0b963ec42f2e87e6846097064cfee0a46204c0
                jmsTemplate.convertAndSend(destination,objectMessage,new ScheduleMessagePostProcessor(delay[0]));
            } else {
                jmsTemplate.convertAndSend(destination,objectMessage);
            }
        } catch (JMSException e) {
            logger.error("error sending sqs message",e);
        }

    }


    @Transactional
    public void scheduleMessage(WorkerMessageType msgType, String message, long startTime) {
        scheduleMessage(defaultDestination,msgType.name(), message, startTime);
    }

    @Transactional
    public void scheduleMessage(String destination,String msgType, String message, long startTime) {
        Map<String, String> map = new HashMap<>();
        map.put("msgType", msgType);
        map.put("msgText", message);
        ObjectMessage objectMessage;
        try {
            objectMessage = new SQSObjectMessage();
            objectMessage.setObject((Serializable) map);
            jmsTemplate.convertAndSend(destination,objectMessage, new ScheduleMessagePostProcessor(Long.valueOf(startTime - System.currentTimeMillis())));
        } catch (JMSException e) {
            logger.error("error sending sqs message",e);
        }
    }




}
