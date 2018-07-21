/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.ascending.training.service.jms;

import org.springframework.jms.core.MessagePostProcessor;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 *
 * @author xshen
 */
public class ScheduleMessagePostProcessor implements MessagePostProcessor {

    private long delay = 0l;

    public ScheduleMessagePostProcessor(long delay) {
        this.delay = delay;
    }

    @Override
    public Message postProcessMessage(Message message) throws JMSException {
        if (delay > 0) {
            //TODO delay will be support in 1.0.2 https://github.com/awslabs/amazon-sqs-java-messaging-lib/pull/12/commits/0a0b963ec42f2e87e6846097064cfee0a46204c0
            message.setLongProperty("delay", delay);
        }
        return message;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }
}
